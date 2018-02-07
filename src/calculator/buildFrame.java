/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;

/**
 *
 * @author ArminderSingh
 */
public class buildFrame extends JFrame {
    
    private JButton buttons[];
    private JTextField output;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JPanel buttonMasterPanel;
    
    private static final int FRAME_WIDTH = 250;
    private static final int FRAME_HEIGHT = 275;
    private double result = 0;
    private String mathOperation;
    private boolean performingCalc = true;
    
    public buildFrame() {
        textPanel();
        buttonPanel();
        performOperations();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }  
    
    private void textPanel() {
        output = new JTextField("0");
        output.setBackground(Color.gray);
        output.setOpaque(true);
        output.setPreferredSize(new Dimension(150, 100));
        add(output, BorderLayout.NORTH);
    }
    
    private void buttonPanel() {
        buttonPanel = new JPanel(new GridLayout(5, 4, 0, 0));
        buttons = new JButton[19];
        
        // Number Buttons
        for(int i = 0; i<10; i++) {
            buttons[i] = new JButton(String.valueOf(i));
        }
        
        // Operation Buttons
        buttons[10] = new JButton("/");
        buttons[11] = new JButton("X");
        buttons[12] = new JButton("-");
        buttons[13] = new JButton("+");
        buttons[14] = new JButton("%");
        buttons[15] = new JButton("mod");
        buttons[16] = new JButton("C");
        buttons[17] = new JButton("=");
        buttons[18] = new JButton(".");
        
        buttonPanel.add(buttons[16]);
        buttonPanel.add(buttons[15]);
        buttonPanel.add(buttons[14]);
        buttonPanel.add(buttons[10]);
        
        for(int i = 7; i<=9; i++) {
            buttonPanel.add(buttons[i]);
        }
        
        buttonPanel.add(buttons[11]);
        
        for(int i = 4; i<= 6; i++) {
            buttonPanel.add(buttons[i]);
        }
        
        buttonPanel.add(buttons[12]);
        
        for(int i = 1; i<=3; i++) {
            buttonPanel.add(buttons[i]);
        }
        
        buttonPanel.add(buttons[13]);
        buttonPanel.add(buttons[0]);
        buttonPanel.add(buttons[18]);
        buttonPanel.add(buttons[17]);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
    }
    
    private void performOperations() {
        
        for(int i = 0; i<10; i++) {
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(performingCalc == true) {
                        output.setText(e.getActionCommand());
                        performingCalc = false;
                    }
                    else {
                        output.setText(output.getText() + e.getActionCommand());
                    }
                }
            });
        }
        
        // Divide Operation
        buttons[10].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mathOperations("divide");
            }
        });
        
        // Multiplication Operation
        buttons[11].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mathOperations("multiply");
            }
        });
        
        // Subtraction Operation
        buttons[12].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mathOperations("subtract");
            }
        });
        
        // Addition Operation
        buttons[13].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mathOperations("add");
            }
        });
        
//        buttons[16].addActionListener(new ActionListener() {
//           public void actionPerformed(ActionEvent e) {
//               
//           } 
//        });
        
        // Equals Operation
        buttons[17].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mathOperations(null);
            } 

        });
         
    }
    
    private void mathOperations(String s) {
        if(output.getText().isEmpty()) {
            return;
        }
        if(mathOperation != null) {
            if(mathOperation == "add") {
                result += Double.parseDouble(output.getText());
            }
            else if(mathOperation == "divide") {
                result /= Double.parseDouble(output.getText());
            }
            else if(mathOperation == "multiply") {
                result *= Double.parseDouble(output.getText());
            }
            else if(mathOperation == "subtract") {
                result -= Double.parseDouble(output.getText());
            }
        }
        else {
            result = Double.parseDouble(output.getText());
        }
                
        output.setText("" + result);
        performingCalc = true;
        mathOperation = s;
    }
    
}
