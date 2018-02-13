/*
Arminder Khinda
Date: 02/13/2018
Comp 585
Purpose: Declare and run the calculator frame
 */

package calculator;

import javax.swing.JFrame;


public class Calculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame calcFrame = new CalculatorFrame();
        calcFrame.setTitle("Calculator");
        calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calcFrame.setVisible(true);
        calcFrame.setResizable(false);
    }
    
}
