grammar OpenDeviceStatement;

program : statement+ '.' ;

statement : 'OPEN' ( 'DEVICE' (  OPT1  |  OPT2  |  OPT3  )? )+ ;

//statement : 'OPEN' ( 'DEVICE' ( (OPT1) |  OPT2  |  OPT3  )? )+ ;
//statement : 'OPEN' ( 'DEVICE' (  OPT1  | (OPT2) |  OPT3  )? )+ ;
//statement : 'OPEN' ( 'DEVICE' (  OPT1  |  OPT2  | (OPT3) )? )+ ;
//statement : 'OPEN' ( 'DEVICE' ( (OPT1) | (OPT2) |  OPT3  )? )+ ;
//statement : 'OPEN' ( 'DEVICE' ( (OPT1) |  OPT2  | (OPT3) )? )+ ;
//statement : 'OPEN' ( 'DEVICE' (  OPT1  | (OPT2) | (OPT3) )? )+ ;
//statement : 'OPEN' ( 'DEVICE' ( (OPT1) | (OPT2) | (OPT3) )? )+ ;

OPT1 : 'OPT-1';
OPT2 : 'OPT-2';
OPT3 : 'OPT-3';

WS : (' '|'\n')+ -> channel(HIDDEN);
