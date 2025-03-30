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
            StringBuilder num = new StringBuilder();

            checkOperation(input.toString());
            App1 app1 = new App1();

            int i = 0;
            while(i < input.length()){

                if(!Character.isDigit(input.charAt(i))){
                    while (i < input.length() && !Character.isDigit(input.charAt(i))){
                        operation.append(input.charAt(i));
                        i++;
                    }
                }
                if(Character.isDigit(input.charAt(i))){
                    while (i < input.length() && Character.isDigit(input.charAt(i))){
                        num.append(input.charAt(i));
                        i++;
                    }
                }

                result = app1.calculate(operation.toString(), Integer.parseInt(num.toString()));

                operation.delete(0, operation.length());
                num.delete(0, num.length());
            }
            System.out.println("result: " + result);
        }

    }

    private static void checkOperation(String operation) throws IOException {
        if(operation.contains("c")){
            restartApplication();
        }

        if(operation.contains("q")){
            System.out.println("sonlandir");
        }
    }

    public static void restartApplication() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-cp", System.getProperty("java.class.path"), "com.relex.App1"
        );
        processBuilder.inheritIO(); // Terminal giriş-çıxışını ötürmək
        processBuilder.start(); // Yeni prosesi başlat
        System.exit(0); // Mövcud prosesi dayandır
    }

    private double calculate(String operation, double num){
        try {
            return switch (operation) {
                case "+" -> addition(num);
                case "-" -> subtraction(num);
                case "*" -> multiplication(num);
                case "/" -> division(num); // Burada division metodunda try-catch var
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


}
