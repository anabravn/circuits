/*! @file */ 

#include <stdio.h>
#include <stdlib.h>

#include "circuits.h"

/** 
 * @brief Inicializa um tabuleiro 
 *
 * @param width Largura do tabuleiro
 * @param height Altura do tabuleiro
 */
Board *BoardInit(int width, int height) {
    int **ptr = (int **) malloc(sizeof(int *) * height);
    Board *new = (Board *) malloc(sizeof(Board));

    for (int i = 0; i < height; i++) {
        ptr[i] = (int *) malloc(sizeof(int) * width);
        for (int j = 0; j < width; j++) {
            ptr[i][j] = 0;
        }
    }

    new->w = width;
    new->h = height;
    new->ptr = ptr;

    return new;
}

/**
* @brief Libera a memória de um tabuleiro
*
* @param b Tabuleiro
*/
void BoardFree(Board *b) {
    for (int i = 0; i < b->h; i++) {
        free(b->ptr[i]);
    }

    free(b->ptr);
    free(b);
}

/** 
* @brief Escreve o tabuleiro em um arquivo
*
* @param b Tabuleiro
* @param fp Arquivo
*/
void BoardPrint(FILE *fp, Board *b) {
    for (int i = 0; i < b->h; i++) {
        for (int j = 0; j < b->w; j++) {
            fprintf(fp, " %c", b->ptr[i][j] ? PIECE_CHAR : ' ');
        }
        fputc('\n', fp);
    }
}

/**
* @brief Ativa uma célula do tabuleiro
* 
* @param b Tabuleiro
* @param x Cordenada x
* @param y Cordenada y
*/
void BoardSet(Board *b, int x, int y) {
    b->ptr[y][x] = 1;
}

int main(void) {
    Board *b = BoardInit(4, 4);
    BoardSet(b, 3, 3);
    BoardSet(b, 0, 0);
    BoardPrint(stdout, b);

    BoardFree(b);

    return 0;
}