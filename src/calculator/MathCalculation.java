/*
Arminder Khinda
Date: 02/13/2018
Comp 585
Purpose: Math calculations logic, invoked upon key and click event
 */

package calculator;


public class MathCalculation {
    
    private double result;
    private String mathOperation;
    
    public MathCalculation() {
        result = 0;
    }
    
    public void performCalculation(String op, String output) {
        
        if(output.isEmpty()) {
            return;
        }
        if(mathOperation != null) {
            if(mathOperation == "add") {
                result += Double.parseDouble(output);
            }
            else if(mathOperation == "divide") {
                result /= Double.parseDouble(output);
            }
            else if(mathOperation == "multiply") {
                result *= Double.parseDouble(output);
            }
            else if(mathOperation == "subtract") {
                result -= Double.parseDouble(output);
            }
            else if(mathOperation == "modulus") {
                result %= Double.parseDouble(output);
            }
        }
        else {
            result = Double.parseDouble(output);
        }
        
        mathOperation = op;
        
    }
    
    public double getResult() {
        return result;
    }
    
    public void setResult(double r) {
        result = r;
    }
    
}
