from jogada import Jogada


class Personagem:
    def __init__(self):
        self.id = None
        self.is_carta_selvagem = False
        self.ferimentos = 0
        self.fadiga = 0
        self.acoes = []
        self.atributos = {}
        self.habilidades = {}
        self.efeitos = []
        self.grupo = None
        self.equipamento = []
        self.partes_corpo = []
        self.inventario = []
        self.ia = None
        self.abalado = False

    def is_incapacitado(self):
        if self.is_carta_selvagem:
            return self.ferimentos >= 4
        else:
            return self.ferimentos >= 1

    def get_grupo(self):
        return self.grupo

    def jogar(self, personagens, melee_nodes, teams):
        return self.ia.jogar(self, personagens, melee_nodes, teams)

    def get_pericia(self, nome):
        pericia_nome = 'pericia_' + nome
        if pericia_nome in self.atributos:
            return self.atributos[pericia_nome]
        else:
            return None

    def get_atributo(self, atributo):
        if atributo not in self.atributos:
            raise AttributeError
        else:
            return self.atributos[atributo]

    def abalar(self):
        if self.abalado:
            self.ferir(1)
        else:
            self.abalado = True

    def desabalar(self):
        jogada_espirito = Jogada([(1, self.atributos['espirito'])], - self.ferimentos)
        if jogada_espirito.rolar_habilidade() >= 4:
            self.abalado = False

    def ferir(self, qt_ferimentos):
        self.ferimentos += qt_ferimentos

    def __str__(self):
        return self.id
