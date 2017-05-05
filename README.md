# YTho
YTho (*why though*) is a golfing language written as part of the Abstract Machines course at Vienna University of Technology. One statement is usually composed of a selector and an operator. The selector first selects part of the main.why.input and then the operator is applied on the selected elements.

[![CircleCI](https://circleci.com/gh/pa-pi/why/tree/master.svg?style=svg)](https://circleci.com/gh/pa-pi/why/tree/master)

## Special characters

## Selectors:
* **e**
  * Selects even indices
* **o**
  * Selects odd indices
* **E**
  * Selects even integers
* **O**
  * Selects odd integers
* **0**
  * Selects all elements
* sn
  * s turns on strict mode for the following selector
  * n is a number 1-9 inclusive
  * selects the n'th element
* [1-9]
  * selects every n'th element
* **n**
  * negative integers
* **p**
  * positive integers
* !x
  * ! turns on "all but" mode for the following selector
  * x can be any selector
* ln
  * n is a number 1-9 inclusive
  * selects elements with length shorter than n
* Ln
  * n is a number 1-9 inclusive
  * selects elements with length longer than n
  
## Operators:
* **S**
  * Squares the selected elements if they are integers
* **I**
  * Increments the selected elements if they are integers
* **P**
  * replaces the selected strings with a truthy (1) value if they are a palindrome, falsy otherwise
* **U**
  * Converts the selected elements to uppercase if they are strings
* **l**
  * Converts the selected elements to lowercase if they are strings
* C
  * Changes the case of each letter in the selected elements
* f
  * floor
* c
  * ceil
* **r**
  * reverse (on strings, treats integers as strings)
* ^n
  * power function
  * n is number 1-9 inclusive
* **b**
  * converts each element to its binary representation
* h
  * converts each element to its hex representation
* \+
  * add/concat
* \- 
  * subtract
* \*
  * multiply
* /
  * divide
  
To come: Atbash alphabet(Atbash cypher)
Possible changes: group commands
  
- [ ] Loops
- [ ] Syntax error checking
- [ ] Literals (Immediate mode)