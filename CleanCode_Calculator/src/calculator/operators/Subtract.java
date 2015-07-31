package calculator.operators;

import java.util.Stack;


public class Subtract extends BasicOperator
{

	@Override
	public String getOperatorIdentifier() {
		return "-";
	}

	@Override
	public Executor getExecutor() {
		Executor exec = new Executor() {
			@Override
			public int execute(Stack<String> tokens) {
				int rightSide = Integer.parseInt(tokens.pop());
				int leftSide = Integer.parseInt(tokens.pop());
				return leftSide - rightSide;
			}
		};
		return exec;
	}
	

}
