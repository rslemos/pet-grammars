package br.eti.rslemos.petgrammars;

import static br.eti.rslemos.petgrammars.ExplicitTreeUnitTest.join;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.junit.Before;
import org.junit.Test;

import br.eti.rslemos.petgrammars.ReportAmbiguityParser.CallStatementContext;

public class ReportAmbiguityUnitTest {
	private ANTLRErrorListener listener;
	
	@Before public void before() {
		listener = mock(ANTLRErrorListener.class, "error listener");
	}
	
	@Test
	public void testAmbiguityOnArgsByVal() {
		ReportAmbiguityParser parser = getParser(join("CALL FUNCTION BY VAL A B"));
				
		parser.addErrorListener(listener);
		CallStatementContext output = parser.callStatement();
		
		System.out.println(output.toStringTree(parser));

		verify(listener).syntaxError(
				same(parser), 
				any(), 
				eq(1), 
				eq(24), 
				matches("reportAttemptingFullContext d=[0-9]+ \\(argsByVal\\), input='B'"), 
				(RecognitionException)isNull()
			);
	
		verify(listener).syntaxError(
				same(parser), 
				any(), 
				eq(1), 
				eq(24), 
				matches("reportAmbiguity d=[0-9]+ \\(argsByVal\\): ambigAlts=\\{1, 2\\}, input='B'"), 
				(RecognitionException)isNull()
			);
	}

	public static ReportAmbiguityParser getParser(String source) {
		try {
			java.io.Reader reader = new java.io.StringReader(source);
			org.antlr.v4.runtime.CharStream input = new org.antlr.v4.runtime.ANTLRInputStream(reader);
			org.antlr.v4.runtime.TokenSource tokenSource = new ReportAmbiguityLexer(input);
			org.antlr.v4.runtime.TokenStream tokenStream = new org.antlr.v4.runtime.CommonTokenStream(tokenSource);
			ReportAmbiguityParser parser = new ReportAmbiguityParser(tokenStream);

			parser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);
			parser.setTrace(true);			
			parser.addErrorListener(new DiagnosticErrorListener(false));
			
			return parser;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
