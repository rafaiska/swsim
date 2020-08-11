import logging

from sw_roll import SWRoll


class Character:
    def __init__(self):
        self.id = None
        self.is_wildcard = False
        self.wounds = 0
        self.fatigue = 0
        self.actions = []
        self.reactions = {}
        self.attributes = {}
        self.abilities = {}
        self.effects = []
        self.group = None
        self.equipment = []
        self.body_parts = []
        self.inventory = []
        self.ai = None
        self.shaken = False

    def is_incapacitated(self):
        if self.is_wildcard:
            return self.wounds >= 4
        else:
            return self.wounds >= 1

    def get_group(self):
        return self.group

    def play(self, characters, melee_nodes, teams):
        return self.ai.jogar(self, characters, melee_nodes, teams)

    def get_skill(self, nome):
        skill_name = 'pericia_' + nome
        if skill_name in self.attributes:
            return self.attributes[skill_name]
        else:
            return None

    def get_attribute(self, attribute):
        if attribute not in self.attributes:
            raise AttributeError
        else:
            return self.attributes[attribute]

    def shake(self):
        logging.info('{} is shaken'.format(self.id))
        self.shaken = True

    def try_recover_shaken(self):
        spirit_roll = SWRoll([self.attributes['espirito']], - self.wounds)
        logging.info('{} is trying to recover from shaken...'.format(self.id, spirit_roll))
        if spirit_roll.hability_roll(self.is_wildcard) >= 4:
            logging.info('Spirit roll: {}'.format(spirit_roll))
            logging.info('{} is not shaken anymore'.format(self.id))
            self.shaken = False

    def inflict_wound(self, wounds):
        if self.is_incapacitated():
            raise RuntimeError('Inflicting wound on incapacitated character')
        if wounds > 0:
            logging.info('{} suffered {} wounds'.format(self.id, wounds))
        self.wounds += wounds

    def __str__(self):
        return self.id
