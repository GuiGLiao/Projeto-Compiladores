grammar IsiLanguage;

@header {
	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;
}

@members {
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
}
 
programa	: 'programa' ID  { program.setName(_input.LT(-1).getText());
                               stack.push(new ArrayList<Command>()); 
                             }
               declaravar+
               'inicio'
               comando+
               'fim'
               'fimprog'
               
               {
                  program.setSymbolTable(symbolTable);
                  program.setCommandList(stack.pop());
               }
			;
						
declaravar	: 'declare' { currentDecl.clear(); } 
               ID  { if (isDeclared(_input.LT(-1).getText())) {
                        throw new IsiSemanticException("Variable already declared: " + _input.LT(-1).getText());

               }
                  currentDecl.add(new Var(_input.LT(-1).getText()));
                  }
               ( VIRG ID                
              		 { if (isDeclared(_input.LT(-1).getText())) {
                        throw new IsiSemanticException("Variable already declared: " + _input.LT(-1).getText());
                     }
                     currentDecl.add(new Var(_input.LT(-1).getText()));
                     }
               )*
               
               DP 
               (
               'number' {currentType = Types.NUMBER;}
               |
               'text' {currentType = Types.TEXT;}
               |
               'real' {currentType = Types.REALNUMBER;}
               )

               { updateType(); } 
               PV
			;

bloco	: { curThread = new ArrayList<Command>(); 
	        stack.push(curThread);  
          }
          (comando)+
		;


comando     :  cmdAttrib
			|  cmdLeitura
			|  cmdEscrita
			|  cmdIF
			|  cmdWhile
         |  cmdExecuteFor
         
			;
			
cmdIF		: 'se'  { stack.push(new ArrayList<Command>());
                      strExpr = "";
                      currentIfCommand = new IfCommand();
                    } 
               AP 
               expr
               OPREL  { strExpr += _input.LT(-1).getText(); }
               expr 
               FP  { currentIfCommand.setExpression(strExpr); }
               'entao'  
               comando+                
               { 
                  currentIfCommand.setTrueList(stack.pop());                            
               }  
               ( 'senao'  
                  { stack.push(new ArrayList<Command>()); }
                 comando+
                 {
                   currentIfCommand.setFalseList(stack.pop());
                 }  
               )? 
               'fimse' 
               {
               	   stack.peek().add(currentIfCommand);
               }  			   
			;
			
cmdAttrib : ID { 
               // Verifica se a variável foi declarada
               String varName = _input.LT(-1).getText();
               if (!isDeclared(varName)) {
                  throw new IsiSemanticException("Undeclared Variable: " + varName);
               }
               // Marca a variável como inicializada
               symbolTable.get(varName).setInitialized(true);
               leftType = symbolTable.get(varName).getType();
            }
            OP_AT {strExpr = "";}
            expr 
            PV
            {
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
;
	
			
cmdLeitura  : 'leia' AP 
               ID { if (!isDeclared(_input.LT(-1).getText())) {
                       throw new IsiSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
                    }
                    symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
                    Command cmdRead = new ReadCommand(symbolTable.get(_input.LT(-1).getText()));
                    stack.peek().add(cmdRead);
                    //System.out.println("DEBUG - " + _input.LT(-1).getText());   
                  } 
               FP 
               PV 
			;
			
cmdEscrita  : 'escreva' AP 
              ( termo  { Command cmdWrite = new WriteCommand(_input.LT(-1).getText());
                         stack.peek().add(cmdWrite);
                       } 
              ) 
              FP PV { rightType = null;}
			;			


cmdWhile :  'enquanto'
                    AP
                    ID{exprCondition = _input.LT(-1).getText(); }
                    OPREL { exprCondition += _input.LT(-1).getText(); }
                    (ID | NUM) {exprCondition += _input.LT(-1).getText(); }
                    FP
                    ACH
                     {
                        curThread = new ArrayList<Command>(); 
                        stack.push(curThread);
                     }
                     (comando)+ 
                    FCH
                     {
                     listaTrue = stack.pop();	
                     Command cmdWhile = new WhileCommand(exprCondition, listaTrue);
                   	stack.peek().add(cmdWhile);
                    } 
         ;


cmdExecuteFor : 'execute'
                  ACH
                  { curThread = new ArrayList<Command>(); 
                    stack.push(curThread);
                  }
                  (comando)+
                  FCH
                  'ate'
                  AP
                  (ID | NUM) {exprExecuteFor = _input.LT(-1).getText(); }
                  OPREL { exprExecuteFor += _input.LT(-1).getText(); }
                  (ID | NUM) {String var = _input.LT(-1).getText(); 
                  exprExecuteFor += var;
                  }
                  FP
                  {
                     listaComandos = stack.pop();
                     Command cmdExecuteFor = new ExecuteForCommand(exprExecuteFor, listaComandos);
                     stack.peek().add(cmdExecuteFor);
                  }
			;
expr		:  termo  { strExpr += _input.LT(-1).getText(); } exprl 			
			;
			
termo		: ID  { if (!isDeclared(_input.LT(-1).getText())) {
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
			| NUM    {  if (rightType == null) {
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
			| TEXTO  {  if (rightType == null) {
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
			;
			
exprl		: ( OP { strExpr += _input.LT(-1).getText(); } 
                termo { strExpr += _input.LT(-1).getText(); } 
              ) *
			;	
			
OP			: '+' | '-' | '*' | '/' 
			;	
			
OP_AT	    : ':='
		    ;
		    
OPREL       : '>' | '<' | '>=' | '<= ' | '<>' | '=='
			;		    			
			
ID			: [a-z] ( [a-z] | [A-Z] | [0-9] )*		
			;
			
NUM			: [0-9] + ('.' [0-9] +)?
			;			
			
VIRG		: ','
			;
						
PV			: ';'
            ;			
            
AP			: '('
			;            
						
FP			: ')'
			;

ACH         : '{'
         ;

FCH         : '}';
									
DP			: ':'
		    ;
		    
TEXTO       : '"' ( [a-z] | [A-Z] | [0-9] | ',' | '.' | ' ' | '-' )* '"'
         ;	
      
WS			: (' ' | '\n' | '\r' | '\t' ) -> skip
         ;