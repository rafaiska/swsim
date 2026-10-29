#include <cstdint>
#include <gtest/gtest.h>
#include <cstdlib>
#include <string>

#include "dice.h"

void assert_die(swsim_die* die, uint8_t faces);
class DiceTest : public testing::Test {
    protected:
        swsim_die die;
        swsim_dice_pool pool;

        DiceTest()
        {
        }

        void SetUp() override
        {
            init_pool(&pool, 0);
        }

        void TearDown() override
        {
        }
};

TEST_F(DiceTest, roll)
{
    die.faces = 6;
    roll(&die);
    assert_die(&die, 6);
}

void assert_die(swsim_die* die, uint8_t faces) {
    ASSERT_EQ(die->faces, faces);
    ASSERT_GE(die->result, 1);
    ASSERT_LE(die->result, die->faces);
}

TEST_F(DiceTest, init_pool)
{
    ASSERT_EQ(pool.d4_cursor, 0);
    ASSERT_EQ(pool.d6_cursor, 0);
    ASSERT_EQ(pool.d8_cursor, 0);
    ASSERT_EQ(pool.d10_cursor, 0);
    ASSERT_EQ(pool.d12_cursor, 0);
    ASSERT_EQ(pool.d20_cursor, 0);
    for (int i=0; i < DICE_POOL_SIZE; ++i) {
        SCOPED_TRACE("dice pool index: " + std::to_string(i));
        assert_die(&(pool.d4_pool[i]), 4);
        assert_die(&(pool.d6_pool[i]), 6);
        assert_die(&(pool.d8_pool[i]), 8);
        assert_die(&(pool.d10_pool[i]),  10);
        assert_die(&(pool.d12_pool[i]), 12);
        assert_die(&(pool.d20_pool[i]), 20);
    }
}

TEST_F(DiceTest, test_roll_from_pool) {
    swsim_die* result = roll_dice(6, 10);
    ASSERT_NE(result, nullptr);
    for (int i = 0; i < 10; ++i) {
        SCOPED_TRACE("dice index: " + std::to_string(i));
        assert_die(&(result[i]), 6);
    }
}

TEST_F(DiceTest, rolls_up_to_pool_size) {
    swsim_die* result = roll_dice(8, DICE_POOL_SIZE);
    ASSERT_NE(result, nullptr);
    for (int i = 0; i < DICE_POOL_SIZE; ++i) {
        SCOPED_TRACE("dice index: " + std::to_string(i));
        assert_die(&(result[i]), 8);
    }
}

TEST_F(DiceTest, rejects_amount_larger_than_pool) {
    ASSERT_EQ(roll_dice(6, DICE_POOL_SIZE + 1), nullptr);
}

TEST_F(DiceTest, rejects_unsupported_faces) {
    ASSERT_EQ(roll_dice(7, 1), nullptr);
}
