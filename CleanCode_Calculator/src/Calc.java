/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author nico krebs
 */
public class Calc extends JFrame {

    private JPanel mainPanel;
    private final JTextField txtInput;
    private final JPanel inputPanel;
    private final JTextField txtOutput;

    /**
     * code taken from http://kevinyavno.com/blog/?p=52
     */
    public Calc() {
        this.setSize(new Dimension(200, 100));
        this.setLayout(new BorderLayout(15, 15));
        mainPanel = new JPanel(new BorderLayout(15, 15));
        inputPanel = new JPanel(new BorderLayout(15, 15));
        txtInput = new JTextField("");
        txtInput.setColumns(20);
        txtInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                ArrayList<String> processedList = new ArrayList<String>();
                if (txtInput.getText().length() > 0) {
                    StringTokenizer st = new StringTokenizer(txtInput.getText());
                    while (st.hasMoreTokens()) {
                        processedList.add(st.nextToken());
                    }
                } else {
                    txtOutput.setText("Error");
                }
                Stack<String> tempList = new Stack<String>();
                Iterator<String> iter = processedList.iterator();
                while (iter.hasNext()) {
                    String temp = iter.next();
                    if (temp.matches("[0-9]*")) {
                        tempList.push(temp);
                    } else if (temp.matches("[*-/+]")) {
                        //If the current item is an operator we pop off the last two elements 
                        //of our stack and calculate them using the operator we are looking at. 
                        //Push the result onto the stack.
                        if (temp.equals("*")) {
                            int rs = Integer.parseInt(tempList.pop());
                            int ls = Integer.parseInt(tempList.pop());
                            int r = ls * rs;
                            tempList.push("" + r);
                        } else if (temp.equals("-")) {
                            int rs = Integer.parseInt(tempList.pop());
                            int ls = Integer.parseInt(tempList.pop());
                            int r = ls - rs;
                            tempList.push("" + r);
                        } else if (temp.equals("/")) {
                            int rs = Integer.parseInt(tempList.pop());
                            int ls = Integer.parseInt(tempList.pop());
                            int r = ls / rs;
                            tempList.push("" + r);
                        } else if (temp.equals("+")) {
                            int rs = Integer.parseInt(tempList.pop());
                            int ls = Integer.parseInt(tempList.pop());
                            int r = ls + rs;
                            tempList.push("" + r);
                        }
                    } else {
                        txtOutput.setText("Error");
                    }
                }
                txtOutput.setText(tempList.pop());
            }
        });
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
    
    public static void main(String[] args) {
        new Calc();
    }
}
