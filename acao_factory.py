from ataque import Ataque
from jogada import Jogada
from sleep import Sleep


class AcaoFactory:
    def create(self, acao, ator):
        acao_name = acao[0]
        if acao_name == 'ataque_desarmado':
            return self._create_ataque_desarmado(ator)
        elif acao_name == 'sleep':
            r_acao = Sleep()
            r_acao.ator = ator
            return r_acao
        raise AttributeError

    def _create_ataque_desarmado(self, atacante):
        ataque = Ataque()
        ataque.atacante = atacante
        dados = []
        if atacante.is_carta_selvagem:
            dados.append((1, 6))
        pericia_lutar = atacante.get_pericia('lutar')
        if pericia_lutar is None:
            dados.append((1, 4))
            modificador = - 2
        else:
            dados.append((1, atacante.get_pericia('lutar')))
            modificador = 0
        ataque.jogada_ataque = Jogada(dados, modificador)
        ataque.jogada_dano = Jogada([(1, atacante.get_atributo('forca'))], 0)
        return ataque
