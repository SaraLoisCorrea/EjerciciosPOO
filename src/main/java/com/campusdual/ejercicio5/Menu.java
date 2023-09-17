package com.campusdual.ejercicio5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static HashMap<Integer, Patient> patientsMap = new HashMap<>();
    public static ArrayList<Diet> dietsList = new ArrayList<>();

    public static void main(String[] args) {
        // Crear algunos pacientes de ejemplo
        Patient patient = new Patient(1,"Juan", "Perez", 85, 178, 34, 1);
        Patient patient1 = new Patient(2,"Pedro", "Rodriguez", 83, 178, 42, 1);
        Patient patient2 = new Patient(3,"María", "Martinez", 58, 158, 58, 1);
        Patient patient3 = new Patient(4,"Luisa", "López", 75, 169, 25, 1);
        Patient patient4 = new Patient(5,"Lucía", "Iglesias", 68, 161, 61, 1);
        Patient patient5 = new Patient(6,"Marcos", "Álvarez", 72, 172, 43, 1);

        // Agregar los pacientes a la lista de pacientes
        patientsMap.put(patient.getId(), patient);
        patientsMap.put(patient1.getId(), patient1);
        patientsMap.put(patient2.getId(), patient2);
        patientsMap.put(patient3.getId(), patient3);
        patientsMap.put(patient4.getId(), patient4);
        patientsMap.put(patient5.getId(), patient5);

        Diet diet = new Diet();
        dietsList.add(diet);
        diet.setName("Dieta vegana");

        Diet diet1 = new Diet();
        dietsList.add(diet1);
        diet1.setName("Dieta mediterránea");

        Diet diet2 = new Diet();
        dietsList.add(diet2);
        diet2.setName("Dieta mixta");

        // Iniciar el menú del programa de pacientes
        mainMenu();
    }

    // Método para mostrar un menú que nos permita escoger entre Pacientes, dietas o salir del programa.
    public static void mainMenu() {
        System.out.println("########################################################");
        System.out.println("################# Programa de dietas ###################");
        System.out.println("########################################################");
        Integer option;

        // El bucle do-while muestra un menú con tres opciones al usuario y utiliza el método getOption para obtener la elección del usuario, asegurándose de que sea un número entre 1 y 3.
        do {
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1- Gestionar pacientes.");
            System.out.println("2- Gestionar dietas.");
            System.out.println("3- Salir del programa.");
            option = Kb.getOption(1, 3);

            // El método switch se utiliza para realizar diferentes acciones según la opción ingresada por el usuario.
            switch (option) {
                case 1:
                    //patientManagementMenu();
                    showPatients(); // Mostramos la lista de pacientes (solo nombres)
                    System.out.println("Escriba el ID del paciente.");
                    Integer selectedPatientId = Kb.getOption(1,patientsMap.size()); // Permitimos al usuario seleccionar un paciente por nombre
                    Patient patient = patientsMap.get(selectedPatientId); // Obtenemos el paciente seleccionado de la lista por nombre
                    if (patient != null) {
                        PatientProgram patientProgram = new PatientProgram(patient); // Creamos un programa de paciente con el paciente seleccionado
                        patientProgram.patientMenu(); // Iniciamos el menú de gestión del paciente
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;
                case 2:
                    showDiets();
                    Integer selectedDiet = Kb.getOption(1,dietsList.size())-1;
                    Diet diet = dietsList.get(selectedDiet);
                    DietProgram dietProgram = new DietProgram(diet);
                    dietProgram.showMenuProgram();
                    break;
                case 3:
                    System.out.println("Gracias por usar el programa, hasta pronto : ");
                    break;
            }
        } while (option != 3);
    }

    // Método para mostrar la lista de pacientes en la consola (solo nombres)
    public static void showPatients() {
        System.out.println("Lista de Pacientes:");
        for (Integer id : patientsMap.keySet()) {
            System.out.println("ID: " + id + " " + patientsMap.get(id).getName() + " " + patientsMap.get(id).getSurname());
        }
    }

    public static void showDiets() {
        System.out.println("Lista de Dietas: ");
        Integer count = 0;
        for (Diet diet: dietsList){
            count++;
            System.out.println(count + " " + diet.getName());

        }
    }
}