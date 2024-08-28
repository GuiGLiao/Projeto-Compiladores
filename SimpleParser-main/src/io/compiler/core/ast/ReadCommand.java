package io.compiler.core.ast;

import io.compiler.types.Var;

public class ReadCommand extends Command{
    private String content;
    private Var var;

    public ReadCommand(String content) {
        this.content = content;
    }
    
    public ReadCommand(Var var){
        this.var = var;
    }

    @Override
    public String generateTarget() {
        return content + " = " + var.getType().getReadMethod() + ";";
    }
    @Override
    public String toString() {
        return "System.out.println(" + content + ");";
    }

}