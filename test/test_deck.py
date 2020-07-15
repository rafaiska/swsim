import unittest
from unittest import mock

from deck import Deck
from card import Card, Suit


class TestDeck(unittest.TestCase):
    def setUp(self) -> None:
        self.deck = Deck()

    def test_create(self):
        self.deck.create_cards()
        self.assertEqual(54, len(self.deck.cards), 'Deck should have specific number of cards')

    def test_insert_and_draw(self):
        card_a = Card(Suit.SPADES, 1)
        self.deck.insert_card(card_a)
        card_b = self.deck.draw()
        self.assertEqual(card_a, card_b)

    def test_draw_random(self):
        def mocked_randint(_, first, last):
            return first + last // 2

        self.deck.create_cards()
        with mock.patch('gameutils.deck.Random.randint', mocked_randint):
            card_a = self.deck.random_draw()
            card_b = self.deck.random_draw()
            card_c = self.deck.random_draw()
        self.assertEqual(Suit.SPADES, card_a.suit)
        self.assertEqual(7, card_a.number)
        self.assertEqual(Suit.HEARTS, card_b.suit)
        self.assertEqual(7, card_b.number)
        self.assertEqual(Suit.DIAMONDS, card_c.suit)
        self.assertEqual(7, card_c.number)

    def test_card_comparator(self):
        card_a = Card(Suit.CLUBS, 10)
        card_b = Card(Suit.CLUBS, 11)
        self.assertGreater(card_b, card_a)
        card_a = Card(Suit.DIAMONDS, 10)
        self.assertGreater(card_a, card_b)


if __name__ == '__main__':
    unittest.main()
