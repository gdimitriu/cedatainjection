# cedatainjection
Data injection using mathematical parser with complex expressions.

The parser is a modified version of the parser from Schild "C the Complete Reference" Copyright 1995 McGraw-Hill Cook Company International.

This parser is also a translation from C++ of my old parser_functions from https://github.com/gdimitriu/parser_functions .

This will evolve as the devs_simulator project for usage as processor operations.

This mathematical parser supports:

- scalar variable
- multidimensional variable (in this moment the operations are using only one variable from the multidimensional like a[1] and not a)
- all functions with one double argument from java.lang.Math. 
- support of operations : +-*/%^ and unary -
- support of parentheses
- memory variables but this could be enhanced to support persistence.
- support for persistence of parsing (interruption of the evaluation) (not implemented yet)
- vector and matrix operations (not supported yet).