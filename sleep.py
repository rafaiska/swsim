from action import Action


class Sleep(Action):
    def __init__(self):
        super().__init__()
        self.ator = None

    def execute(self):
        print('{} não faz nada'.format(self.ator))
