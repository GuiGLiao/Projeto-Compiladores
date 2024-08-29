// Generated from IsiLanguage.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class IsiLanguageParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		OP=18, OP_AT=19, OPREL=20, ID=21, NUM=22, VIRG=23, PV=24, AP=25, FP=26, 
		ACH=27, FCH=28, DP=29, TEXTO=30, WS=31;
	public static final int
		RULE_programa = 0, RULE_declaravar = 1, RULE_bloco = 2, RULE_comando = 3, 
		RULE_cmdIF = 4, RULE_cmdAttrib = 5, RULE_cmdLeitura = 6, RULE_cmdEscrita = 7, 
		RULE_cmdWhile = 8, RULE_cmdExecuteFor = 9, RULE_expr = 10, RULE_termo = 11, 
		RULE_exprl = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declaravar", "bloco", "comando", "cmdIF", "cmdAttrib", "cmdLeitura", 
			"cmdEscrita", "cmdWhile", "cmdExecuteFor", "expr", "termo", "exprl"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'inicio'", "'fim'", "'fimprog'", "'declare'", "'number'", 
			"'text'", "'real'", "'se'", "'entao'", "'senao'", "'fimse'", "'leia'", 
			"'escreva'", "'enquanto'", "'execute'", "'ate'", null, "':='", null, 
			null, null, "','", "';'", "'('", "')'", "'{'", "'}'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "OP", "OP_AT", "OPREL", "ID", "NUM", 
			"VIRG", "PV", "AP", "FP", "ACH", "FCH", "DP", "TEXTO", "WS"
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
	public String getGrammarFileName() { return "IsiLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private HashMap<String,Var> symbolTable = new HashMap<String, Var>();
	    private ArrayList<Var> currentDecl = new ArrayList<Var>();
	    private Types currentType;
	    private Types leftType=null, rightType=null;
	    private Program program = new Program();
	    private String strExpr = "";
	    private IfCommand currentIfCommand;
	    private String exprId;
	    private String exprCondition;
	    private ArrayList<Command> listaTrue;
	    private ArrayList<Command> curThread;
	    private ArrayList<Command> listaComandos;
	    private String exprForInit;
	    private String exprForCondition;
	    private String exprForDo;
	    private String exprExecuteFor;
	    
	    private Stack<ArrayList<Command>> stack = new Stack<ArrayList<Command>>();
	    
	    
	    public void updateType(){
	    	for(Var v: currentDecl){
	    	   v.setType(currentType);
	    	   symbolTable.put(v.getId(), v);
	    	}
	    }
	    public void exibirVar(){
	        for (String id: symbolTable.keySet()){
	        	System.out.println(symbolTable.get(id));
	        }
	    }
	    
	    public Program getProgram(){
	    	return this.program;
	    	}
	    
	    public boolean isDeclared(String id){
	    	return symbolTable.get(id) != null;
	    }
	    
	      public StringBuilder variableNotUsed(){
	         StringBuilder sb = new StringBuilder();
	         for (String id: symbolTable.keySet()){
	            if (!symbolTable.get(id).isInitialized()){
	                  sb.append("Variable " + id + "\n");
	            }
	         }
	         return sb;
	      }

	      public void Warnings(){
	         StringBuilder warning = new StringBuilder();
	         StringBuilder varUnused = variableNotUsed();
	         if (varUnused.length() > 0){
	            warning.append("!!!Warning!!! Variable declared but not used:" + varUnused);
	         }
	         if (warning.length() > 0){
	            System.out.println(warning);
	         }
	      }

	public IsiLanguageParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(T__0);
			setState(27);
			match(ID);
			 program.setName(_input.LT(-1).getText());
			                               stack.push(new ArrayList<Command>()); 
			                             
			setState(30); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(29);
				declaravar();
				}
				}
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__4 );
			setState(34);
			match(T__1);
			setState(36); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(35);
				comando();
				}
				}
				setState(38); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2220544L) != 0) );
			setState(40);
			match(T__2);
			setState(41);
			match(T__3);

			                  program.setSymbolTable(symbolTable);
			                  program.setCommandList(stack.pop());
			               
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaravarContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(IsiLanguageParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLanguageParser.ID, i);
		}
		public TerminalNode DP() { return getToken(IsiLanguageParser.DP, 0); }
		public TerminalNode PV() { return getToken(IsiLanguageParser.PV, 0); }
		public List<TerminalNode> VIRG() { return getTokens(IsiLanguageParser.VIRG); }
		public TerminalNode VIRG(int i) {
			return getToken(IsiLanguageParser.VIRG, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(T__4);
			 currentDecl.clear(); 
			setState(46);
			match(ID);
			 if (isDeclared(_input.LT(-1).getText())) {
			                        throw new IsiSemanticException("Variable already declared: " + _input.LT(-1).getText());

			               }
			                  currentDecl.add(new Var(_input.LT(-1).getText()));
			                  
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(48);
				match(VIRG);
				setState(49);
				match(ID);
				 if (isDeclared(_input.LT(-1).getText())) {
				                        throw new IsiSemanticException("Variable already declared: " + _input.LT(-1).getText());
				                     }
				                     currentDecl.add(new Var(_input.LT(-1).getText()));
				                     
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			match(DP);
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(57);
				match(T__5);
				currentType = Types.NUMBER;
				}
				break;
			case T__6:
				{
				setState(59);
				match(T__6);
				currentType = Types.TEXT;
				}
				break;
			case T__7:
				{
				setState(61);
				match(T__7);
				currentType = Types.REALNUMBER;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 updateType(); 
			setState(66);
			match(PV);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlocoContext extends ParserRuleContext {
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 curThread = new ArrayList<Command>(); 
				        stack.push(curThread);  
			          
			setState(70); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(69);
				comando();
				}
				}
				setState(72); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2220544L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComandoContext extends ParserRuleContext {
		public CmdAttribContext cmdAttrib() {
			return getRuleContext(CmdAttribContext.class,0);
		}
		public CmdLeituraContext cmdLeitura() {
			return getRuleContext(CmdLeituraContext.class,0);
		}
		public CmdEscritaContext cmdEscrita() {
			return getRuleContext(CmdEscritaContext.class,0);
		}
		public CmdIFContext cmdIF() {
			return getRuleContext(CmdIFContext.class,0);
		}
		public CmdWhileContext cmdWhile() {
			return getRuleContext(CmdWhileContext.class,0);
		}
		public CmdExecuteForContext cmdExecuteFor() {
			return getRuleContext(CmdExecuteForContext.class,0);
		}
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitComando(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_comando);
		try {
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				cmdAttrib();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				cmdLeitura();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 3);
				{
				setState(76);
				cmdEscrita();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(77);
				cmdIF();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 5);
				{
				setState(78);
				cmdWhile();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 6);
				{
				setState(79);
				cmdExecuteFor();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdIFContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdIFContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdIF; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdIF(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdIF(this);
		}
	}

	public final CmdIFContext cmdIF() throws RecognitionException {
		CmdIFContext _localctx = new CmdIFContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cmdIF);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__8);
			 stack.push(new ArrayList<Command>());
			                      strExpr = "";
			                      currentIfCommand = new IfCommand();
			                    
			setState(84);
			match(AP);
			setState(85);
			expr();
			setState(86);
			match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(88);
			expr();
			setState(89);
			match(FP);
			 currentIfCommand.setExpression(strExpr); 
			setState(91);
			match(T__9);
			setState(93); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(92);
				comando();
				}
				}
				setState(95); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2220544L) != 0) );
			 
			                  currentIfCommand.setTrueList(stack.pop());                            
			               
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(98);
				match(T__10);
				 stack.push(new ArrayList<Command>()); 
				setState(101); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(100);
					comando();
					}
					}
					setState(103); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2220544L) != 0) );

				                   currentIfCommand.setFalseList(stack.pop());
				                 
				}
			}

			setState(109);
			match(T__11);

			               	   stack.peek().add(currentIfCommand);
			               
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdAttribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode OP_AT() { return getToken(IsiLanguageParser.OP_AT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PV() { return getToken(IsiLanguageParser.PV, 0); }
		public CmdAttribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdAttrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdAttrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdAttrib(this);
		}
	}

	public final CmdAttribContext cmdAttrib() throws RecognitionException {
		CmdAttribContext _localctx = new CmdAttribContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmdAttrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(ID);
			 
			               // Verifica se a variável foi declarada
			               String varName = _input.LT(-1).getText();
			               if (!isDeclared(varName)) {
			                  throw new IsiSemanticException("Undeclared Variable: " + varName);
			               }
			               // Marca a variável como inicializada
			               symbolTable.get(varName).setInitialized(true);
			               leftType = symbolTable.get(varName).getType();
			            
			setState(114);
			match(OP_AT);
			strExpr = "";
			setState(116);
			expr();
			setState(117);
			match(PV);

			               // Verifica se os tipos são compatíveis
			               if (leftType.getValue() < rightType.getValue()) {
			                  throw new IsiSemanticException("Unmatched Types: " + leftType + " and " + rightType);
			               }

			               if (strExpr =="")
			               {
			                  throw new IsiSemanticException("Variável "+ varName +" não foi atribuída");
			               }
			               Command cmdAttrib = new AttribCommand(varName, strExpr);
			               stack.peek().add(cmdAttrib);
			               //System.out.println("DEBUG - " + cmdAttrib);
			            
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdLeituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public TerminalNode PV() { return getToken(IsiLanguageParser.PV, 0); }
		public CmdLeituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdLeitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdLeitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdLeitura(this);
		}
	}

	public final CmdLeituraContext cmdLeitura() throws RecognitionException {
		CmdLeituraContext _localctx = new CmdLeituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdLeitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(T__12);
			setState(121);
			match(AP);
			setState(122);
			match(ID);
			 if (!isDeclared(_input.LT(-1).getText())) {
			                       throw new IsiSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
			                    }
			                    symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
			                    Command cmdRead = new ReadCommand(symbolTable.get(_input.LT(-1).getText()));
			                    stack.peek().add(cmdRead);
			                    //System.out.println("DEBUG - " + _input.LT(-1).getText());   
			                  
			setState(124);
			match(FP);
			setState(125);
			match(PV);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEscritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public TerminalNode PV() { return getToken(IsiLanguageParser.PV, 0); }
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public CmdEscritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEscrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdEscrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdEscrita(this);
		}
	}

	public final CmdEscritaContext cmdEscrita() throws RecognitionException {
		CmdEscritaContext _localctx = new CmdEscritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdEscrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(T__13);
			setState(128);
			match(AP);
			{
			setState(129);
			termo();
			 Command cmdWrite = new WriteCommand(_input.LT(-1).getText());
			                         stack.peek().add(cmdWrite);
			                       
			}
			setState(132);
			match(FP);
			setState(133);
			match(PV);
			 rightType = null;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdWhileContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLanguageParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLanguageParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLanguageParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLanguageParser.FCH, 0); }
		public TerminalNode NUM() { return getToken(IsiLanguageParser.NUM, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdWhile(this);
		}
	}

	public final CmdWhileContext cmdWhile() throws RecognitionException {
		CmdWhileContext _localctx = new CmdWhileContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdWhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__14);
			setState(137);
			match(AP);
			setState(138);
			match(ID);
			exprCondition = _input.LT(-1).getText(); 
			setState(140);
			match(OPREL);
			 exprCondition += _input.LT(-1).getText(); 
			setState(142);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUM) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			exprCondition += _input.LT(-1).getText(); 
			setState(144);
			match(FP);
			setState(145);
			match(ACH);

			                        curThread = new ArrayList<Command>(); 
			                        stack.push(curThread);
			                     
			setState(148); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(147);
				comando();
				}
				}
				setState(150); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2220544L) != 0) );
			setState(152);
			match(FCH);

			                     listaTrue = stack.pop();	
			                     Command cmdWhile = new WhileCommand(exprCondition, listaTrue);
			                   	stack.peek().add(cmdWhile);
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdExecuteForContext extends ParserRuleContext {
		public TerminalNode ACH() { return getToken(IsiLanguageParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLanguageParser.FCH, 0); }
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLanguageParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLanguageParser.ID, i);
		}
		public List<TerminalNode> NUM() { return getTokens(IsiLanguageParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(IsiLanguageParser.NUM, i);
		}
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdExecuteForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdExecuteFor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdExecuteFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdExecuteFor(this);
		}
	}

	public final CmdExecuteForContext cmdExecuteFor() throws RecognitionException {
		CmdExecuteForContext _localctx = new CmdExecuteForContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdExecuteFor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(T__15);
			setState(156);
			match(ACH);
			 curThread = new ArrayList<Command>(); 
			                    stack.push(curThread);
			                  
			setState(159); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(158);
				comando();
				}
				}
				setState(161); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2220544L) != 0) );
			setState(163);
			match(FCH);
			setState(164);
			match(T__16);
			setState(165);
			match(AP);
			setState(166);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUM) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			exprExecuteFor = _input.LT(-1).getText(); 
			setState(168);
			match(OPREL);
			 exprExecuteFor += _input.LT(-1).getText(); 
			setState(170);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUM) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			String var = _input.LT(-1).getText(); 
			                  exprExecuteFor += var;
			                  
			setState(172);
			match(FP);

			                     listaComandos = stack.pop();
			                     Command cmdExecuteFor = new ExecuteForCommand(exprExecuteFor, listaComandos);
			                     stack.peek().add(cmdExecuteFor);
			                  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public ExprlContext exprl() {
			return getRuleContext(ExprlContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			termo();
			 strExpr += _input.LT(-1).getText(); 
			setState(177);
			exprl();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode NUM() { return getToken(IsiLanguageParser.NUM, 0); }
		public TerminalNode TEXTO() { return getToken(IsiLanguageParser.TEXTO, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_termo);
		try {
			setState(185);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				match(ID);
				 if (!isDeclared(_input.LT(-1).getText())) {
				                       throw new IsiSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
				                    }
				                    if (!symbolTable.get(_input.LT(-1).getText()).isInitialized()){
				                       throw new IsiSemanticException("Variable "+_input.LT(-1).getText()+" has no value assigned");
				                    }
				                    
				                    if (rightType == null){
				                       rightType = symbolTable.get(_input.LT(-1).getText()).getType();
				                       //System.out.println("Encontrei pela 1a vez uma variavel = "+rightType);
				                    }   
				                    else{
				                       if (symbolTable.get(_input.LT(-1).getText()).getType().getValue() > rightType.getValue()){
				                          rightType = symbolTable.get(_input.LT(-1).getText()).getType();
				                          //System.out.println("Ja havia tipo declarado e mudou " +_input.LT(-1).getText()+ "para = "+rightType);
				                          
				                       }
				                    }
				                  
				}
				break;
			case NUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(181);
				match(NUM);
				  if (rightType == null) {
							 				rightType = Types.NUMBER;
							 				//System.out.println("Encontrei um numero pela 1a vez "+rightType);
							            }
							            else{
							                if (rightType.getValue() < Types.NUMBER.getValue()){			                    			                   
							                	rightType = Types.NUMBER;
							                	//System.out.println("Mudei o tipo para Number = "+rightType);
							                }
							            }
							         
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 3);
				{
				setState(183);
				match(TEXTO);
				  if (rightType == null) {
							 				rightType = Types.TEXT;
							 				//System.out.println("Encontrei pela 1a vez um texto ="+ rightType);
							            }
							            else{
							                if (rightType.getValue() < Types.TEXT.getValue()){			                    
							                	rightType = Types.TEXT;
							                	//System.out.println("Mudei o tipo para TEXT = "+rightType);
							                	
							                }
							            }
							         
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprlContext extends ParserRuleContext {
		public List<TerminalNode> OP() { return getTokens(IsiLanguageParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(IsiLanguageParser.OP, i);
		}
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public ExprlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterExprl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitExprl(this);
		}
	}

	public final ExprlContext exprl() throws RecognitionException {
		ExprlContext _localctx = new ExprlContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_exprl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(187);
				match(OP);
				 strExpr += _input.LT(-1).getText(); 
				setState(189);
				termo();
				 strExpr += _input.LT(-1).getText(); 
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001f\u00c6\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0004"+
		"\u0000\u001f\b\u0000\u000b\u0000\f\u0000 \u0001\u0000\u0001\u0000\u0004"+
		"\u0000%\b\u0000\u000b\u0000\f\u0000&\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0005\u00014\b\u0001\n\u0001\f\u00017\t\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001@\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0004\u0002G\b\u0002\u000b\u0002\f\u0002H\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003Q\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0004\u0004^\b\u0004\u000b\u0004\f\u0004_\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0004\u0004f\b\u0004\u000b\u0004\f\u0004g\u0001"+
		"\u0004\u0001\u0004\u0003\u0004l\b\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0004\b\u0095\b\b\u000b\b\f\b\u0096\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0004\t\u00a0\b\t\u000b"+
		"\t\f\t\u00a1\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u00ba\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u00c1"+
		"\b\f\n\f\f\f\u00c4\t\f\u0001\f\u0000\u0000\r\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u0000\u0001\u0001\u0000\u0015"+
		"\u0016\u00cb\u0000\u001a\u0001\u0000\u0000\u0000\u0002,\u0001\u0000\u0000"+
		"\u0000\u0004D\u0001\u0000\u0000\u0000\u0006P\u0001\u0000\u0000\u0000\b"+
		"R\u0001\u0000\u0000\u0000\np\u0001\u0000\u0000\u0000\fx\u0001\u0000\u0000"+
		"\u0000\u000e\u007f\u0001\u0000\u0000\u0000\u0010\u0088\u0001\u0000\u0000"+
		"\u0000\u0012\u009b\u0001\u0000\u0000\u0000\u0014\u00af\u0001\u0000\u0000"+
		"\u0000\u0016\u00b9\u0001\u0000\u0000\u0000\u0018\u00c2\u0001\u0000\u0000"+
		"\u0000\u001a\u001b\u0005\u0001\u0000\u0000\u001b\u001c\u0005\u0015\u0000"+
		"\u0000\u001c\u001e\u0006\u0000\uffff\uffff\u0000\u001d\u001f\u0003\u0002"+
		"\u0001\u0000\u001e\u001d\u0001\u0000\u0000\u0000\u001f \u0001\u0000\u0000"+
		"\u0000 \u001e\u0001\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000!\"\u0001"+
		"\u0000\u0000\u0000\"$\u0005\u0002\u0000\u0000#%\u0003\u0006\u0003\u0000"+
		"$#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000"+
		"\u0000&\'\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000()\u0005\u0003"+
		"\u0000\u0000)*\u0005\u0004\u0000\u0000*+\u0006\u0000\uffff\uffff\u0000"+
		"+\u0001\u0001\u0000\u0000\u0000,-\u0005\u0005\u0000\u0000-.\u0006\u0001"+
		"\uffff\uffff\u0000./\u0005\u0015\u0000\u0000/5\u0006\u0001\uffff\uffff"+
		"\u000001\u0005\u0017\u0000\u000012\u0005\u0015\u0000\u000024\u0006\u0001"+
		"\uffff\uffff\u000030\u0001\u0000\u0000\u000047\u0001\u0000\u0000\u0000"+
		"53\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u000068\u0001\u0000\u0000"+
		"\u000075\u0001\u0000\u0000\u00008?\u0005\u001d\u0000\u00009:\u0005\u0006"+
		"\u0000\u0000:@\u0006\u0001\uffff\uffff\u0000;<\u0005\u0007\u0000\u0000"+
		"<@\u0006\u0001\uffff\uffff\u0000=>\u0005\b\u0000\u0000>@\u0006\u0001\uffff"+
		"\uffff\u0000?9\u0001\u0000\u0000\u0000?;\u0001\u0000\u0000\u0000?=\u0001"+
		"\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000AB\u0006\u0001\uffff\uffff"+
		"\u0000BC\u0005\u0018\u0000\u0000C\u0003\u0001\u0000\u0000\u0000DF\u0006"+
		"\u0002\uffff\uffff\u0000EG\u0003\u0006\u0003\u0000FE\u0001\u0000\u0000"+
		"\u0000GH\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000HI\u0001\u0000"+
		"\u0000\u0000I\u0005\u0001\u0000\u0000\u0000JQ\u0003\n\u0005\u0000KQ\u0003"+
		"\f\u0006\u0000LQ\u0003\u000e\u0007\u0000MQ\u0003\b\u0004\u0000NQ\u0003"+
		"\u0010\b\u0000OQ\u0003\u0012\t\u0000PJ\u0001\u0000\u0000\u0000PK\u0001"+
		"\u0000\u0000\u0000PL\u0001\u0000\u0000\u0000PM\u0001\u0000\u0000\u0000"+
		"PN\u0001\u0000\u0000\u0000PO\u0001\u0000\u0000\u0000Q\u0007\u0001\u0000"+
		"\u0000\u0000RS\u0005\t\u0000\u0000ST\u0006\u0004\uffff\uffff\u0000TU\u0005"+
		"\u0019\u0000\u0000UV\u0003\u0014\n\u0000VW\u0005\u0014\u0000\u0000WX\u0006"+
		"\u0004\uffff\uffff\u0000XY\u0003\u0014\n\u0000YZ\u0005\u001a\u0000\u0000"+
		"Z[\u0006\u0004\uffff\uffff\u0000[]\u0005\n\u0000\u0000\\^\u0003\u0006"+
		"\u0003\u0000]\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_]\u0001"+
		"\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000"+
		"ak\u0006\u0004\uffff\uffff\u0000bc\u0005\u000b\u0000\u0000ce\u0006\u0004"+
		"\uffff\uffff\u0000df\u0003\u0006\u0003\u0000ed\u0001\u0000\u0000\u0000"+
		"fg\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000"+
		"\u0000hi\u0001\u0000\u0000\u0000ij\u0006\u0004\uffff\uffff\u0000jl\u0001"+
		"\u0000\u0000\u0000kb\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000"+
		"lm\u0001\u0000\u0000\u0000mn\u0005\f\u0000\u0000no\u0006\u0004\uffff\uffff"+
		"\u0000o\t\u0001\u0000\u0000\u0000pq\u0005\u0015\u0000\u0000qr\u0006\u0005"+
		"\uffff\uffff\u0000rs\u0005\u0013\u0000\u0000st\u0006\u0005\uffff\uffff"+
		"\u0000tu\u0003\u0014\n\u0000uv\u0005\u0018\u0000\u0000vw\u0006\u0005\uffff"+
		"\uffff\u0000w\u000b\u0001\u0000\u0000\u0000xy\u0005\r\u0000\u0000yz\u0005"+
		"\u0019\u0000\u0000z{\u0005\u0015\u0000\u0000{|\u0006\u0006\uffff\uffff"+
		"\u0000|}\u0005\u001a\u0000\u0000}~\u0005\u0018\u0000\u0000~\r\u0001\u0000"+
		"\u0000\u0000\u007f\u0080\u0005\u000e\u0000\u0000\u0080\u0081\u0005\u0019"+
		"\u0000\u0000\u0081\u0082\u0003\u0016\u000b\u0000\u0082\u0083\u0006\u0007"+
		"\uffff\uffff\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0085\u0005"+
		"\u001a\u0000\u0000\u0085\u0086\u0005\u0018\u0000\u0000\u0086\u0087\u0006"+
		"\u0007\uffff\uffff\u0000\u0087\u000f\u0001\u0000\u0000\u0000\u0088\u0089"+
		"\u0005\u000f\u0000\u0000\u0089\u008a\u0005\u0019\u0000\u0000\u008a\u008b"+
		"\u0005\u0015\u0000\u0000\u008b\u008c\u0006\b\uffff\uffff\u0000\u008c\u008d"+
		"\u0005\u0014\u0000\u0000\u008d\u008e\u0006\b\uffff\uffff\u0000\u008e\u008f"+
		"\u0007\u0000\u0000\u0000\u008f\u0090\u0006\b\uffff\uffff\u0000\u0090\u0091"+
		"\u0005\u001a\u0000\u0000\u0091\u0092\u0005\u001b\u0000\u0000\u0092\u0094"+
		"\u0006\b\uffff\uffff\u0000\u0093\u0095\u0003\u0006\u0003\u0000\u0094\u0093"+
		"\u0001\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0094"+
		"\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u0001\u0000\u0000\u0000\u0098\u0099\u0005\u001c\u0000\u0000\u0099\u009a"+
		"\u0006\b\uffff\uffff\u0000\u009a\u0011\u0001\u0000\u0000\u0000\u009b\u009c"+
		"\u0005\u0010\u0000\u0000\u009c\u009d\u0005\u001b\u0000\u0000\u009d\u009f"+
		"\u0006\t\uffff\uffff\u0000\u009e\u00a0\u0003\u0006\u0003\u0000\u009f\u009e"+
		"\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u009f"+
		"\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a3"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005\u001c\u0000\u0000\u00a4\u00a5"+
		"\u0005\u0011\u0000\u0000\u00a5\u00a6\u0005\u0019\u0000\u0000\u00a6\u00a7"+
		"\u0007\u0000\u0000\u0000\u00a7\u00a8\u0006\t\uffff\uffff\u0000\u00a8\u00a9"+
		"\u0005\u0014\u0000\u0000\u00a9\u00aa\u0006\t\uffff\uffff\u0000\u00aa\u00ab"+
		"\u0007\u0000\u0000\u0000\u00ab\u00ac\u0006\t\uffff\uffff\u0000\u00ac\u00ad"+
		"\u0005\u001a\u0000\u0000\u00ad\u00ae\u0006\t\uffff\uffff\u0000\u00ae\u0013"+
		"\u0001\u0000\u0000\u0000\u00af\u00b0\u0003\u0016\u000b\u0000\u00b0\u00b1"+
		"\u0006\n\uffff\uffff\u0000\u00b1\u00b2\u0003\u0018\f\u0000\u00b2\u0015"+
		"\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005\u0015\u0000\u0000\u00b4\u00ba"+
		"\u0006\u000b\uffff\uffff\u0000\u00b5\u00b6\u0005\u0016\u0000\u0000\u00b6"+
		"\u00ba\u0006\u000b\uffff\uffff\u0000\u00b7\u00b8\u0005\u001e\u0000\u0000"+
		"\u00b8\u00ba\u0006\u000b\uffff\uffff\u0000\u00b9\u00b3\u0001\u0000\u0000"+
		"\u0000\u00b9\u00b5\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000\u0000"+
		"\u0000\u00ba\u0017\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005\u0012\u0000"+
		"\u0000\u00bc\u00bd\u0006\f\uffff\uffff\u0000\u00bd\u00be\u0003\u0016\u000b"+
		"\u0000\u00be\u00bf\u0006\f\uffff\uffff\u0000\u00bf\u00c1\u0001\u0000\u0000"+
		"\u0000\u00c0\u00bb\u0001\u0000\u0000\u0000\u00c1\u00c4\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000"+
		"\u0000\u00c3\u0019\u0001\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000"+
		"\u0000\r &5?HP_gk\u0096\u00a1\u00b9\u00c2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}