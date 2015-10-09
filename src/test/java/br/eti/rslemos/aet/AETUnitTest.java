package br.eti.rslemos.aet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.eti.rslemos.aet.AETGrammarParser.RootContext;

public class AETUnitTest {
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
				"2 node-10"
			);
		
		AETGrammarParser parser = AETGrammarParser.getParser(source);
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
					+ ")"
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
