package com.relex;

import java.io.IOException;
import java.util.Scanner;

public class App2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome Calculator App");
        System.out.println("existing operation: +, -, *, /, %");
        Scanner scan = new Scanner(System.in);
        double result = 0;
        double num1 = scan.nextDouble();
        while(true){
            String operation = String.valueOf(scan.next().charAt(0));
            checkOperation(operation);
            double num2 = scan.nextDouble();

            Calculate calculate = new Calculate();
            result = calculate.calculate(num1, operation, num2, scan);
            System.out.printf("result: %.2f\n", result);
            num1 = result;
        }
    }

    public static void restartApplication() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-cp", System.getProperty("java.class.path"), "com.relex.App2"
        );
        processBuilder.inheritIO(); // Terminal giriş-çıxışını ötürmək
        processBuilder.start(); // Yeni prosesi başlat
        System.exit(0); // Mövcud prosesi dayandır
    }

    private static void checkOperation(String operation) throws IOException {
        if(operation.equalsIgnoreCase("C")){
            restartApplication();
        }

        if(operation.equalsIgnoreCase("Q")){
            System.out.println("sonlandir");
        }
    }

}
