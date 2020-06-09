grammar Binary;

// Shown below is a grammar for parsing a binary algebra expression
// Annotations to right of alternatives are needed for code generation
expr
    : expr XOR expr        #XOR
    | LPAREN expr RPAREN   #PARENTHESIS
    | NOT expr             #NOT
    | expr AND expr        #AND
    | expr OR expr         #OR
    | expr IMP expr        #IMPLICATION
    | expr EQ expr         #EQUAL
    | INT                  #INTEGER
    | VAR                  #VARIABLE
    | WS                   #WHITESPACE
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