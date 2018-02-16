/*
Arminder Khinda
Date: 02/13/2018
Comp 585
Purpose: Create the GUI frame, add key and click listeners to buttons
 */

package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;


public class CalculatorFrame extends JFrame implements KeyListener, ActionListener {
    
    // Buttons
    private JButton buttons[];
    
    //TextField
    private JTextField output;
    
    // Panels
    private JPanel textPanel;
    private JPanel buttonPanel;
    
    // Menu
    private MenuBar menuBar;
    
    // JFrame Size
    private static final int FRAME_WIDTH = 270;
    private static final int FRAME_HEIGHT = 350;
    
    // Determine if new input
    private boolean newInput = true;
    
    //Calculation object
    MathCalculation calc = new MathCalculation();
    
    /***** Build the frame *****/
    public CalculatorFrame() {
        addKeyListener(this);
        addMenuBar();
        textPanel();
        buttonPanel();
        keyListeners();
        buttonClickListeners();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }  
    
    /***** Setup the menu bar at the top *****/
    private void addMenuBar() {
        menuBar = new MenuBar();
        setJMenuBar(menuBar);
    }
    
    /***** Text field above buttons *****/
    private void textPanel() {
        textPanel = new JPanel(new BorderLayout());
        output = new JTextField("0");
        output.addKeyListener(this);
        output.setBackground(Color.gray);
        output.setOpaque(true);
        output.setPreferredSize(new Dimension(150, 100));
        output.setEditable(false);
        textPanel.add(output);
        add(textPanel, BorderLayout.NORTH);
    }
    
    /***** Add the buttons using a GridLayout *****/
    private void buttonPanel() {
        buttonPanel = new JPanel(new GridLayout(5, 4));
        buttons = new JButton[19];
        
        // Number Buttons
        for(int i = 0; i<10; i++) {
            buttons[i] = new JButton(String.valueOf(i));
            buttons[i].setPreferredSize(new Dimension(40, 40));
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
        
        // Add the buttons by row
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
    
    private void keyListeners() {
        for (JButton button : buttons) {
            button.addKeyListener(this);
        }
    }
    
    private void buttonClickListeners() {
        for (JButton button : buttons) {
            button.addActionListener(this);
        }
    }
    
    /***** Common operation method for button and key listeners *****/
    private void doAction(char event) {
         
        if(event == '0' || event == '1' || event == '2' || event == '3' ||
           event == '4' || event == '5' || event == '6' || event == '7' ||
           event == '8' || event == '9') {
            if(newInput == true) {
                output.setText("" + event);
                newInput = false;
            }
            else {
                output.setText(output.getText() + event);
            }
        }
        else if(event == '/') {
            calc.performCalculation("divide", output.getText());
            output.setText("" + calc.getResult());
            newInput = true;
        }
        else if(event == 'X') {
            calc.performCalculation("multiply", output.getText());
            output.setText("" + calc.getResult());
            newInput = true;
        }
        else if(event == '+') {
            calc.performCalculation("add", output.getText());
            output.setText("" + calc.getResult());
            newInput = true;
        }
        else if(event == '-') {
            calc.performCalculation("subtract", output.getText());
            output.setText("" + calc.getResult());
            newInput = true;
        }
        else if(event == 'm') {
            calc.performCalculation("modulus", output.getText());
            output.setText("" + calc.getResult());
            newInput = true;
        }
        else if(event == '%') {
            output.setText("" + Double.parseDouble(output.getText()) / 100);
            newInput = true; 
        }
        else if(event == '.') {
            if(newInput == true) {
                output.setText("0.");
                newInput = false;
            }
            else {
                String text = output.getText();
                if(text.indexOf(".") == -1) {
                    output.setText(text.concat("."));
                }
            }
        }
        else if(event == 'C') {
            output.setText("0");
            calc.setResult(0);
            newInput = true;
        }
        else if(event == '=') {
            calc.performCalculation(null, output.getText());
            output.setText("" + calc.getResult());
            newInput = true;
        }
    }
    
    /***** Handle button press *****/
    @Override
    public void actionPerformed(ActionEvent e) {
        char action = e.getActionCommand().charAt(0);
        
        if(e.getActionCommand() != "mod") {
            doAction(action);
        }
        else {
            doAction('m');
        }  
    }
    
    /***** Handle key presses *****/
    @Override
    public void keyPressed(KeyEvent e) {
        char eventChar = e.getKeyChar();
        
        for(int i = 48; i<=57; i++) {
            if(eventChar == i ) {
                doAction((char) i);
            }
        }

        switch (eventChar) {
            case 37:
                buttons[14].doClick();
                break;
            case 42:
                buttons[11].doClick();
                break;
            case 43:
                buttons[13].doClick();
                break;
            case 45:
                buttons[12].doClick();
                break;
            case 46:
                buttons[18].doClick();
                break;
            case 47:
                buttons[10].doClick();
                break;
            case 61:
                buttons[17].doClick();
                break;
            case 67:
            case 99:
                buttons[16].doClick();
                break;
            case 109:
                buttons[15].doClick();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    } 
}