package io.compiler.core.ast;

public class WriteCommand extends Command{
    private String content;
    private String text;

    
    @Override
    public String generateTarget() {
        return "System.out.println(" + content + ");";
    }
    public WriteCommand(String text) {

        this.text = text;

    }
}
