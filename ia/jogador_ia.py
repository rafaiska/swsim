from abc import abstractmethod


class JogadorIA:
    @abstractmethod
    def jogar(self, personagem, outros_personagens, melee_nodes, teams):
        raise NotImplementedError
