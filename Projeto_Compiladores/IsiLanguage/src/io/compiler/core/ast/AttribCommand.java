package io.compiler.core.ast;

public class AttribCommand extends Command{

	private String id;
	private String expr;
	
        
        public String getId()
        {
            return this.id;
        }
        
	public AttribCommand(String id, String expr) {
		this.id = id;
		this.expr = expr;
	}
	@Override
	public String generateTarget() {
		// TODO Auto-generated method stub
		return id + " = "+expr+";\n";
	}
	@Override
	public String toString() {
		return "AttribCommand [id=" + id + ", expr=" + expr + "]";
	}

}
