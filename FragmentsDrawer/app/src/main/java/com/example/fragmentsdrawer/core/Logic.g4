parser grammar Logic;

options {
    tokenVocab = LogicTokens;
}

// Shown below is a grammar for parsing a binary algebra expression
// Annotations to right of alternatives are needed for code generation
expr
    : parenthesis
    | not
    | expr AND expr
    | expr OR expr
    | expr IMPL expr
    | expr EQUAL expr
    | expr XOR expr
    | INT
    | VAR
    | WS
    ;

parenthesis: LPAREN expr RPAREN;

not: NOT expr;
