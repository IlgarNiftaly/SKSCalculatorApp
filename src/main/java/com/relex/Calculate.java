package com.relex;

import java.util.Scanner;

public class Calculate {
    public double calculate(double num1, String operation, double num2, Scanner scan){
        return switch (operation) {
            case "+" -> addition(num1, num2);
            case "-" -> subtraction(num1, num2);
            case "*" -> multiplication(num1, num2);
            case "/" -> {
                while (num2 == 0){
                    System.out.println("Xeta: 0-a bölmə mümkün deyil, yenidən daxil edin: ");
                    num2 = scan.nextDouble();
                }
                yield division(num1, num2);
            }
            case "%" -> {
                while(num2 == 0) {
                    System.out.println("Xeta: 0-a bölmə mümkün deyil, yenidən daxil edin: ");
                    num2 = scan.nextDouble();
                }
                yield percent(num1, num2);
            }
            default -> {
                System.out.println("Xeta: Verilənlər yanlış daxil edildi!");
                yield 0;
            }
        };
    }

    private double addition(double num1, double num2){
        return num1 + num2;
    }

    private double subtraction(double num1, double num2){
        return num1 - num2;
    }

    private double multiplication(double num1, double num2){
        return num1 * num2;
    }

    private double division(double num1, double num2){
        return num1 / num2;
    }

    private double percent(double num1, double num2){
        return num1 % num2;
    }

}
