grammar ReportAmbiguity;

unit : statements*;

statements :
		callStatement+
		// '.'
	;

callStatement : 'CALL' ID (argsByRef | argsByVal)*;

argsByRef : ('BY' 'REF')? ID+;

argsByVal : 'BY' 'VAL' ID+;

ID : ('A'..'Z')+;

WS : (' '|'\n')+ -> channel(HIDDEN);

