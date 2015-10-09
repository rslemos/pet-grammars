package br.eti.rslemos.aet;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import br.eti.rslemos.aet.AETGrammarParser.RootContext;

@State(Scope.Benchmark)
public class AETBenchmark {

	public enum Source {
		TOPLEVELONLY(AETUnitTest.join(
				"1 node-0",
				"1 node-00",
				"1 node-000",
				"1 node-001",
				"1 node-002",
				"1 node-01",
				"1 node-010",
				"1 node-0100",
				"1 node-011",
				"1 node-02",
				"1 node-1",
				"1 node-10"
			)),
		DEEPTREE(AETUnitTest.join(
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
			));
		
		public String text;

		private Source(String text) {
			this.text = text;
		}
	}
	
	@Param({"true", "false"})
	public boolean buildHierarchy;
	
	@Param({"TOPLEVELONLY", "DEEPTREE"})
	public Source source;
	
	@Benchmark
	public RootContext compile() {
		AETGrammarParser parser = AETGrammarParser.getParser(source.text);
		parser.buildHierarchy = buildHierarchy;
		return parser.root();
	}

	public static void main(String[] args) throws Exception {
		Options opts = new OptionsBuilder()
			.include(AETBenchmark.class.getSimpleName())
			.forks(1)
			.build();
		
		new Runner(opts).run();
	}
}
