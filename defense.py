from typing import Optional

from action import Action
from attack import Attack


class Defense(Action):
    def __init__(self):
        super().__init__(reaction=True)
        self.attack_action: Optional[Attack] = None

    def execute(self):
        defender = self.attack_action.attacked
        difficulty = defender.get_attribute('resistencia')
        if self.attack_action.attack_roll.hit:
            self.attack_action.damage_roll.check_against_difficulty(difficulty)
            if self.attack_action.damage_roll.hit:
                wounds = self.attack_action.damage_roll.raises
                if not defender.shaken:
                    defender.shake()
                else:
                    wounds += 1
                defender.inflict_wound(wounds)
