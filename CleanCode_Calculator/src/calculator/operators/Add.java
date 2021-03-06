package calculator.operators;

import java.util.Stack;

public class Add extends BasicOperator {

	@Override
	public String getOperatorIdentifier() {
		return "+";
	}

	@Override
	public Executor getExecutor() {
		Executor exec = new Executor() {

			@Override
			public int execute(Stack<String> operands) {
				int leftSide = Integer.parseInt(operands.pop());
				int rightSide = Integer.parseInt(operands.pop());
				return leftSide + rightSide;
			}
		};
		return exec;
	}

}
