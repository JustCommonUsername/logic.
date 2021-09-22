lexer grammar LogicTokens;

NOT: '¬';

AND: '∧';

OR: '∨';

IMPL: '⇒';

EQUAL: '≡';

XOR: '⊕';

LPAREN: '(';

RPAREN: ')';

VAR: ('a' .. 'z' | 'A' .. 'Z')+ ('0' .. '9')+;

BOOL: DIGIT | BOOLEAN;

DIGIT: '0' | '1';

BOOLEAN: '$true' | '$false';

WS: [ \r\n\t] + -> skip;
