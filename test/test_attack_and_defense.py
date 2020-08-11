import logging
import unittest
from unittest import mock

from action_factory import ActionFactory
from attack import Attack
from personagem_factory import PersonagemFactory


class TestSWRoll(unittest.TestCase):
    def setUp(self) -> None:
        logging.basicConfig(level=logging.INFO, filemode='w',
                            format='%(filename)s:%(levelname)s %(message)s')
        factory = PersonagemFactory().using('grunt')
        self.grunt_1 = factory.create('good')
        self.grunt_2 = factory.create('bad')

    def test_unnarmed_attack(self):
        attack = ActionFactory().create('ataque_desarmado', self.grunt_1)
        attack.attacked = self.grunt_2
        results = [3, 6, 5]
        with mock.patch('sw_roll.randint', lambda a, b: results.pop()):
            attack.execute()
        self.assertEqual(5, attack.attack_roll.result_sum)
        self.assertEqual(9, attack.damage_roll)
