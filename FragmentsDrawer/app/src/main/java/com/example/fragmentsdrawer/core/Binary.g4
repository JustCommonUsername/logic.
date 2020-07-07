grammar Binary;

@header {
    package com.example.fragmentsdrawer.core;
}

// Shown below is a grammar for parsing a binary algebra expression
// Annotations to right of alternatives are needed for code generation
expr
    : expr EOF             #EOF
    | LPAREN expr RPAREN   #ParExpr
    | NOT expr             #NotBlock
    | expr AND expr        #AndBlock
    | expr OR expr         #OrBlock
    | expr IMP expr        #ImplBlock
    | expr EQ expr         #EqualBlock
    | expr XOR expr        #XorBlock
    | INT                  #Int
    | VAR                  #Var
    | WS                   #WS
    ;

NOT: '¬';

AND: '∧';

OR: '∨';

IMP: '⇒';

EQ: '≡';

XOR: '⊕';

LPAREN: '(';

RPAREN: ')';

INT: '0' | '1';

VAR: ('a' .. 'z' | 'A' .. 'Z') ('a' .. 'z' | 'A' .. 'Z')*;

WS: [ \r\n\t] + -> skip;