package com.campusdual.ejercicio5;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Kb {
    // Método para leer un entero desde la entrada estándar (teclado)
    public static Integer nextInt(){
        Scanner keyboard = new Scanner(System.in); // Crear un objeto Scanner para la entrada estándar
        String resultString = keyboard.nextLine(); // Leer una línea de texto desde el teclado
        Integer result = 0; // Inicializar la variable de resultado como 0 (por defecto)

        try {
            result = Integer.parseInt(resultString); // Intentar convertir la cadena en un entero
        } catch (Exception e) {
            throw new InputMismatchException(); // Capturar cualquier excepción y lanzar una excepción InputMismatch
        }
        return result; // Devolver el entero resultante
    }

    // Método para leer un entero desde la entrada estándar con manejo de excepciones
    public static Integer forceNextInt(){
        Integer resultado = null; // Inicializar el resultado como nulo
        boolean notvalid = true; // Bandera para controlar si la entrada es válida

        do {
            try {
                resultado = nextInt(); // Intentar leer un entero utilizando el método nextInt()
                notvalid = false; // Si se ejecuta sin errores, marcar como entrada válida
            } catch (InputMismatchException e) {
                System.out.println("Es necesario que sea un número"); // En caso de excepción, mostrar un mensaje de error
            }
        } while (notvalid); // Repetir el ciclo hasta que se proporcione una entrada válida

        return resultado; // Devolver el entero resultante
    }

    // Método para leer una línea de texto desde la entrada estándar (teclado)
    public static String nextLine(){
        Scanner keyboard = new Scanner(System.in); // Crear un objeto Scanner para la entrada estándar
        String result = keyboard.nextLine(); // Leer una línea de texto desde el teclado
        return result; // Devolver la cadena de texto resultante
    }

    public static String nextLine(String text){
        System.out.println(text);
        Scanner keyboard = new Scanner(System.in); // Crear un objeto Scanner para la entrada estándar
        String result = keyboard.nextLine(); // Leer una línea de texto desde el teclado
        return result; // Devolver la cadena de texto resultante
    }

    // Se define un método privado llamado getOption que toma dos enteros min y max. Este método se utiliza para obtener una opción de menú válida de entrada del usuario.
    public static Integer getOption(Integer min,Integer max){

        // Se declaran dos variables locales option e notvalid para almacenar la opción ingresada por el usuario y para verificar si la opción es válida o no.
        Integer option = 0;
        Boolean notvalid = true;

        // Se utiliza un bucle do-while para solicitar al usuario una opción hasta que se ingrese una opción válida.
        // En el bloque try, se intenta leer un número entero del teclado utilizando Kb.forceNextInt().
        // Si el usuario ingresa algo que no es un número, se captura una excepción InputMismatchException y se muestra un mensaje de error.

        do{
            try {
                option = Kb.forceNextInt();
                notvalid = option<min || option>max;
            }catch (InputMismatchException e){
                System.out.println("La opción debe ser un número");
                Kb.nextLine();
            }
            if(notvalid){
                System.out.println("Opción no válida, se requiere un número entre "+min+" y "+max);
            }
            // Si la opción no es válida (fuera del rango especificado), se muestra un mensaje de error y se repite la solicitud de opción hasta que se ingrese una opción válida.
        }while(notvalid);

        // Finalmente, el método getOption devuelve la opción válida ingresada por el usuario.
        return option;
    }
}