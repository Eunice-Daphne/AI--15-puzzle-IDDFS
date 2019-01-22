# AI--15-puzzle-IDDFS
This program is written in Java. This program solves the 15-puzzle using IDDFS.
It supports the following goal state,

1  2  3  4
5  6  7  8
9  10 11 12
13 14 15 0

The main function and the IDDFS algorithm is written in FifteenPuzzleIDDFS.java
The code for intermediate state structure and the movement of the blank tile is written in Frame.java

The output of the program will print the following if the solution exists, else will print no solution found;
-Moves to reach goal state
-Number of nodes expanded
-Time taken
-Memory Used

To run:

You can compile both the files and run the file FifteenPuzzleIDDFS.java in the terminal by giving the input as arguments.
Or else put both the files in a default package in Eclipse and run the FifteenPuzzleIDDFS.java by specifying its arguments in the run configuration.

Sample input:
1 0 2 4 5 7 3 8 9 6 11 12 13 10 14 15

Sample output:
Moves: RDLDDRR
Number of Nodes expanded: 3576
Time Taken: 32ms
Memory Used: 10404.0kb
