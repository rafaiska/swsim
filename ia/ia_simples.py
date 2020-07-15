import random

from acao_factory import AcaoFactory
from ia.ia_utils import IAUtils
from ia.jogador_ia import JogadorIA


class IASimples(JogadorIA):
    def __init__(self):
        self.inimigos = None

    def jogar(self, personagem, outros_personagens, melee_nodes, teams):
        if personagem.abalado:
            personagem.desabalar()
        if personagem.abalado:
            return AcaoFactory().create(['sleep'], personagem)
        if self.inimigos is None:
            self.inimigos = IAUtils.get_inimigos(personagem, outros_personagens, teams)
        alvo = self._escolher_alvo(personagem, melee_nodes)
        if alvo is None:
            return AcaoFactory().create(['sleep'], personagem)
        acao_builder = self._get_acao_ataque(personagem)
        acao = AcaoFactory().create(acao_builder, personagem)
        acao.atacado = alvo
        return acao

    @staticmethod
    def _get_acao_ataque(personagem):
        for acao in personagem.acoes:
            nome_acao = acao[0]
            if nome_acao == 'ataque_desarmado':
                return acao

    @staticmethod
    def _get_personagem_melee_node(personagem, melee_nodes):
        for melee_node in melee_nodes:
            if personagem in melee_node:
                return melee_node
        return None

    def _escolher_alvo(self, personagem, melee_nodes):
        personagem_node = self._get_personagem_melee_node(personagem, melee_nodes)
        if personagem_node is not None:
            return random.choice(filter(lambda p: p != personagem and p in self.inimigos, personagem_node))
        else:
            return random.choice(self.inimigos)
