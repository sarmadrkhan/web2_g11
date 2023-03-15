# Lab 1
## Index
[Task 1: Matrix](#task-1--matrixTask)<br/>
[Task 2: Flatten Array](#task-2--flatten-array)<br/>
[Task 3: WordCount](#task-3--wordcount)<br/>
[Task 4: React](#task-4--react)<br/>

## Task 1 : Matrix

### Instructions

Given a string representing a matrix of numbers, return the rows and columns of that matrix.

So given a string with embedded newlines like:

9 8 7<br/>
5 3 2<br/>
6 6 7<br/>

representing this matrix:

|       | 1 | 2 | 3 |
|-------|---|---|---|
| **1** | 9 | 8 | 7 |
| **2** | 5 | 3 | 2 |
| **3** | 6 | 6 | 7 |

your code should be able to spit out:

A list of the rows, reading each row left-to-right while moving top-to-bottom across the rows,
A list of the columns, reading each column top-to-bottom while moving from left-to-right.
The rows for our example matrix:

* 9, 8, 7
* 5, 3, 2
* 6, 6, 7

And its columns:

* 9, 5, 6
* 8, 3, 6
* 7, 2, 7

## Task 2 : Flatten Array

### Instructions

Take a nested list and return a single flattened list with all values except nil/null.

The challenge is to write a function that accepts an arbitrarily-deep nested list-like structure and returns a flattened structure without any nil/null values.

For Example

input: [ 1, [2, 3, null, 4], [null], 5]

output: [ 1, 2, 3, 4, 5 ]

## Task 3 : WordCount

### Instructions

Given a phrase, count the occurrences of each word in that phrase.

For the purposes of this exercise you can expect that a word will always be one of:

A number composed of one or more ASCII digits (ie "0" or "1234") OR
A simple word composed of one or more ASCII letters (ie "a" or "they") OR
A contraction of two simple words joined by a single apostrophe (ie "it's" or "they're")
When counting words you can assume the following rules:

The count is case insensitive (ie "You", "you", and "YOU" are 3 uses of the same word)
The count is unordered; the tests will ignore how words and counts are ordered
Other than the apostrophe in a contraction all forms of punctuation are ignored
The words can be separated by any form of whitespace (ie "\t", "\n", " ")
For example, for the phrase "That's the password: 'PASSWORD 123'!", cried the Special Agent.\nSo I fled. the count would be:

- that's: 1
- the: 2
- password: 2
- 123: 1
- cried: 1
- special: 1
- agent: 1
- so: 1
- i: 1
- fled: 1

## Task 4 : React 

### Instructions

Implement a basic reactive system.

Reactive programming is a programming paradigm that focuses on how values are computed in terms of each other to allow a change to one value to automatically propagate to other values, like in a spreadsheet.

Implement a basic reactive system with cells with settable values ("input" cells) and cells with values computed in terms of other cells ("compute" cells). Implement updates so that when an input value is changed, values propagate to reach a new stable system state.

In addition, compute cells should allow for registering change notification callbacks. Call a cell’s callbacks when the cell’s value in a new stable state has changed from the previous stable state.