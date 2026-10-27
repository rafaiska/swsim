#include "dice.h"
#include <stdint.h>
#include <stdlib.h>

static void init_pool_faces(uint8_t faces, swsim_die* d_pool, uint8_t* counter);

void roll(swsim_die* die) {
    die->result = (rand() % die->faces) + 1;
}

void init_pool(swsim_dice_pool* pool) {
    init_pool_faces(4, pool->d4_pool, &(pool->d4_cursor));
    init_pool_faces(6, pool->d6_pool, &(pool->d6_cursor));
    init_pool_faces(8, pool->d8_pool, &(pool->d8_cursor));
    init_pool_faces(10, pool->d10_pool, &(pool->d10_cursor));
    init_pool_faces(12, pool->d12_pool, &(pool->d12_cursor));
    init_pool_faces(20, pool->d20_pool, &(pool->d20_cursor));
}

static void init_pool_faces(uint8_t faces, swsim_die* d_pool, uint8_t* counter) {
    for (int i = 0; i < DICE_POOL_SIZE; i++) {
        d_pool[i].faces = faces;
        roll(&(d_pool[i]));
    }
    *counter = 0;
}
