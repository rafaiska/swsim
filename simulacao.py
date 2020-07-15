from typing import List

from card import Card
from deck import Deck
from personagem import Personagem


class Simulacao:
    def __init__(self):
        self.personagens: List[Personagem] = []
        self.deck = None
        self.descarte = None
        self.team_groups = {}
        self.melee_nodes = []

    def preparar_deck(self):
        self.descarte = Deck()
        self.deck = Deck()
        self.deck.create_cards()

    def is_over(self):
        teams = set()
        for personagem in self.personagens:
            if not personagem.is_incapacitado():
                teams.add(self._get_team_personagem(personagem))
            if len(teams) > 1:
                return False
        return True

    def get_time_vencedor(self):
        teams = set()
        for personagem in self.personagens:
            if not personagem.is_incapacitado():
                teams.add(self._get_team_personagem(personagem))
        if len(teams) > 1:
            return None
        else:
            return teams.pop()

    def simular(self):
        turno = 1
        while not self.is_over():
            print('TURNO {}'.format(turno))
            self._step()
            turno += 1

    def _step(self):
        iniciativas = self._draw_iniciativa()
        for grupo in iniciativas:
            for personagem in self._get_personagens_grupo(grupo):
                if not personagem.is_incapacitado():
                    acao = personagem.jogar(self.personagens, self.melee_nodes, self.team_groups)
                    acao.executar()

    def _draw_iniciativa(self):
        grupos = self._get_grupos()
        iniciativas = {grupo: None for grupo in grupos}
        if len(iniciativas) > len(self.deck):
            self.preparar_deck()
        for grupo in iniciativas:
            iniciativas[grupo] = self.sacar_carta()
        return [k for k, v in sorted(iniciativas.items(), key=lambda x: x[1])]

    def _get_grupos(self) -> set:
        grupos = set()
        for p in self.personagens:
            grupos.add(p.grupo)
        return grupos

    def _get_personagens_grupo(self, grupo):
        return filter(lambda p: p.grupo == grupo, self.personagens)

    def sacar_carta(self) -> Card:
        carta = self.deck.random_draw()
        self.descarte.insert_card(carta)
        return carta

    def add_personagem(self, personagem):
        self.personagens.append(personagem)

    def add_team_grupo(self, grupo, team):
        if team not in self.team_groups:
            self.team_groups[team] = []
        self.team_groups[team].append(grupo)

    def _get_team_personagem(self, personagem):
        for team, groups in self.team_groups.items():
            if personagem.grupo in groups:
                return team
        raise RuntimeError
