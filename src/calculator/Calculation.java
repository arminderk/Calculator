/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author ArminderSingh
 */
public class Calculation {
    
    private double result;
    private String mathOperation;
    
    public Calculation() {
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
