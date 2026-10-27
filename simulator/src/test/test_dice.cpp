#include <gtest/gtest.h>
#include <cstdlib>

#include "dice.h"

class DiceTest : public testing::Test {
    protected:
        swsim_die die;

        DiceTest()
        {
        }

        void SetUp() override
        {
            srand(0);
        }

        void TearDown() override
        {
        }
};

TEST_F(DiceTest, roll)
{
    die.faces = 6;
    roll(&die);
    ASSERT_GE(die.result, 1);
    ASSERT_LE(die.result, die.faces);
}
