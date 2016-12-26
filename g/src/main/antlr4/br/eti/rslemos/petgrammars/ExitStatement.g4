grammar ExitStatement;

program : statement+ '.' ;

statement : 'EXIT' ( 'PROGRAM' | 'METHOD' | 'SECTION' | 'PERFORM' 'CYCLE'? )? ;

WS : (' '|'\n')+ -> channel(HIDDEN);
