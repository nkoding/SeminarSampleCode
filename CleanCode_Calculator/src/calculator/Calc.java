/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.security.auth.Subject;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calculator.operators.Add;
import calculator.operators.Divide;
import calculator.operators.Multiply;
import calculator.operators.OperandPusher;
import calculator.operators.Subtract;

/**
 * @author nico krebs
 */
public class Calc extends JFrame {
	private static String defaultResult="error";
	private JPanel mainPanel;
	private JTextField txtInput;
	private JPanel inputPanel;
	private JTextField txtOutput;
	private Stack<String> operands;
	private ArrayList<Operator> operators;

	/**
	 * code taken from http://kevinyavno.com/blog/?p=52
	 */
	public Calc() {
		initGUI();
		initListeners();
		initOperators();
	}

	private void initOperators() {
		operators = new ArrayList<Operator>();
		operators.add(new OperandPusher());
		operators.add(new Add());
		operators.add(new Subtract());
		operators.add(new Multiply());
		operators.add(new Divide());
	}

	private void initListeners() {
		txtInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				calculate();
			}
		});
	}

	public void initGUI() {
		this.setSize(new Dimension(200, 100));
		this.setLayout(new BorderLayout(15, 15));
		mainPanel = new JPanel(new BorderLayout(15, 15));
		inputPanel = new JPanel(new BorderLayout(15, 15));
		txtInput = new JTextField("");
		txtInput.setColumns(20);
		txtOutput = new JTextField("");
		txtOutput.setBackground(Color.white);
		txtOutput.setEditable(false);
		inputPanel.add(txtInput, BorderLayout.CENTER);
		inputPanel.add(txtOutput, BorderLayout.SOUTH);
		mainPanel.add(inputPanel, BorderLayout.CENTER);
		this.add(mainPanel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	protected void calculate() {
		Stack<String> tokens = tokenize();
		operands = new Stack<String>();
		Iterator<String> tokenIter = tokens.iterator();
		String result = defaultResult;
		
		while (tokenIter.hasNext()) {
			String token = tokenIter.next();
			
 			for(Operator operator:operators){
 				
				if( ! operator.canRun(token)){
					continue;
				}
				operator.run(operands, token);
			}
		}
		if(! operands.isEmpty()){
			result = operands.pop();
		}
		
		txtOutput.setText(result);
	}

	
	
	protected Stack<String> tokenize() {
		if (txtInput.getText().length() <= 0) {
			txtOutput.setText("Error");
			return new Stack<String>();
		}

		StringTokenizer st = new StringTokenizer(txtInput.getText());
		Stack<String> tokens = new Stack<String>();

		while (st.hasMoreTokens()) {
			tokens.add(st.nextToken());
		}
		return tokens;
	}

	public static void main(String[] args) {
		new Calc();
	}
}
