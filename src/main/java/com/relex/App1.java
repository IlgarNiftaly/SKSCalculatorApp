package com.relex;

import java.io.IOException;
import java.util.Scanner;

public class App1 {
    static double result = 0;
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome Calculator App");
        System.out.println("existing operation: +, -, *, /, %");
        Scanner scan = new Scanner(System.in);
        while(true){

            StringBuilder input = new StringBuilder();
            input.append(scan.nextLine().toLowerCase().replaceAll("\\s+", ""));
            StringBuilder operation = new StringBuilder("+");
            StringBuilder num = new StringBuilder("0");

            checkOperation(input);
            App1 app1 = new App1();

            int i = 0;
            while(i < input.length()){
                operation.append("+");
                num.append("0");

                if(!Character.isDigit(input.charAt(i))){
                    operation.delete(0, operation.length());
                    while (i < input.length() && !Character.isDigit(input.charAt(i))){
                        if(operation.length() > 1  && input.charAt(i) == '+'){
                            break;
                        }
                        operation.append(input.charAt(i));
                        i++;
                    }
                }
                if(Character.isDigit(input.charAt(i))){
                    num.delete(0, num.length());
                    while (i < input.length() && Character.isDigit(input.charAt(i))){
                        num.append(input.charAt(i));
                        i++;
                    }
                }
                checkOperation(operation);
                result = app1.calculate(operation, Integer.parseInt(num.toString()));

                operation.delete(0, operation.length());
                num.delete(0, num.length());
            }
            System.out.println("result: " + result);
        }

    }

    private static void checkOperation(StringBuilder ope) throws IOException {
        if(ope.length() > 1){
            if(ope.charAt(0) == '+' || ope.charAt(0) == '-' || ope.charAt(0) == '*' || ope.charAt(0) == '/' || ope.charAt(0) == '%'){
                ope.deleteCharAt(0);
            }
        }
        String operation = ope.toString();

        if(operation.equalsIgnoreCase("c")){
            restartApplication();
        }

        if(operation.equalsIgnoreCase("q")){
            System.out.println("Proqram sonlandirildi");
            System.exit(0);
        }
    }

    private double calculate(StringBuilder ope, double num){
        String operation = ope.toString();

        try {
            return switch (operation) {
                case "+" -> addition(num);
                case "-" -> subtraction(num);
                case "*" -> multiplication(num);
                case "/" -> division(num);
                case "%" -> percent(num);
                case "sq" -> sq(num);
                default -> {
                    System.out.println("Xeta: Verilenler yanlis daxil edildi!");
                    yield 0;
                }
            };
        } catch (Exception e) {
            System.out.println("Xeta: " + e.getMessage()); // Hər hansı digər səhvi burada tuturuq
            return 0; // Qlobal səhv nəticəsi qaytarırıq
        }
    }

    private double addition(double num){
        return result + num;
    }

    private double subtraction(double num){
        return result - num;
    }

    private double multiplication(double num){
        return result * num;
    }

    private double division(double num){
        return result / num;
    }

    private double percent(double num){
        return result % num;
    }

    private double sq(double num){
        return result * result;
    }



    public static void restartApplication() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-cp", System.getProperty("java.class.path"), "com.relex.App1"
        );
        processBuilder.inheritIO(); // Terminal giriş-çıxışını ötürmək
        processBuilder.start(); // Yeni prosesi başlat
        System.exit(0); // Mövcud prosesi dayandır
    }

}
