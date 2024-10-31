#ifndef _CIRCUITS_SD
#define _CIRCUITS_SD

#define PIECE_CHAR 'o'

typedef struct {
    int w;
    int h;
    int **ptr;
} Board;

Board *BoardInit(int, int);
void BoardFree(Board *);

void BoardPrint(FILE *, Board *);

#endif