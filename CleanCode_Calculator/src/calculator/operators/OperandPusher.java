package calculator.operators;

import java.util.Stack;

import calculator.Operator;

public class OperandPusher implements Operator{

	@Override
	public boolean canRun(String operand) {
		return operand.matches("[0-9]+");
	}

	@Override
	public void run(Stack<String> operands, String currentToken) {
		operands.push(currentToken);
	}

}
