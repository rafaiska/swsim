from abc import abstractmethod


class Acao:
    def __init__(self):
        pass

    @abstractmethod
    def executar(self):
        raise NotImplementedError
