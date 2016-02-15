package br.eti.rslemos.aet;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.profile.CompilerProfiler;
import org.openjdk.jmh.profile.LinuxPerfAsmProfiler;
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
			)),
		VERYLONGTREE(AETUnitTest.join(
				"1 node-1",
				"1 node-2",
				"1 node-3",
				"1 node-4",
				"1 node-5",
				"1 node-6",
				"1 node-7",
				"1 node-8",
				"1 node-9",
				"01 node-10",
				"01 node-11",
				"01 node-12",
				"01 node-13",
				"01 node-14",
				"01 node-15",
				"01 node-16",
				"01 node-17",
				"01 node-18",
				"01 node-19",
				"01 node-20",
				"01 node-21",
				"01 node-22",
				"01 node-23",
				"01 node-24",
				"01 node-25",
				"01 node-26",
				"01 node-27",
				"01 node-28",
				"01 node-29",
				"01 node-30",
				"01 node-31",
				"01 node-32",
				"01 node-33",
				"01 node-34",
				"01 node-35",
				"01 node-36",
				"01 node-37",
				"01 node-38",
				"01 node-39",
				"01 node-40",
				"01 node-41",
				"01 node-42",
				"01 node-43",
				"01 node-44",
				"01 node-45",
				"01 node-46",
				"01 node-47",
				"01 node-48",
				"01 node-49",
				"01 node-50",
				"01 node-51",
				"01 node-52",
				"01 node-53",
				"01 node-54",
				"01 node-55",
				"01 node-56",
				"01 node-57",
				"01 node-58",
				"01 node-59",
				"01 node-60",
				"01 node-61",
				"01 node-62",
				"01 node-63",
				"01 node-64",
				"01 node-65",
				"01 node-66",
				"01 node-67",
				"01 node-68",
				"01 node-69",
				"01 node-70",
				"01 node-71",
				"01 node-72",
				"01 node-73",
				"01 node-74",
				"01 node-75",
				"01 node-76",
				"01 node-77",
				"01 node-78",
				"01 node-79",
				"01 node-80",
				"01 node-81",
				"01 node-82",
				"01 node-83",
				"01 node-84",
				"01 node-85",
				"01 node-86",
				"01 node-87",
				"01 node-88",
				"01 node-89",
				"01 node-90",
				"01 node-91",
				"01 node-92",
				"01 node-93",
				"01 node-94",
				"01 node-95",
				"01 node-96",
				"01 node-97",
				"01 node-98",
				"01 node-99",
				"001 node-100"
			)),
		VERYDEEPTREE(AETUnitTest.join(
				"1 node-1",
				"2 node-2",
				"3 node-3",
				"4 node-4",
				"5 node-5",
				"6 node-6",
				"7 node-7",
				"8 node-8",
				"9 node-9",
				"10 node-10",
				"11 node-11",
				"12 node-12",
				"13 node-13",
				"14 node-14",
				"15 node-15",
				"16 node-16",
				"17 node-17",
				"18 node-18",
				"19 node-19",
				"20 node-20",
				"21 node-21",
				"22 node-22",
				"23 node-23",
				"24 node-24",
				"25 node-25",
				"26 node-26",
				"27 node-27",
				"28 node-28",
				"29 node-29",
				"30 node-30",
				"31 node-31",
				"32 node-32",
				"33 node-33",
				"34 node-34",
				"35 node-35",
				"36 node-36",
				"37 node-37",
				"38 node-38",
				"39 node-39",
				"40 node-40",
				"41 node-41",
				"42 node-42",
				"43 node-43",
				"44 node-44",
				"45 node-45",
				"46 node-46",
				"47 node-47",
				"48 node-48",
				"49 node-49",
				"50 node-50",
				"51 node-51",
				"52 node-52",
				"53 node-53",
				"54 node-54",
				"55 node-55",
				"56 node-56",
				"57 node-57",
				"58 node-58",
				"59 node-59",
				"60 node-60",
				"61 node-61",
				"62 node-62",
				"63 node-63",
				"64 node-64",
				"65 node-65",
				"66 node-66",
				"67 node-67",
				"68 node-68",
				"69 node-69",
				"70 node-70",
				"71 node-71",
				"72 node-72",
				"73 node-73",
				"74 node-74",
				"75 node-75",
				"76 node-76",
				"77 node-77",
				"78 node-78",
				"79 node-79",
				"80 node-80",
				"81 node-81",
				"82 node-82",
				"83 node-83",
				"84 node-84",
				"85 node-85",
				"86 node-86",
				"87 node-87",
				"88 node-88",
				"89 node-89",
				"90 node-90",
				"91 node-91",
				"92 node-92",
				"93 node-93",
				"94 node-94",
				"95 node-95",
				"96 node-96",
				"97 node-97",
				"98 node-98",
				"99 node-99",
				"100 node-100"
			));
		public String text;

		private Source(String text) {
			this.text = text;
		}
	}
	
	@Param({"true", "false"})
	public boolean buildHierarchy;

	@Param({"true", "false"})
	public boolean guardExit;
	
	@Param({"true", "false"})
	public boolean guardExitShortCircuit;
	
	@Param({"TOPLEVELONLY", "DEEPTREE", "VERYDEEPTREE", "VERYLONGTREE"})
	public Source source;
	
	@Benchmark
	@Warmup(iterations = 40)
	public RootContext compile() {
		AETGrammarParser parser = AETGrammarParser.getParser(source.text);
		parser.buildHierarchy = buildHierarchy;
		parser.guardExit = guardExit;
		parser.guardExitShortCircuit = guardExitShortCircuit;
		return parser.root();
	}

	public static void main(String[] args) throws Exception {
		Options opts = new OptionsBuilder()
			.include(AETBenchmark.class.getSimpleName())
			.forks(1)
			.addProfiler(LinuxPerfAsmProfiler.class)
			.build();
		
		new Runner(opts).run();
	}
}
