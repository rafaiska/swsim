from card import Suit, Card
from random import Random


class Deck:
    def __init__(self):
        self.cards = list()

    def create_cards(self) -> None:
        if len(self.cards) > 0:
            raise RuntimeError('Deck must be empty')
        for number in range(1, 14):
            for suit in Suit.get_suits_without_joker():
                self.cards.append(Card(suit, number))
        self.cards.append(Card(Suit.JOKER, 1))
        self.cards.append(Card(Suit.JOKER, 2))

    def draw(self) -> Card:
        return self.cards.pop()

    def random_draw(self) -> Card:
        draw = Random().randint(0, len(self.cards) - 1)
        return self.cards.pop(draw)

    def insert_card(self, card: Card) -> None:
        self.cards.append(card)

    def __len__(self):
        return len(self.cards)
