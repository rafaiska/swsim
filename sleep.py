from acao import Acao


class Sleep(Acao):
    def __init__(self):
        super().__init__()
        self.ator = None

    def executar(self):
        print('{} não faz nada'.format(self.ator))
