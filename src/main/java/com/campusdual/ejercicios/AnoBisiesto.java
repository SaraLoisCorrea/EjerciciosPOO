package com.campusdual.ejercicios;
import java.util.Scanner;

/** Comprueba si un año es bisiesto con un if **/

public class AnoBisiesto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa un año para comprobar si es bisiesto: ");
        int ano = scanner.nextInt();

        if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
            System.out.println(ano + ", Es un año bisiesto.");
        } else {
            System.out.println(ano + ", No es un año bisiesto.");
        }
    }
}