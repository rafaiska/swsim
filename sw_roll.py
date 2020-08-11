import logging
import math
from random import randint
from typing import List

P_DIST_EXP_FIT = {4: (1.42755031, -0.34242885),
                  6: (1.37729571, -0.27095012),
                  8: (1.33679802, -0.21891428),
                  10: (1.30948005, -0.18301764),
                  12: (1.2897073, -0.15680166)}


def _calc_probability(die: int, difficulty):
    exp_fit = P_DIST_EXP_FIT[die]
    return exp_fit[0] * math.exp((difficulty + 1) * exp_fit[1])


def _combine_probability(b, a):
    return ((1 - a) * b) + ((1 - b) * a) + (a * b)


class SWRoll:
    def __init__(self, dice: List[int], modifier: int):
        self.dice = dice
        self.modifier = modifier
        self.results = None
        self.result_sum = None
        self.hit = None
        self.raises = None

    def _roll(self, dice_to_roll: List[int], allow_ace=True):
        self.results = []
        while len(dice_to_roll) > 0:
            die = dice_to_roll.pop()
            d_roll = DiceRoll(die)
            d_roll.roll()
            results = [d_roll.result]
            while d_roll.is_max() and allow_ace:
                d_roll.roll()
                results.append(d_roll.result)
            self.results.append(sorted(results, reverse=True))

    def hability_roll(self, is_wildcard=False):
        dice_to_roll = self.dice.copy()
        if is_wildcard:
            dice_to_roll.append(6)
        self._roll(dice_to_roll)
        self._make_hability_results()
        logging.info('Hability roll: {}'.format(self))
        return self.result_sum

    def damage_roll(self, extra_damage_by_raise=False, is_bennie_reroll=False):
        dice_to_roll = self.dice.copy()
        if extra_damage_by_raise:
            dice_to_roll.append(6)
        modifier = self.modifier
        if is_bennie_reroll:
            modifier += 2
        self._roll(dice_to_roll)
        self._make_damage_results(modifier)
        logging.info('Damage roll: {}'.format(self))
        return self.result_sum

    def check_against_difficulty(self, difficulty: int):
        self.hit = self.result_sum >= difficulty
        self.raises = max(0, self.result_sum - difficulty)

    def __str__(self):
        ret = '{} '.format(self.dice)
        ret += '+ ' if self.modifier >= 0 else '- '
        ret += str(abs(self.modifier))
        ret += ' = {} + {} = {}'.format(self.results, self.modifier, self.result_sum)
        return ret

    def _make_hability_results(self):
        self.result_sum = max([sum(r) for r in self.results]) + self.modifier

    def _make_damage_results(self, modifier: int):
        self.result_sum = sum([sum(r) for r in self.results]) + modifier

    def get_hit_chance(self, difficulty: int, is_wildcard=False):
        """ This is a really ROUGH estimate for AI decision purposes """
        d_minus_mod = difficulty - self.modifier
        dice = self.dice.copy()
        if is_wildcard:
            dice.append(6)
        die = dice[0]
        probability_a = _calc_probability(die, d_minus_mod)
        for die in dice[1:]:
            probability_b = _calc_probability(die, d_minus_mod)
            probability_a = _combine_probability(probability_b, probability_a)
        return probability_a


class DiceRoll:
    def __init__(self, faces):
        self.faces = faces
        self.result = None

    def roll(self):
        self.result = randint(1, self.faces)

    def is_max(self):
        return self.result is not None and self.result == self.faces
