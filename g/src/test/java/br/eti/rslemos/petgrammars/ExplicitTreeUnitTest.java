package br.eti.rslemos.petgrammars;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.junit.Test;

import br.eti.rslemos.petgrammars.ExplicitTreeParser.RootContext;

public class ExplicitTreeUnitTest {
	@Test
	public void test1() {
		String source = join(
				"1 node-0",
				"2 node-00",
				"3 node-000",
				"3 node-001",
				"3 node-002",
				"2 node-01",
				"3 node-010",
				"4 node-0100",
				"3 node-011",
				"2 node-02",
				"1 node-1",
				"2 node-10",
				"TRAILING",
				"Now come some garbage just to prove that ambiguity goes as far as input looks like a head"
			);
		
		ExplicitTreeParser parser = ExplicitTreeParser.getParser(source);
		parser.addErrorListener(new DiagnosticErrorListener(false));
		parser.addErrorListener(new ConsoleErrorListener());
		parser.buildHierarchy = true;
		parser.guardExit = true;
		RootContext root = parser.root();
		
		assertThat(root.toStringTree(parser), is(equalTo(
				"(root "
					+ "(item (head 1 node-0) "
						+ "(item (head 2 node-00) "
							+ "(item (head 3 node-000)) "
							+ "(item (head 3 node-001)) "
							+ "(item (head 3 node-002))"
						+ ") "
						+ "(item (head 2 node-01) "
							+ "(item (head 3 node-010) "
								+ "(item (head 4 node-0100))"
							+ ") "
							+ "(item (head 3 node-011))"
						+ ") "
						+ "(item (head 2 node-02))"
					+ ") "
					+ "(item (head 1 node-1) "
						+ "(item (head 2 node-10))"
					+ ") "
					+ "(trailing TRAILING Now come some garbage just to prove that ambiguity goes as far as input looks like a head)"
				+ ")"
			)));
	}

	public static String join(String... lines) {
		StringBuilder result = new StringBuilder();
		
		for (String line : lines)
			result.append(line).append("\n");
		
		result.setLength(result.length() - 1);
		
		return result.toString();
	}
}
