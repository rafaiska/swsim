import random

from ia.ia_factory import IAFactory
from character import Character


class PersonagemFactory:
    def __init__(self):
        self.counter = 0
        self.builders = {
            'grunt': {'is_carta_selvagem': False,
                      'base_builder': None,
                      'nome': 'grunt',
                      'atributos': {
                          'agilidade': [6],
                          'inteligencia': [6],
                          'espirito': [6],
                          'forca': [6],
                          'vigor': [6],
                          'deslocamento': [6],
                          'aparar': [4],
                          'resistencia': [4],
                          'carisma': [0]
                      },
                      'pericias': {
                          'lutar': [4, 6],
                          'atirar': [4, 6],
                          'perceber': [4, 6],
                      },
                      'partes_corpo': [
                          'cabeca', '#tronco', 'braco_d', 'braco_e', 'perna_d', 'perna_e', '$mao_d', '$mao_e'
                      ],
                      'acoes': [['ataque_desarmado']],
                      'inventario': [
                          ['espada_curta', 100],
                          ['arco_curto', 'flecha*10', 50],
                          ['corselete_couro', 100]
                      ],
                      'ia': 'ia_simples'
                      },
            'pc_basic': {'is_carta_selvagem': True,
                         'base_builder': None,
                         'nome': 'personagem_jogador',
                         'atributos': {
                             'agilidade': [6],
                             'inteligencia': [6],
                             'espirito': [6],
                             'forca': [8],
                             'vigor': [6],
                             'deslocamento': [6],
                             'aparar': [4],
                             'resistencia': [4],
                             'carisma': [0]
                         },
                         'pericias': {
                             'lutar': [4, 6],
                             'atirar': [4, 6],
                             'perceber': [4, 6],
                         },
                         'partes_corpo': [
                             'cabeca', '#tronco', 'braco_d', 'braco_e', 'perna_d', 'perna_e', '$mao_d', '$mao_e'
                         ],
                         'acoes': [['ataque_desarmado']],
                         'inventario': [
                             ['espada_curta', 100],
                             ['arco_curto', 'flecha*10', 100],
                             ['corselete_couro', 100]
                         ],
                         'ia': 'ia_simples'
                         }}
        self.selected_builder = None

    def using(self, builder):
        if builder not in self.builders:
            raise RuntimeError
        else:
            self.selected_builder = self.builders[builder]
        return self

    def create(self, grupo):
        novo = Character()
        novo.group = grupo
        self._preencher(novo)
        return novo

    def _preencher(self, novo, builder=None):
        if builder is None:
            if self.selected_builder is None:
                return
            builder = self.selected_builder
        novo.id = builder['nome'] + str(self._increment_counter())
        base_builder = builder['base_builder']
        if base_builder is not None:
            self._preencher(novo, self.builders[base_builder])
        atributos = builder['atributos']
        for atributo in atributos:
            novo.attributes[atributo] = self._resolver_atributo(atributos[atributo])
        pericias = builder['pericias']
        for pericia in pericias:
            pericia_nome = 'pericia_' + pericia
            novo.attributes[pericia_nome] = self._resolver_atributo(pericias[pericia])
        partes_corpo = builder['partes_corpo']
        for parte_corpo in partes_corpo:
            novo.body_parts.append(parte_corpo)
        self._preencher_resistencias(novo)
        novo.is_wildcard = builder['is_carta_selvagem']
        novo.ai = IAFactory().with_builder(builder['ia']).create()
        for acao in builder['acoes']:
            novo.actions.append(acao)

    def _resolver_atributo(self, param):
        return random.choice(param)

    def _preencher_resistencias(self, novo):
        for parte in novo.body_parts:
            resistencia_nome = 'resitencia:' + parte
            novo.attributes[resistencia_nome] = (novo.attributes['vigor'] // 2) + 2
            if parte[0] == '#':
                novo.attributes['resistencia'] = (novo.attributes['vigor'] // 2) + 2

    def _increment_counter(self):
        self.counter += 1
        return self.counter
