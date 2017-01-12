package parser.Exception;

public class SyntaxException extends Exception {
	private int row;

	public SyntaxException(int row){
		super("Syntax Error");
		this.row = row;
	}

	public void setRow(int row){
		this.row = row;
	}

	public int getRow(){
		return row;
	}
}
