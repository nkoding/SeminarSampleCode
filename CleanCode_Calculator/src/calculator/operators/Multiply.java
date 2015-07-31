package calculator.operators;

import java.util.Stack;


public class Multiply extends BasicOperator
{

	@Override
	public String getOperatorIdentifier() {
		return "*";
	}

	@Override
	public Executor getExecutor() {
		Executor exec = new Executor() {
			@Override
			public int execute(Stack<String> tokens) {
				int leftSide = Integer.parseInt(tokens.pop());
				int rightSide = Integer.parseInt(tokens.pop());
				return leftSide * rightSide;
			}
		};
		return exec;
	}
	

}
