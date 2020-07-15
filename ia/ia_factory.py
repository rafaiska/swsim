from ia.ia_simples import IASimples


class IAFactory:
    def __init__(self):
        self.builders = {'ia_simples': IASimples}
        self.selected_builder = None

    def with_builder(self, builder):
        self.selected_builder = builder
        return self

    def create(self):
        if self.selected_builder not in self.builders:
            raise AttributeError
        return self.builders[self.selected_builder]()
