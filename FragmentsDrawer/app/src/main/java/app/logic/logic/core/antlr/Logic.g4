grammar Logic;

// Shown below is a grammar for parsing a binary algebra expression
// Annotations to right of alternatives are needed for code generation
// Labels are added for Visitor pattern implementation
expr
    : LPAREN expr RPAREN             #parenthesis
    | NOT expr                       #not
    | left=expr AND right=expr       #and
    | left=expr OR right=expr        #or
    | left=expr IMPL right=expr      #implication
    | left=expr EQUAL right=expr     #equality
    | left=expr XOR right=expr       #xor
    | BOOL                           #boolean
    | VAR                            #variable
    | WS                             #whitespace
    ;

NOT: '¬' | '~';

AND: '∧' | '&';

OR: '∨' | '|';

IMPL: '⇒' | '=>';

EQUAL: '≡' | '<=>';

XOR: '⊕';

LPAREN: '(';

RPAREN: ')';

VAR: ('a' .. 'z' | 'A' .. 'Z')+ ('0' .. '9')*;

BOOL: DIGIT | BOOLEAN;

DIGIT: '0' | '1';

BOOLEAN: '$true' | '$false';

WS: [ \r\n\t] + -> skip;
