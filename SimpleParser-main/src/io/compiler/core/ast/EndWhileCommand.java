package io.compiler.core.ast;

import java.util.ArrayList;

public class EndWhileCommand extends Command {
    private String condition;
    private ArrayList<Command> listaTrue;
    private ArrayList<Command> listaFalse;
    private String expression;

    public EndWhileCommand(String condition, ArrayList<Command> lt, ArrayList<Command> lf) {
        this.condition = condition;
        this.listaTrue = lt != null ? lt : new ArrayList<>();
        this.listaFalse = lf != null ? lf : new ArrayList<>();
    }

    public EndWhileCommand(String expression){
        this.expression = expression;
    }

    @Override
    public String generateTarget() {
        StringBuilder str = new StringBuilder();
        str.append("while (").append(condition).append(") {\n");
        for (Command cmd : listaTrue) {
            str.append(cmd.generateTarget()).append("\n");
        }
        str.append("}");
        if (!listaFalse.isEmpty()) {
            str.append(" else {\n");
            for (Command cmd : listaFalse) {
                str.append(cmd.generateTarget()).append("\n");
            }
            str.append("}\n");
        }
        return str.toString();
    }

    @Override
    public String toString() {
        return "EndWhileCommand [condition=" + condition + ", listaTrue=" + listaTrue + ", listaFalse=" + listaFalse + "]";
    }
}