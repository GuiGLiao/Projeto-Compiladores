package io.compiler.core.ast;

import java.util.ArrayList;

public class ExecuteForCommand extends Command{
     
	private String condition;
	private ArrayList<Command> listaCmd;
	
	public ExecuteForCommand(String condition, ArrayList<Command> lcmd) {
		this.condition = condition;
		this.listaCmd = lcmd;
	}
	@Override
	public String generateTarget() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("do{\n");
		for (Command cmd: listaCmd) {
			str.append(cmd.generateTarget()+"\n");
		}
		str.append("}while("+condition+");\n");
		return str.toString();
	}
	@Override
	public String toString() {
		return "CommandFazer [condition=[" + condition + "], comandos=" + listaCmd + "]";
	}
	
    
}
