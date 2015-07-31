package calculator.operators;

import java.util.Stack;

import calculator.Operator;

public abstract class BasicOperator  implements Operator
{
	private Executor executor;
	
	public BasicOperator() {
		super();
		this.executor = getExecutor();
	}

	@Override
	public boolean canRun(String operator) {
		return operator.equals(getOperatorIdentifier());
	}

	@Override
	public void run(Stack<String> operands, String currentToken) {
		int r = executor.execute(operands);
		operands.push("" + r);
	}

	public abstract String getOperatorIdentifier();
	
	public abstract Executor getExecutor();
}
