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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;

/**
 *
 * @author ArminderSingh
 */
public class CalculatorFrame extends JFrame implements KeyListener, ActionListener {
    
    // Buttons
    private JButton buttons[];
    
    //TextField
    private JTextField output;
    
    // Panels
    private JPanel textPanel;
    private JPanel buttonPanel;
    
    // Menu
    private JMenuBar menuBar;
    private JMenu appMenu;
    private JMenu helpMenu;
    private JMenuItem exitMenuItem;
    private JMenuItem aboutMenuItem;
    
    // JFrame Size
    private static final int FRAME_WIDTH = 275;
    private static final int FRAME_HEIGHT = 300;
    
    // Math Operations
    private double result = 0;
    private String mathOperation;
    private boolean newInput = true;
    
    // Build the frame
    public CalculatorFrame() {
        addKeyListener(this);
        addMenuBar();
        textPanel();
        buttonPanel();
        keyListeners();
        buttonClickListeners();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }  
    
    // setup the menu
    private void addMenuBar() {
        menuBar = new JMenuBar();
        // menu
        appMenu = new JMenu("App");
        helpMenu = new JMenu("Help");
        // menu items
        exitMenuItem = new JMenuItem("Exit");
        aboutMenuItem = new JMenuItem("About");
        // add menu items to menu
        appMenu.add(exitMenuItem);
        helpMenu.add(aboutMenuItem);
        // add menus to bar
        menuBar.add(appMenu);
        menuBar.add(helpMenu);
        // menu item listeners
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Thanks for using my app.");
            }
        });
        setJMenuBar(menuBar);
    }
    
    // Text field at the top
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
    
    // Add the buttons using a GridLayout
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
    
    // Common operation method for button and key listeners
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
            mathCalculations("divide");
        }
        else if(event == 'X') {
            mathCalculations("multiply");
        }
        else if(event == '+') {
            mathCalculations("add");
        }
        else if(event == '-') {
            mathCalculations("subtract");
        }
        else if(event == 'm') {
            mathCalculations("modulus");
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
            result = 0;
            newInput = true;
        }
        else if(event == '=') {
            mathCalculations(null);
        }
    }
    
    // Method to perform Math Calculations
    private void mathCalculations(String s) {
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
            else if(mathOperation == "modulus") {
                result %= Double.parseDouble(output.getText());
            }
        }
        else {
            result = Double.parseDouble(output.getText());
        }
                
        output.setText("" + result);
        newInput = true;
        mathOperation = s;
    }
    
    // Handle button press
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
    
    // Handle key presses

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
//            doAction('%');
                break;
            case 42:
                buttons[11].doClick();
//            doAction('X');
                break;
            case 43:
                //            doAction('+');
                buttons[13].doClick();
                break;
            case 45:
                buttons[12].doClick();
//            doAction('-');
                break;
            case 46:
                //            doAction('.');
                buttons[18].doClick();
                break;
            case 47:
                buttons[10].doClick();
//            doAction('/');
                break;
            case 61:
                buttons[17].doClick();
//            doAction('=');
                break;
            case 67:
            case 99:
                buttons[16].doClick();
//            doAction('C');
                break;
            case 109:
                buttons[15].doClick();
//            doAction('m');
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
