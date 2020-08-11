from abc import abstractmethod
from typing import Optional


class Action:
    def __init__(self, reaction=False):
        self.reaction: bool = reaction

    @abstractmethod
    def execute(self):
        raise NotImplementedError
