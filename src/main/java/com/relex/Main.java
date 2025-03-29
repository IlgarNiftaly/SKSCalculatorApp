package com.relex;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome Calculator App");
        System.out.println("existing operation: +, -, *, /");
        Scanner scan = new Scanner(System.in);
        double result = 0;
        double num1 = scan.nextDouble();
        while(true){
            char operation = scan.next().charAt(0);
            if(operation == 'C' || operation == 'c'){
                main(args);
            }
            double num2 = scan.nextDouble();

            Calculate calculate = new Calculate();
            result = calculate.calculate(num1, operation, num2, scan);
            System.out.println("result = " + result);
            num1 = result;
        }

    }
}
