import enum


class Suit(enum.Enum):
    CLUBS = 0
    DIAMONDS = 1
    HEARTS = 2
    SPADES = 3
    JOKER = 4

    @staticmethod
    def get_suits_without_joker():
        return [Suit.CLUBS, Suit.DIAMONDS, Suit.SPADES, Suit.HEARTS]

    def __eq__(self, other):
        return self.value == other.value

    def __lt__(self, other):
        return self.value < other.value


class Card:
    def __init__(self, suit: Suit, number: int):
        self.suit = suit
        self.number = number

    def __str__(self):
        if self.suit == Suit.JOKER:
            return 'Joker'

        if self.number == 1:
            number_name = 'A'
        elif 2 <= self.number <= 10:
            number_name = str(self.number)
        elif self.number == 11:
            number_name = 'J'
        elif self.number == 12:
            number_name = 'Q'
        elif self.number == 13:
            number_name = 'K'
        else:
            raise RuntimeError

        return number_name + ' of ' + self.suit.name

    def __eq__(self, other):
        return self.suit == other.suit and self.number == other.number

    def __lt__(self, other):
        return self.suit < other.suit or (self.suit == other.suit and self.number < other.number)
