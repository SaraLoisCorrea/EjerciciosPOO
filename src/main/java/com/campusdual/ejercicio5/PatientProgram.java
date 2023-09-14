package com.campusdual.ejercicio5;

import java.util.InputMismatchException;

public class PatientProgram {
    private Patient patient;

    // Constructor de la clase que toma un objeto de tipo Patient como argumento
    public PatientProgram(Patient patient) {
        this.patient = patient;
    }

    // Método principal para el menú del programa de pacientes
    public void patientMenu() {
        System.out.println("########################################################");
        System.out.println("################# Programa de pacientes ###################");
        System.out.println("########################################################");
        Integer option;
        do {
            System.out.println("    Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-  Seleccionar un paciente.");
            System.out.println("2-  Dar de alta un nuevo paciente");
            System.out.println("3-  Mostrar información de un paciente.");
            System.out.println("4-  Añadir una dieta");
            System.out.println("5-  Dar de baja un paciente.");
            System.out.println("6-  Salir del programa.");

            // Obtiene la opción del usuario dentro del rango de 1 a 6
            option = getOption(1, 6);
            Integer numberPatient = null;
            switch (option) {
                case 1:
                    // Mostrar lista de pacientes
                    Menu.showPatients();
                    numberPatient = getOption(0, Menu.patientsList.size());
                    this.patient = Menu.patientsList.get(numberPatient);
                    System.out.println("Ha cambiado al paciente " + this.patient.getName());
                    break;
                case 2:
                    // Dar de alta un nuevo paciente (falta implementar)
                    // Crear una variable para guardar los datos que recibimos con Kb, lo hacemos así para no tener que hacer varios sout
                    /**
                    String name = Kb.nextLine("Nombre, nuevo paciente: ");
                    String surname = Kb.nextLine("Apellidos, nuevo paciente");

                    //TODO
                    Integer weight = 55; // Peso del paciente
                    Integer height = 158; // Altura del paciente
                    Integer age = 51; // Edad del paciente
                    Integer genre = 0;
                    Patient newPatient = new Patient(name, surname, weight, height, age, genre);        // rellena los datos
                    Menu.patientsList.add(newPatient);              // añade a Lucía a la lista   **/

                    Patient newPatient = Menu.createNewPatient();
                    this.patient = newPatient;          // cambia del paciente actual por el nuevo paciente "lucía"
                    break;
                case 3:
                    // Mostrar información del paciente (falta implementar)
                    this.patient.showInfo();        // le damos la orden al paciente actual (patientprogram.this.patient) de mostrar su información con show info
                    break;
                case 4:
                    // Iniciar el programa de dieta
                    DietProgram dietProgram = new DietProgram();
                    dietProgram.setPatient(this.patient);
                    dietProgram.showMenuProgram();
                    break;
                case 5:
                    // Dar de baja a un paciente (falta implementar)
                    Menu.showPatients();
                    break;
                case 6:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
            }
        } while (option != 6);
    }

    // Método privado para obtener una opción válida dentro de un rango
    private Integer getOption(Integer min, Integer max) {
        Integer option = 0;
        Boolean notValid = true;
        do {
            try {
                option = Kb.forceNextInt();
                notValid = option < min || option > max;
            } catch (InputMismatchException e) {
                System.out.println("La opción debe ser un número");
                Kb.nextLine();
            }
            if (notValid) {
                System.out.println("Opción no válida, se requiere un número entre " + min + " y " + max);
            }
        } while (notValid);
        return option;
    }
}
