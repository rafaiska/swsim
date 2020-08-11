from typing import Optional

from attack import Attack
from sleep import Sleep
from sw_roll import SWRoll


class ActionFactory:
    @staticmethod
    def create(action, **kwargs):
        action_name = action[0]
        if action_name == 'ataque_desarmado':
            return ActionFactory._create_unarmed_attack(kwargs)
        elif action_name == 'sleep':
            r_acao = Sleep()
            r_acao.ator = actor
            return r_acao
        raise AttributeError('Invalid action name')

    @staticmethod
    def _create_unarmed_attack(**kwargs):
        new_factory = AttackFactory('unarmed_attack')
        new_factory.attacker = attacker
        new_factory.skill = 'lutar'
        new_factory.damage_dice = [attacker.get_attribute('forca')]
        new_factory.damage_mod = 0
        return new_factory


class AttackFactory:
    def __init__(self, action_class):
        self.action_class = action_class
        self.ability_score = None
        self.ability_mod = None
        self.damage_dice = None
        self.damage_mod = None

    def create(self):
        new_action = Attack()
        new_action.attacker = self.attacker
        ability_dice = []
        ability_modifier = - self.attacker.wounds
        fighting_skill = self.attacker.get_skill('lutar')
        if fighting_skill is None:
            ability_dice.append(4)
            modificador = - 2
        else:
            dados.append(atacante.get_skill('lutar'))
            modificador = 0
        ataque.attack_roll = SWRoll(dados, modificador)
        ataque.damage_roll = SWRoll([atacante.get_attribute('forca')], 0)
        return ataque
