package com.campusdual.ejercicio5;

import java.util.InputMismatchException;

public class PatientProgram {
    private Patient patient;
    private final DietProgram dietProgram;


    public PatientProgram(Patient patient) {
        this.patient = patient;
        this.dietProgram = new DietProgram();
        this.dietProgram.setPatient(patient);
    }

    public void patientMenu() {
        System.out.println("########################################################");
        System.out.println("################# Programa de pacientes ###################");
        System.out.println("########################################################");

        Integer option;

        do {
            displayMenuOptions();
            option = getOption(1, 3);

            switch (option) {
                case 1:
                    displayPatientInfo();
                    break;
                case 2:
                    dietProgram.showMenuProgram();
                    break;
                case 3:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
            }
        } while (option != 3);
    }

    private void displayMenuOptions() {
        System.out.println("    Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-  Mostrar información de un paciente.");
        System.out.println("2-  Gestión de dietas.");
        System.out.println("3-  Salir del programa.");
    }

    private void changePatient() {
        Menu.showPatients();
        int numberPatient = getOption(0, Menu.patientsMap.size() - 1);
        this.patient = Menu.patientsMap.get(numberPatient);
        System.out.println("Ha cambiado al paciente " + this.patient.getName() + this.patient.getSurname());
        this.dietProgram.setPatient(patient);
    }

    private void displayPatientInfo() {
        if (this.patient != null) {
            this.patient.showInfo();
        } else {
            System.out.println("No se ha seleccionado ningún paciente.");
        }
    }

    private Integer getOption(Integer min, Integer max) {
        Integer option = 0;
        boolean notValid = true;

        do {
            try {
                option = Kb.forceNextInt();
                notValid = option < min || option > max;
            } catch (InputMismatchException e) {
                System.out.println("La opción debe ser un número.");
                Kb.nextLine();
            }
            if (notValid) {
                System.out.println("Opción no válida, se requiere un número entre " + min + " y " + max);
            }
        } while (notValid);

        return option;
    }
}