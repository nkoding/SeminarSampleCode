package calculator;

import java.util.Stack;

public interface Operator {
	
	/**
	 * check if operand string can be handled
	 * @return
	 */
	public boolean canRun(String operand);
	
	/**
	 * calculate 
	 * @param operands
	 * @param currentToken TODO
	 */
	public void run(Stack<String> operands, String currentToken) ;
}
