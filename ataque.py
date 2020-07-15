from acao import Acao


class Ataque(Acao):
    def __init__(self):
        super().__init__()
        self.atacante = None
        self.atacado = None
        self.jogada_ataque = None
        self.jogada_dano = None

    def executar(self):
        echo = '{} ataca {}'.format(self.atacante, self.atacado)
        limiar_inimigo = self.atacado.get_atributo('aparar')
        jogada_ataque = self.jogada_ataque.rolar_habilidade()
        ampliacoes = self._get_ampliacoes(limiar_inimigo, jogada_ataque)
        if ampliacoes == -1:
            print(echo + ', mas erra o ataque. Ataque {} contra Aparar {}'.format(jogada_ataque, limiar_inimigo))
            return
        echo += ' e acerta'
        dano = 2 if ampliacoes > 0 else 0
        dano += self.jogada_dano.rolar_dano()
        limiar_dano_inimigo = self.atacado.get_atributo('resistencia')
        ampliacoes = self._get_ampliacoes(limiar_dano_inimigo, dano)
        if ampliacoes == -1:
            print(echo + ', mas não causa dano')
            return
        elif ampliacoes == 0:
            print(echo + ', abalando o oponente')
            self.atacado.abalar()
        else:
            print(echo + ', causando {} ferimentos'.format(ampliacoes))
            self.atacado.ferir(ampliacoes)

    def _get_ampliacoes(self, limiar, jogada):
        if jogada < limiar:
            return - 1
        else:
            return (jogada - limiar) // 4
