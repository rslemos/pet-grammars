lexer grammar ExplicitTreeLexer;

WS : (' '|'\n') -> channel(HIDDEN);

LEVEL : [0-9]+;
NAME : [-A-Za-z0-9]+;

TRAILING : 'TRAILING';

