package io.compiler.core.ast;

import java.util.ArrayList;

public class WhileCommand extends Command {
    private String condition;
    private ArrayList<Command> listaTrue;

    public WhileCommand(String condition, ArrayList<Command> lt) {
        this.condition = condition;
        this.listaTrue = lt;
    }


    @Override
    public String generateTarget() {
        // TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("while ("+condition+") {\n");
		for (Command cmd: listaTrue) {
			str.append(cmd.generateTarget());
		}
		str.append("}");
	
		return str.toString();
    }

    @Override
    public String toString() {
        return "WhileCommand [condition=" + condition + ", listaTrue=" + listaTrue + "]";
    }
}