add_library(swsim_simulator
    src/dice.c
)

target_include_directories(swsim_simulator
    PUBLIC
        ${CMAKE_CURRENT_SOURCE_DIR}/src
)

find_package(GTest REQUIRED)

add_executable(swsim_tests
    src/test/test_dice.cpp
    src/test/test_main.cpp
)

target_link_libraries(swsim_tests
    PRIVATE
        swsim_simulator
        GTest::gtest
)

add_test(NAME swsim_tests COMMAND swsim_tests)
