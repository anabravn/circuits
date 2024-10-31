main: main.c
	gcc main.c -o main -Wall

check: test.c 
	gcc circuits.c test.c -o check -lcheck -Wall

test: check
	./check