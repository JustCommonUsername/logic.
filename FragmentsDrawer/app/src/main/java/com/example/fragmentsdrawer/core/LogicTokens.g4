lexer grammar LogicTokens;

NOT: '¬';

AND: '∧';

OR: '∨';

IMPL: '⇒';

EQUAL: '≡';

XOR: '⊕';

LPAREN: '(';

RPAREN: ')';

INT: '0' | '1';

VAR: ('a' .. 'z' | 'A' .. 'Z') ('a' .. 'z' | 'A' .. 'Z')*;

WS: [ \r\n\t] + -> skip;
