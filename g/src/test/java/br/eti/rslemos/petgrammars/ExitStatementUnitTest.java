package br.eti.rslemos.petgrammars;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;

public class ExitStatementUnitTest {
	private ANTLRErrorListener listener;
	
	@Before public void before() {
		listener = mock(ANTLRErrorListener.class, "error listener");
	}
	
	@Test
	public void noViableAlternativeAtEOF() {
		String text = "EXIT";
		//String text = "OPEN DEVICE DEVICE.";
		
		ExitStatementParser parser = getParser(text);
		
		parser.addErrorListener(listener);
		ParseTree output = parser.statement();
		
		System.out.println(output.toStringTree(parser));
		
		verifyNoMoreInteractions(listener);
	}

	public static ExitStatementParser getParser(String source) {
		try {
			java.io.Reader reader = new java.io.StringReader(source);
			org.antlr.v4.runtime.CharStream input = new org.antlr.v4.runtime.ANTLRInputStream(reader);
			org.antlr.v4.runtime.TokenSource tokenSource = new ExitStatementLexer(input);
			org.antlr.v4.runtime.TokenStream tokenStream = new org.antlr.v4.runtime.CommonTokenStream(tokenSource);
			ExitStatementParser parser = new ExitStatementParser(tokenStream);

			parser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);
			parser.setTrace(true);
			parser.addErrorListener(new DiagnosticErrorListener(false));
			
			return parser;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
