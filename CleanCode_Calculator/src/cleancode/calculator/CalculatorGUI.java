/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleancode.calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author kdot
 */
class CalculatorGUI extends JFrame {

    private JPanel mainPanel;
    private final JTextField txtInput;
    private final JPanel inputPanel;
    private final JTextField txtOutput;

    public CalculatorGUI() {
        Stack<String> stack = new Stack<String>();
        Stack<String> numberStack = new Stack<String>();
        this.setSize(new Dimension(200, 100));
        this.setLayout(new BorderLayout(15, 15));
        mainPanel = new JPanel(new BorderLayout(15, 15));
        inputPanel = new JPanel(new BorderLayout(15, 15));

        txtInput = new JTextField("");
        txtInput.setColumns(20);
        txtInput.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                txtOutput.setText(compute(txtInput.getText()));
            }
        });

        txtOutput = new JTextField("");
//        txtOutput.setPreferredSize(new Dimension(300, 20));
        txtOutput.setBackground(Color.white);
        txtOutput.setEditable(false);

        inputPanel.add(txtInput, BorderLayout.CENTER);
        inputPanel.add(txtOutput, BorderLayout.SOUTH);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * compute method taken from http://kevinyavno.com/blog/?p=52
     * @param input
     * @return 
     */
    public static String compute(String input) {
        //Process the list into an ArrayList
        ArrayList<String> processedList = new ArrayList<String>();
        if (!input.isEmpty()) {
            StringTokenizer st = new StringTokenizer(input);
            while (st.hasMoreTokens()) {
                processedList.add(st.nextToken());
            }
        } else {
            return "Error";
        }
        
        //A Stack, we will use this for the calculation
        Stack<String> tempList = new Stack<String>();
        
        //Iterate over the whole processed list
        Iterator<String> iter = processedList.iterator();
        while (iter.hasNext()) {
            String temp = iter.next();
            if (temp.matches("[0-9]*")) {
                //If the current item is a number (aka operand), push it onto the stack
                tempList.push(temp);
            } else if (temp.matches("[*-/+]")) {
                //If the current item is an operator we pop off the last two elements 
                //of our stack and calculate them using the operator we are looking at. 
                //Push the result onto the stack. 
                if (temp.equals("*")) {
                    int rs = Integer.parseInt(tempList.pop());
                    int ls = Integer.parseInt(tempList.pop());
                    int result = ls * rs;
                    tempList.push("" + result);
                } else if (temp.equals("-")) {
                    int rs = Integer.parseInt(tempList.pop());
                    int ls = Integer.parseInt(tempList.pop());
                    int result = ls - rs;
                    tempList.push("" + result);
                } else if (temp.equals("/")) {
                    int rs = Integer.parseInt(tempList.pop());
                    int ls = Integer.parseInt(tempList.pop());
                    int result = ls / rs;
                    tempList.push("" + result);
                } else if (temp.equals("+")) {
                    int rs = Integer.parseInt(tempList.pop());
                    int ls = Integer.parseInt(tempList.pop());
                    int result = ls + rs;
                    tempList.push("" + result);
                }
            } else {
                return "Error";
            }
        }
        //Return the last element on the Stack.
        return tempList.pop();
    }
}
