package ByName;

import java.util.regex.Pattern;

public class RegConst {
	static String[] antlr = { "Lexer$", "Parser$", "Listener$", "BaseListener$" };
	static Pattern[] antlrp = { Pattern.compile(antlr[0]),
			Pattern.compile(antlr[1]), Pattern.compile(antlr[2]),
			Pattern.compile(antlr[3]) };
	static String[] javacc = { "JJT.*State", "Constants$", "^Node$",
			"^ParseException$", "^SimpleCharStream$", "^SimpleNode$",
			"^Token$", "^TokenMgrError$", "^ASTPerl$", "^ASTPython$",
			".*TreeConstants", ".*Visitor" };
	static Pattern[] javaccp = { Pattern.compile(javacc[0]),
			Pattern.compile(javacc[1]), Pattern.compile(javacc[2]),
			Pattern.compile(javacc[3]), Pattern.compile(javacc[4]),
			Pattern.compile(javacc[5]), Pattern.compile(javacc[6]),
			Pattern.compile(javacc[7]), Pattern.compile(javacc[8]),
			Pattern.compile(javacc[9]), Pattern.compile(javacc[10]),
			Pattern.compile(javacc[11]) };
	static String jflex = "Yylex";
	static Pattern jflexp = Pattern.compile(jflex);
	static String[] sablecc = { "^Lexer$", "^LexerException$", "^Parser$",
			"^ParserException$", "^DepthFirstAdapter$", "^Analysis$",
			"^AnalysisAdapter$", "^Switch$", "^Switchable$", "T[A-Z][a-z]*",
			"^Token$", "A[A-Z][a-z]*", "^Start$", "^Node$", "^State$" };
	static Pattern[] sableccp = { Pattern.compile(sablecc[0]),
			Pattern.compile(sablecc[1]), Pattern.compile(sablecc[2]),
			Pattern.compile(sablecc[3]), Pattern.compile(sablecc[4]),
			Pattern.compile(sablecc[5]), Pattern.compile(sablecc[6]),
			Pattern.compile(sablecc[7]), Pattern.compile(sablecc[8]),
			Pattern.compile(sablecc[9]), Pattern.compile(sablecc[10]),
			Pattern.compile(sablecc[11]), Pattern.compile(sablecc[12]),
			Pattern.compile(sablecc[13]), Pattern.compile(sablecc[14]) };
}
