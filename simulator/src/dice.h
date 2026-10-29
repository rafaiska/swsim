#ifndef SWSIM_DICE_H
#define SWSIM_DICE_H

#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

#define DICE_POOL_SIZE 128

typedef struct swsim_die {
    uint8_t faces;
    uint8_t result;
} swsim_die;

typedef struct swsim_dice_pool {
    swsim_die d4_pool[DICE_POOL_SIZE];
    swsim_die d6_pool[DICE_POOL_SIZE];
    swsim_die d8_pool[DICE_POOL_SIZE];
    swsim_die d10_pool[DICE_POOL_SIZE];
    swsim_die d12_pool[DICE_POOL_SIZE];
    swsim_die d20_pool[DICE_POOL_SIZE];
    uint8_t d4_cursor;
    uint8_t d6_cursor;
    uint8_t d8_cursor;
    uint8_t d10_cursor;
    uint8_t d12_cursor;
    uint8_t d20_cursor;
    uint8_t init;
} swsim_dice_pool;

void roll(swsim_die* die);
void init_pool(swsim_dice_pool* pool, uint32_t seed);
/* Returns NULL when faces is unsupported or amount exceeds DICE_POOL_SIZE. */
swsim_die* roll_dice(uint8_t faces, uint8_t amount);

#ifdef __cplusplus
}
#endif

#endif
