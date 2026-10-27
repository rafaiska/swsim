#ifndef SWSIM_DICE_H
#define SWSIM_DICE_H

#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

typedef struct swsim_die {
    uint8_t faces;
    uint8_t result;
} swsim_die;

void roll(swsim_die* die);

#ifdef __cplusplus
}
#endif

#endif
