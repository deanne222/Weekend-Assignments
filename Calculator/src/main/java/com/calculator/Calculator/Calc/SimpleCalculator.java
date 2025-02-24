package com.calculator.Calculator.Calc;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SimpleCalculator {

    @PostMapping("/addition")
    public String Add(@RequestBody Map<String, Integer> add){
        int num1 = add.get("num1");
        int num2 = add.get("num2");
        int result = num1 + num2;
        return "The Sum is: " + result;
    }

    @PostMapping("/subtraction")
    public String Sub(@RequestBody Map<String, Integer> sub){
        int num1 = sub.get("num1");
        int num2 = sub.get("num2");
        int result = num1 - num2;
        return "The Difference is: " + result;
    }

    @PostMapping("/multiplication")
    public String Xly(@RequestBody Map<String, Integer> xly){
        int num1 = xly.get("num1");
        int num2 = xly.get("num2");
        int result = num1 * num2;
        return "The Product is: " + result;
    }

    @PostMapping("/division")
    public String Div(@RequestBody Map<String, Integer> div){
        int num1 = div.get("num1");
        int num2 = div.get("num2");
        if(num2 == 0){
            return "Division by 0 is not allowed";
        }
        else{
            int result = num1/num2;
            return "The Division is: " + result;
        }
    }




}
