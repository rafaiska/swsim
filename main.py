import logging
import pathlib

from personagem_factory import PersonagemFactory
from simulacao import Simulacao


def configurar(simulacao: Simulacao):
    g_factory = PersonagemFactory().using('grunt')
    p_factory = PersonagemFactory().using('pc_basic')
    grupo_aliados = 'aliados'
    grupo_inimigos = 'inimigos'
    simulacao.add_team_grupo(grupo_aliados, 1)
    simulacao.add_team_grupo(grupo_inimigos, 2)

    simulacao.add_personagem(p_factory.create(grupo_aliados))
    for _ in range(2):
        simulacao.add_personagem(g_factory.create((grupo_inimigos)))
    simulacao.preparar_deck()


def main():
    log_file = str(pathlib.Path.home()) + '/.swsim.log'
    logging.basicConfig(level=logging.INFO, filename=log_file, filemode='w',
                        format='%(filename)s:%(levelname)s %(message)s')
    simulacao = Simulacao()
    configurar(simulacao)
    while not simulacao.is_over():
        simulacao.simular()
    print('\n\nTime {} venceu!'.format(simulacao.get_time_vencedor()))


if __name__ == '__main__':
    main()
