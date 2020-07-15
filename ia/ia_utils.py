class IAUtils:
    @staticmethod
    def get_inimigos(personagem, outros_personagens, teams):
        inimigos = []
        time_personagem = IAUtils._get_team(personagem, teams)
        for p in outros_personagens:
            if p == personagem:
                continue
            t = IAUtils._get_team(p, teams)
            if t != time_personagem:
                inimigos.append(p)
        return inimigos

    @staticmethod
    def _get_team(personagem, teams):
        for team in teams:
            if personagem.grupo in teams[team]:
                return team
        raise RuntimeError
