// Generated from Logic.g4 by ANTLR 4.8
package app.logic.logic.core.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LogicLexer extends Lexer {
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
			null, null, null, null, null, null, "'\u2295'", "'('", "')'"
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


	public LogicLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Logic.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\17Y\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\5\5"+
		"\'\n\5\3\6\3\6\3\6\3\6\5\6-\n\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\6\n\66\n\n"+
		"\r\n\16\n\67\3\n\7\n;\n\n\f\n\16\n>\13\n\3\13\3\13\5\13B\n\13\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\rQ\n\r\3\16\6\16T\n\16"+
		"\r\16\16\16U\3\16\3\16\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\3\2\7\4\2\u0080\u0080\u00ae\u00ae\4\2((\u2229\u2229"+
		"\4\2~~\u222a\u222a\4\2C\\c|\5\2\13\f\17\17\"\"\2_\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\3\35\3\2\2\2\5\37\3\2\2\2\7!\3\2\2\2\t&\3\2\2\2\13,\3\2\2\2\r.\3"+
		"\2\2\2\17\60\3\2\2\2\21\62\3\2\2\2\23\65\3\2\2\2\25A\3\2\2\2\27C\3\2\2"+
		"\2\31P\3\2\2\2\33S\3\2\2\2\35\36\t\2\2\2\36\4\3\2\2\2\37 \t\3\2\2 \6\3"+
		"\2\2\2!\"\t\4\2\2\"\b\3\2\2\2#\'\7\u21d4\2\2$%\7?\2\2%\'\7@\2\2&#\3\2"+
		"\2\2&$\3\2\2\2\'\n\3\2\2\2(-\7\u2263\2\2)*\7>\2\2*+\7?\2\2+-\7@\2\2,("+
		"\3\2\2\2,)\3\2\2\2-\f\3\2\2\2./\7\u2297\2\2/\16\3\2\2\2\60\61\7*\2\2\61"+
		"\20\3\2\2\2\62\63\7+\2\2\63\22\3\2\2\2\64\66\t\5\2\2\65\64\3\2\2\2\66"+
		"\67\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28<\3\2\2\29;\4\62;\2:9\3\2\2\2;>"+
		"\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\24\3\2\2\2><\3\2\2\2?B\5\27\f\2@B\5\31\r"+
		"\2A?\3\2\2\2A@\3\2\2\2B\26\3\2\2\2CD\4\62\63\2D\30\3\2\2\2EF\7&\2\2FG"+
		"\7v\2\2GH\7t\2\2HI\7w\2\2IQ\7g\2\2JK\7&\2\2KL\7h\2\2LM\7c\2\2MN\7n\2\2"+
		"NO\7u\2\2OQ\7g\2\2PE\3\2\2\2PJ\3\2\2\2Q\32\3\2\2\2RT\t\6\2\2SR\3\2\2\2"+
		"TU\3\2\2\2US\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\b\16\2\2X\34\3\2\2\2\n\2&,"+
		"\67<APU\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}