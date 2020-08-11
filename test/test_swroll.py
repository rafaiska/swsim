import logging
import unittest
from unittest import mock

from sw_roll import SWRoll


class TestSWRoll(unittest.TestCase):
    def setUp(self) -> None:
        logging.basicConfig(level=logging.INFO, filemode='w',
                            format='%(filename)s:%(levelname)s %(message)s')

    def test_ability_check_extra(self):
        roll = SWRoll([8], 2)
        results = [4]
        with mock.patch('sw_roll.randint', lambda a, b: results.pop()):
            roll.hability_roll()
        self.assertEqual('[8] + 2 = [[4]] + 2 = 6', str(roll))

    def test_ability_check_wc(self):
        roll = SWRoll([8], 2)
        results = [4, 2]
        with mock.patch('sw_roll.randint', lambda a, b: results.pop()):
            roll.hability_roll(True)
        self.assertEqual('[8] + 2 = [[2], [4]] + 2 = 6', str(roll))

    def test_ability_check_wc_ace(self):
        roll = SWRoll([8], 2)
        results = [4, 3, 6]
        with mock.patch('sw_roll.randint', lambda a, b: results.pop()):
            roll.hability_roll(True)
        self.assertEqual('[8] + 2 = [[6, 3], [4]] + 2 = 11', str(roll))

    def test_damage_check(self):
        roll = SWRoll([8, 8], 2)
        results = [4, 6]
        with mock.patch('sw_roll.randint', lambda a, b: results.pop()):
            roll.damage_roll()
        self.assertEqual('[8, 8] + 2 = [[6], [4]] + 2 = 12', str(roll))

    def test_damage_check_ace(self):
        roll = SWRoll([8, 8], 2)
        results = [6, 8, 4, 8]
        with mock.patch('sw_roll.randint', lambda a, b: results.pop()):
            roll.damage_roll()
        self.assertEqual('[8, 8] + 2 = [[8, 4], [8, 6]] + 2 = 28', str(roll))

    def test_hit_chance(self):
        roll = SWRoll([10], 0)
        self.assertEqual(0.7562201284610328, roll.get_hit_chance(2))
