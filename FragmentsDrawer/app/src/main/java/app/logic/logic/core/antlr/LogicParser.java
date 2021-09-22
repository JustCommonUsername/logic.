// Generated from Logic.g4 by ANTLR 4.8
package app.logic.logic.core.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LogicParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NOT=1, AND=2, OR=3, IMPL=4, EQUAL=5, XOR=6, LPAREN=7, RPAREN=8, VAR=9, 
		BOOL=10, DIGIT=11, BOOLEAN=12, WS=13;
	public static final int
		RULE_expr = 0;
	private static String[] makeRuleNames() {
		return new String[] {
			"expr"
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

	@Override
	public String getGrammarFileName() { return "Logic.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LogicParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NotContext extends ExprContext {
		public TerminalNode NOT() { return getToken(LogicParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanContext extends ExprContext {
		public TerminalNode BOOL() { return getToken(LogicParser.BOOL, 0); }
		public BooleanContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitBoolean(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode OR() { return getToken(LogicParser.OR, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OrContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode AND() { return getToken(LogicParser.AND, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AndContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ImplicationContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode IMPL() { return getToken(LogicParser.IMPL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ImplicationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterImplication(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitImplication(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitImplication(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VariableContext extends ExprContext {
		public TerminalNode VAR() { return getToken(LogicParser.VAR, 0); }
		public VariableContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XorContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode XOR() { return getToken(LogicParser.XOR, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public XorContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterXor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitXor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitXor(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhitespaceContext extends ExprContext {
		public TerminalNode WS() { return getToken(LogicParser.WS, 0); }
		public WhitespaceContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterWhitespace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitWhitespace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitWhitespace(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesisContext extends ExprContext {
		public TerminalNode LPAREN() { return getToken(LogicParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LogicParser.RPAREN, 0); }
		public ParenthesisContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitParenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitParenthesis(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualityContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode EQUAL() { return getToken(LogicParser.EQUAL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public EqualityContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).enterEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicListener ) ((LogicListener)listener).exitEquality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicVisitor ) return ((LogicVisitor<? extends T>)visitor).visitEquality(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				_localctx = new ParenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(3);
				match(LPAREN);
				setState(4);
				expr(0);
				setState(5);
				match(RPAREN);
				}
				break;
			case NOT:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(7);
				match(NOT);
				setState(8);
				expr(9);
				}
				break;
			case BOOL:
				{
				_localctx = new BooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(9);
				match(BOOL);
				}
				break;
			case VAR:
				{
				_localctx = new VariableContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(10);
				match(VAR);
				}
				break;
			case WS:
				{
				_localctx = new WhitespaceContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(11);
				match(WS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(31);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(29);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new AndContext(new ExprContext(_parentctx, _parentState));
						((AndContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(14);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(15);
						match(AND);
						setState(16);
						((AndContext)_localctx).right = expr(9);
						}
						break;
					case 2:
						{
						_localctx = new OrContext(new ExprContext(_parentctx, _parentState));
						((OrContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(17);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(18);
						match(OR);
						setState(19);
						((OrContext)_localctx).right = expr(8);
						}
						break;
					case 3:
						{
						_localctx = new ImplicationContext(new ExprContext(_parentctx, _parentState));
						((ImplicationContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(20);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(21);
						match(IMPL);
						setState(22);
						((ImplicationContext)_localctx).right = expr(7);
						}
						break;
					case 4:
						{
						_localctx = new EqualityContext(new ExprContext(_parentctx, _parentState));
						((EqualityContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(23);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(24);
						match(EQUAL);
						setState(25);
						((EqualityContext)_localctx).right = expr(6);
						}
						break;
					case 5:
						{
						_localctx = new XorContext(new ExprContext(_parentctx, _parentState));
						((XorContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(26);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(27);
						match(XOR);
						setState(28);
						((XorContext)_localctx).right = expr(5);
						}
						break;
					}
					} 
				}
				setState(33);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\17%\4\2\t\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\17\n\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2 \n\2\f\2\16\2#\13\2\3\2\2\3\2"+
		"\3\2\2\2\2,\2\16\3\2\2\2\4\5\b\2\1\2\5\6\7\t\2\2\6\7\5\2\2\2\7\b\7\n\2"+
		"\2\b\17\3\2\2\2\t\n\7\3\2\2\n\17\5\2\2\13\13\17\7\f\2\2\f\17\7\13\2\2"+
		"\r\17\7\17\2\2\16\4\3\2\2\2\16\t\3\2\2\2\16\13\3\2\2\2\16\f\3\2\2\2\16"+
		"\r\3\2\2\2\17!\3\2\2\2\20\21\f\n\2\2\21\22\7\4\2\2\22 \5\2\2\13\23\24"+
		"\f\t\2\2\24\25\7\5\2\2\25 \5\2\2\n\26\27\f\b\2\2\27\30\7\6\2\2\30 \5\2"+
		"\2\t\31\32\f\7\2\2\32\33\7\7\2\2\33 \5\2\2\b\34\35\f\6\2\2\35\36\7\b\2"+
		"\2\36 \5\2\2\7\37\20\3\2\2\2\37\23\3\2\2\2\37\26\3\2\2\2\37\31\3\2\2\2"+
		"\37\34\3\2\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\3\3\2\2\2#!\3\2\2\2"+
		"\5\16\37!";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}