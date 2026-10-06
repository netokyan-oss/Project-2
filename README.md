# CSC2820 Data Structures Programming Project 2

## Student
Kyan Neto

## Course
CSC2820 - Data Structures

## Project
Programming Project 2 - Doubly Linked List, Finger Searching, and Stable Matching

## Description
This project implements a generic doubly linked list with previous and next
pointers, finger searching, and a stable matching algorithm using the
Gale-Shapley algorithm.

The project is divided into three main parts:

- Phase I: Doubly linked list implementation
- Phase II: Finger searching
- Phase III: Stable matching for the dodgeball draft

## Files

- `DoubleNode.java` - Node used by the doubly linked list
- `DoubleLinkedList.java` - Generic doubly linked list implementation
- `Finger.java` - Finger used to improve list searching
- `Candidate.java` - Represents teams and players and their preferences
- `DodgeballDraft.java` - Loads candidates and performs stable matching

## Test Cases

The project was tested using the instructor-provided test cases.

### Case 1
The generated output was compared with the expected output using `diff`.

Result: Passed with no differences.

### Case 2
The generated output was compared with the expected output using `diff`.

Result: Passed with no differences.

## How to Compile

From the project directory:

```bash
rm -rf bin
mkdir bin
javac -d bin src/list/*.java src/Candidate.java src/DodgeballDraft.java

java -cp bin DodgeballDraft


### What my project should look like

DS Project2
├── README.md
├── src
│   ├── Candidate.java
│   ├── DodgeballDraft.java
│   └── list
│       ├── DoubleLinkedList.java
│       ├── DoubleNode.java
│       └── Finger.java
└── test-cases
    ├── case1
    │   ├── teams1.txt
    │   ├── players1.txt
    │   ├── results1.txt
    │   └── myresults.txt
    └── case2
        ├── teams2.txt
        ├── players2.txt
        ├── results2.txt
        └── myresults.txt
