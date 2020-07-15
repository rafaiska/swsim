import random
from queue import Queue


class Jogada:
    def __init__(self, dados, modificador):
        self.dados = dados
        self.modificador = modificador

    def rolar_habilidade(self):
        echo = 'Rolando habilidade {}: '.format(self.__str__())
        resultados_rolagens = []
        for dado in self.dados:
            quantidade = dado[0]
            faces = dado[1]
            for _ in range(quantidade):
                rolagem = Rolagem(faces, True)
                resultados_rolagens.append(rolagem.rolar())
        resultado_final = max(resultados_rolagens)
        echo += '{} {}{}'.format(resultado_final, '+' if self.modificador >= 0 else '', self.modificador)
        resultado_final += self.modificador
        print(echo)
        return resultado_final if resultado_final >= 0 else 0

    def rolar_dano(self):
        acumulador = self.modificador
        for dado in self.dados:
            quantidade = dado[0]
            faces = dado[1]
            for _ in range(quantidade):
                acumulador += Rolagem(faces).rolar()
        return acumulador if acumulador >= 0 else 0

    def __str__(self):
        ret = ''
        for dado in self.dados:
            ret += '{}d{}, '.format(dado[0], dado[1])
        ret += '{}{}'.format('+' if self.modificador >= 0 else '', self.modificador)
        return ret


class Rolagem:
    def __init__(self, faces, permite_as=False):
        self.faces = faces
        self.permite_as = permite_as
        self._resultados = None

    def _roll_and_append(self):
        resultado = random.randint(1, self.faces)
        self._resultados.append(resultado)
        return resultado

    def rolar(self):
        self._resultados = []
        resultado = self._roll_and_append()
        while self.permite_as and resultado == self.faces:
            resultado = self._roll_and_append()
        return sum(self._resultados)

    def get_resultado(self):
        return sum(self._resultados)
