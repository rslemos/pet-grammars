grammar AETGrammar;

@parser::members {
	public boolean buildHierarchy = true;
	public boolean guardExit = false;
	public boolean guardExitShortCircuit = false;
	
	public static AETGrammarParser getParser(String source) {
		try {
			java.io.Reader reader = new java.io.StringReader(source);
			org.antlr.v4.runtime.CharStream input = new org.antlr.v4.runtime.ANTLRInputStream(reader);
			org.antlr.v4.runtime.TokenSource tokenSource = new AETGrammarLexer(input);
			org.antlr.v4.runtime.TokenStream tokenStream = new org.antlr.v4.runtime.CommonTokenStream(tokenSource);
			AETGrammarParser parser = new AETGrammarParser(tokenStream);
			return parser;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public int nextTokenAsIntegerOr(int def) {
		try {
			return Integer.parseInt(getCurrentToken().getText());
		} catch (NumberFormatException e) {
			return def;
		}
	}
}

WS : (' '|'\n') -> channel(HIDDEN);

LEVEL : [0-9]+;
NAME : [-A-Za-z0-9]+;

root: item* trailing?;	

trailing: 'TRAILING' (LEVEL | NAME)*;

item:
	head
    (
        { buildHierarchy && nextTokenAsIntegerOr(-1) > $head.level }?
        item
    )*
    { !guardExitShortCircuit || !(buildHierarchy && nextTokenAsIntegerOr(-1) > $head.level) || !guardExit }?
    ;

head returns[int level]:
    LEVEL { $level = $LEVEL.int; }
    NAME
	;
