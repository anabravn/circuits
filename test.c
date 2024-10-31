#include <check.h>
#include <stdlib.h>

#include "circuits.h"

START_TEST (check_board_init)
{
    Board *b = BoardInit(10, 10);

    for(int i = 0; i < 10; i++) {
        for(int j = 0; j < 10; j++) {
            ck_assert(b->ptr[i][j] == 0);
        }
    }

    BoardFree(b);
}

Suite *board_suite(void) {
    Suite *s;
    TCase *tc_core;

    s = suite_create("Board initialization");
    tc_core = tcase_create("init");

    tcase_add_test(tc_core, check_board_init);
    suite_add_tcase(s, tc_core);

    return s;
}

int main(void) {
    int no_failed = 0;
    Suite *s;
    SRunner *runner;
     
    s = board_suite();
    runner = srunner_create(s);

    srunner_run_all(runner, CK_NORMAL);
    no_failed = srunner_ntests_failed(runner); 

    srunner_free(runner);                      
    return (no_failed == 0) ? EXIT_SUCCESS : EXIT_FAILURE; 
}