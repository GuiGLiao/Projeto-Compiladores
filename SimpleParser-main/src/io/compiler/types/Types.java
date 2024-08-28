package io.compiler.types;

public enum Types {
	NUMBER(1),
	REALNUMBER(2),
	TEXT(3);
	
	private int value;
	
	private  Types(int valueNumber) {
		this.value = valueNumber;
	}
	public Integer getValue() {
		return this.value;
	}

	public String getReadMethod() {
		switch(this) {
		case NUMBER:
			return "Integer.parseInt(System.console().readLine())";
		case REALNUMBER:
			return "Double.parseDouble(System.console().readLine())";
		case TEXT:
			return "System.console().readLine()";
		default:
			return null;
		}
	}
}
