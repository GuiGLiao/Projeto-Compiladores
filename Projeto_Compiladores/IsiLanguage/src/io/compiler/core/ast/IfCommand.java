package io.compiler.core.ast;
import java.util.List;

public class IfCommand extends Command {

    private List<Command> trueList;
    private List<Command> falseList;
    private String expression;

    public IfCommand(){
        super();
    }

    public IfCommand(String expression, List<Command> trueList, List<Command> falseList){
        super();
        this.expression = expression;
        this.trueList = trueList;
        this.falseList = falseList;
    }

    public void setTrueList(List<Command> trueList) {
        if (trueList != null) {
            this.trueList = trueList;
        }
    }

    public void setFalseList(List<Command> falseList) {
        if (falseList != null) {
            this.falseList = falseList;
        }
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<Command> getTrueList() {
        return trueList;
    }

    public List<Command> getFalseList() {
        return falseList;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public String generateTarget() {
        StringBuilder str = new StringBuilder();
        str.append("if (" + expression + ") {\n");
        for(Command cmd: trueList) {
            str.append(cmd.generateTarget());
        }
        str.append("}");

        if(!falseList.isEmpty()){
            str.append("else {\n");
            for(Command cmd: falseList) {
                str.append(cmd.generateTarget());
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