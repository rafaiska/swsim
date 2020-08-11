import logging
from typing import Optional

from action import Action
from character import Character
from sw_roll import SWRoll


class Attack(Action):
    def __init__(self):
        super().__init__()
        self.attacker: Optional[Character] = None
        self.attacked: Optional[Character] = None
        self.attack_roll: Optional[SWRoll] = None
        self.damage_roll: Optional[SWRoll] = None
        self.is_melee: Optional[bool] = None

    def execute(self):
        logging.info('{} attacks {}'.format(self.attacker, self.attacked))
        if self.is_melee:
            difficulty = self.attacked.get_attribute('aparar')
        else:
            difficulty = 4
        self.attack_roll.hability_roll(self.attacker.is_wildcard)
        self.attack_roll.check_against_difficulty(difficulty)
        self.damage_roll.damage_roll(self.attack_roll.raises >= 1)
