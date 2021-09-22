// Generated from C:/TemporaryProjects/Temporary folder, Tasks to test, Android/Tasks_to_test_on_08-02-2020/FragmentsDrawer/app/src/main/java/com/logic/fragmentsdrawer/core\LogicTokens.g4 by ANTLR 4.8
package app.logic.logic.core;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LogicTokens extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NOT=1, AND=2, OR=3, IMPL=4, EQUAL=5, XOR=6, LPAREN=7, RPAREN=8, VAR=9, 
		BOOL=10, DIGIT=11, BOOLEAN=12, WS=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"NOT", "AND", "OR", "IMPL", "EQUAL", "XOR", "LPAREN", "RPAREN", "VAR", 
			"BOOL", "DIGIT", "BOOLEAN", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\u0412\u00AC'", "'\u0432\u20AC\u00A7'", "'\u0432\u20AC\u0401'", 
			"'\u0432\u2021\u2019'", "'\u0432\u2030\u040E'", "'\u0432\u0409\u2022'", 
			"'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NOT", "AND", "OR", "IMPL", "EQUAL", "XOR", "LPAREN", "RPAREN", 
			"VAR", "BOOL", "DIGIT", "BOOLEAN", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public LogicTokens(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LogicTokens.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\17\\\b\1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\n\6\n:\n\n\r\n\16\n;\3\n\6\n?\n\n\r\n\16\n@\3\13\3\13\5\13E\n\13\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\rT\n\r\3\16\6\16"+
		"W\n\16\r\16\16\16X\3\16\3\16\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\3\2\4\4\2C\\c|\5\2\13\f\17\17\"\"\2`\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\3\35\3\2\2\2\5 \3\2\2\2\7$\3\2\2\2\t(\3\2\2\2\13"+
		",\3\2\2\2\r\60\3\2\2\2\17\64\3\2\2\2\21\66\3\2\2\2\239\3\2\2\2\25D\3\2"+
		"\2\2\27F\3\2\2\2\31S\3\2\2\2\33V\3\2\2\2\35\36\7\u0414\2\2\36\37\7\u00ae"+
		"\2\2\37\4\3\2\2\2 !\7\u0434\2\2!\"\7\u20ae\2\2\"#\7\u00a9\2\2#\6\3\2\2"+
		"\2$%\7\u0434\2\2%&\7\u20ae\2\2&\'\7\u0403\2\2\'\b\3\2\2\2()\7\u0434\2"+
		"\2)*\7\u2023\2\2*+\7\u201b\2\2+\n\3\2\2\2,-\7\u0434\2\2-.\7\u2032\2\2"+
		"./\7\u0410\2\2/\f\3\2\2\2\60\61\7\u0434\2\2\61\62\7\u040b\2\2\62\63\7"+
		"\u2024\2\2\63\16\3\2\2\2\64\65\7*\2\2\65\20\3\2\2\2\66\67\7+\2\2\67\22"+
		"\3\2\2\28:\t\2\2\298\3\2\2\2:;\3\2\2\2;9\3\2\2\2;<\3\2\2\2<>\3\2\2\2="+
		"?\4\62;\2>=\3\2\2\2?@\3\2\2\2@>\3\2\2\2@A\3\2\2\2A\24\3\2\2\2BE\5\27\f"+
		"\2CE\5\31\r\2DB\3\2\2\2DC\3\2\2\2E\26\3\2\2\2FG\4\62\63\2G\30\3\2\2\2"+
		"HI\7&\2\2IJ\7v\2\2JK\7t\2\2KL\7w\2\2LT\7g\2\2MN\7&\2\2NO\7h\2\2OP\7c\2"+
		"\2PQ\7n\2\2QR\7u\2\2RT\7g\2\2SH\3\2\2\2SM\3\2\2\2T\32\3\2\2\2UW\t\3\2"+
		"\2VU\3\2\2\2WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z[\b\16\2\2[\34\3"+
		"\2\2\2\b\2;@DSX\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}