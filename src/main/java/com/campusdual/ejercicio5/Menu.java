package com.campusdual.ejercicio5;

//--Teniendo en cuenta el programa anterior(ejercicio4):
//--Realiza los cambios necesarios para:
//  -La dieta ya no será única si no que se guardará en una lista de dietas. Esta lista tendrá un nombre para poder ser recuperado. Será necesario cambiar el menú para gestionar esta lista. Ahora en ved de crear/reemplazar dieta lo que haremos será:
//    a- Agregar dieta: Añade una dieta a la lista. Podemos reutilizar el menú antiguo de crear/reemplazar
//    b- Mostrar detalles de dieta: Muestra los detalles de una dieta y permite modificarla
//    c- Eliminar dieta: Elimina una dieta de la lista(Siempre que no la tenga asignada un paciente)
//
//  -Se pueden dar de alta personas: Las personas tendrán los siguientes atributos: nombre, apellidos, peso, altura, edad y sexo.
//    -Las personas podrán tener asignadas una lista de dietas que se catalogarán de lunes a domingo sacadas de la lista de dietas anterior.
//    -Se agregara un apartado nuevo al menú de "Gestión de pacientes" con las siguientes funcionalidades:
//      1- Dar de alta un paciente : Agregará un paciente nuevo a la lista de pacientes
//      2- Listar y Mostrar detalles de un paciente : Mostrará el detalle de un paciente así como todas sus dietas listadas de Lunes a domingo
//      3- Asignar una dieta: Muestra la lista de dietas general y puede asignarle una a un día de la semana del paciente.
//      4- Dar de baja un paciente: Elimina los datos de un paciente(No borra sus dietas asignadas dado que pueden estar asignadas a otro paciente)

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static ArrayList<Patient> patientsList = new ArrayList<>();
    public static ArrayList<Diet> dietsList = new ArrayList<>();

    public static void main(String[] args) {
        // Crear algunos pacientes de ejemplo
        Patient patient = new Patient("Juan", "Perez", 85, 178, 34, 1);
        Patient patient1 = new Patient("Pedro", "Rodriguez", 83, 178, 34, 1);


        // Agregar los pacientes a la lista de pacientes
        patientsList.add(patient);
        patientsList.add(patient1);

        Diet diet = new Diet();
        dietsList.add(diet);

        Diet diet1 = new Diet();
        dietsList.add(diet1);

        Diet diet2 = new Diet();
        dietsList.add(diet2);

        // Mostrar la lista de pacientes
        //showPatients();

        // Iniciar el menú del programa de pacientes
        //patientProgram.patientMenu();
        mainMenu();
    }

    // Método para mostrar un menú que nos permita escoger entre Pacientes, dietas o salir del programa.
    public static void mainMenu() {             // Cuando no trabajemos con objetos hay que meter el static.
        System.out.println("########################################################");
        System.out.println("################# Programa de dietas ###################");
        System.out.println("########################################################");
        Integer option;

        // El bucle do-while muestra un menú con cuatro opciones al usuario y utiliza el método getOption para obtener la elección del usuario, asegurándose de que sea un número entre 1 y 4.
        do {
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Seleccionar paciente.");
            System.out.println("2-Seleccionar dieta.");
            System.out.println("3-Crear un nuevo paciente.");
            System.out.println("4-Salir del programa");
            option = Kb.getOption(1, 4);

            //El método switch se utiliza para realizar diferentes acciones según la opción ingresada por el usuario. Las opciones 1, 2 y 3
            //llaman a otros métodos (createMenu, showDetailsMenu y addFoodMenu, respectivamente), mientras que
            //la opción 4 imprime un mensaje de despedida y sale del programa.
            switch (option) {
                case 1:
                    showPatients();     //mostrar pacientes
                    Integer selectedPatient = Kb.getOption(0,patientsList.size()-1);        // Creamos una variable para guardar el input del teclado
                    Patient patient = patientsList.get(selectedPatient);        // Creamos una variable para guardar un paciente, seleccionado de la "patientList" con el número que guardamos en selectedPatient
                    PatientProgram patientProgram = new PatientProgram(patient);        // Creamos el objeto patientProgram y le añadimos el paciente que necesita para ser creado como atributo
                    patientProgram.patientMenu();       // activa el método patientMenu
                    break;
                case 2:
                    showDiet();     // mostramos dietas
                    Integer selectedDiet = Kb.forceNextInt();       // Creamos una variable para guardar el input del teclado
                    Diet diet = dietsList.get(selectedDiet);        // Creamos una variable para guardar la dieta seleccionada de la dietList
                    DietProgram dietProgram = new DietProgram(diet);        // Creamos el objeto dietProram y añadimos la dieta
                    dietProgram.showMenuProgram();      // activa el método dietMenu
                    break;
                case 3:
                    createNewPatient();
                    break;
                case 4:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
            }
        } while (option != 4);
    }

    // Método que se encarga de mostrar la lista de pacientes en la consola
    public static void showPatients() {
        for (int i = 0; i < patientsList.size(); i++) {         //recorre la lista de pacientes almacenada en la variable patientsList
            System.out.println(i + " " + patientsList.get(i).getName());
            // i: Representa el índice del paciente en la lista.
            // patientsList.get(i): Obtiene el objeto paciente en la posición i de la lista.
            // .getName(): Llama al método getName() del objeto paciente para obtener su nombre.
        }
    }

    // Método que se encarga de seleccionar una dieta
    public static void showDiet() {
        for (int i = 0; i < dietsList.size(); i++) {
            System.out.println(i + " " );
            // i: Representa el índice del paciente en la lista.
            // patientsList.get(i): Obtiene el objeto paciente en la posición i de la lista.
            // .getName(): Llama al método getName() del objeto paciente para obtener su nombre.
        }
    }

    // Método para crear un nuevo paciente
    public static Patient createNewPatient() {
        String name = Kb.nextLine("Nombre, nuevo paciente: ");
        String surname = Kb.nextLine("Apellidos, nuevo paciente");

        //TODO
        Integer weight = 55; // Peso del paciente
        Integer height = 158; // Altura del paciente
        Integer age = 51; // Edad del paciente
        Integer genre = 0;
        Patient newPatient = new Patient(name, surname, weight, height, age, genre);        // rellena los datos
        patientsList.add(newPatient);
        return newPatient;
    }

}