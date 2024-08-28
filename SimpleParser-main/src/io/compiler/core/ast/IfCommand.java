package io.compiler.core.ast;
import java.util.ArrayList;

public class IfCommand extends Command {

    private ArrayList<Command> trueList = new ArrayList<>();
    private ArrayList<Command> falseList = new ArrayList<>();
    private String expression;

    public IfCommand(String expression) {
        this.expression = expression;
    }

    public void setTrueList(ArrayList<Command> trueList) {
        if (trueList != null) {
            this.trueList = trueList;
        }
    }

    public void setFalseList(ArrayList<Command> falseList) {
        if (falseList != null) {
            this.falseList = falseList;
        }
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public ArrayList<Command> getTrueList() {
        return trueList;
    }

    public ArrayList<Command> getFalseList() {
        return falseList;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public String generateTarget() {
        StringBuilder str = new StringBuilder();
        str.append("if (").append(expression).append(") {\n");
        for (Command cmd : trueList) {
            str.append(cmd.generateTarget()).append("\n");
        }
        str.append("}");
        if (!falseList.isEmpty()) {
            str.append(" else {\n");
            for (Command cmd : falseList) {
                str.append(cmd.generateTarget()).append("\n");
            }
            str.append("}\n");
        }
        return str.toString();
    }

    @Override
    public String toString() {
        return "IfCommand [expression=" + expression + ", trueList=" + trueList + ", falseList=" + falseList + "]";
    }
}