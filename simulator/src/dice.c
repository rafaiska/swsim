#include "dice.h"
#include <stdint.h>
#include <stddef.h>
#include <stdlib.h>
#include <time.h>

swsim_dice_pool DICE_POOL;

static void init_pool_faces(uint8_t faces, swsim_die* d_pool, uint8_t* counter);
static swsim_die* roll_dice_from_pool(swsim_die* pool, uint8_t* cursor, uint8_t faces, uint8_t amount);

void roll(swsim_die* die) {
    die->result = (rand() % die->faces) + 1;
}

void init_pool(swsim_dice_pool* pool, uint32_t seed) {
    srand(seed);
    pool->init = 1;
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

swsim_die* roll_dice(uint8_t faces, uint8_t amount) {
    if (amount > DICE_POOL_SIZE)
        return NULL;

    if (DICE_POOL.init == 0)
        init_pool(&DICE_POOL, time(NULL));

    switch(faces) {
        case 4: return roll_dice_from_pool(DICE_POOL.d4_pool, &DICE_POOL.d4_cursor, faces, amount);
        case 6: return roll_dice_from_pool(DICE_POOL.d6_pool, &DICE_POOL.d6_cursor, faces, amount);
        case 8: return roll_dice_from_pool(DICE_POOL.d8_pool, &DICE_POOL.d8_cursor, faces, amount);
        case 10: return roll_dice_from_pool(DICE_POOL.d10_pool, &DICE_POOL.d10_cursor, faces, amount);
        case 12: return roll_dice_from_pool(DICE_POOL.d12_pool, &DICE_POOL.d12_cursor, faces, amount);
        case 20: return roll_dice_from_pool(DICE_POOL.d20_pool, &DICE_POOL.d20_cursor, faces, amount);
        default: return NULL;
    }
}

static swsim_die* roll_dice_from_pool(swsim_die* pool, uint8_t* cursor, uint8_t faces, uint8_t amount) {
    if (*cursor + amount > DICE_POOL_SIZE)
        init_pool_faces(faces, pool, cursor);
    swsim_die* result = pool + *cursor;
    *cursor += amount;
    return result;
}
