#include "dice.h"
#include <stdlib.h>

void roll(swsim_die* die) {
    die->result = (rand() % die->faces) + 1;
}