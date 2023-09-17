package com.campusdual.ejercicio5;

import java.util.ArrayList;

public class Patient {
    // ATRIBUTOS DE PACIENTE
    private Integer id; // Nombre del paciente
    private String name; // Nombre del paciente
    private String surname; // Apellido del paciente
    private Integer weight; // Peso del paciente
    private Integer height; // Altura del paciente
    private Integer age; // Edad del paciente
    private Integer genre; // Género del paciente (1 para hombre, 2 para mujer)

    // CONSTRUCTOR DE LA PACIENTE
    public Patient(Integer id, String name, String surname, Integer weight, Integer height, Integer age, Integer genre) {
        // Inicializar los atributos del paciente con los valorens proporcionados al crear una instancia de la clase
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.genre = genre;
        // Lista de dietas para la semana del paciente
        ArrayList<Diet> weekDiet = null; // Inicializar la lista de dietas como nula al principio
    }

    public Patient(String name, String surname, Integer weight, Integer height, Integer age, Integer genre) {
        this.name = name;
        this.surname = surname;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.genre = genre;
        // this.id =
    }

    public void showInfo(){
        System.out.println(id);
        System.out.println(name);
        System.out.println(surname);
        System.out.println(weight);
        System.out.println(height);
        System.out.println(age);
        System.out.println(genre);

    }


    // Métodos para acceder a los atributos del paciente y modificarlos

    // Obtener el nombre del paciente
    public String getName() {
        return name;
    }

    // Obtener el apellido del paciente
    public String getSurname() {
        return surname;
    }

    // Obtener el peso del paciente
    public Integer getWeight() {
        return weight;
    }

    // Obtener la altura del paciente
    public Integer getHeight() {
        return height;
    }

    // Obtener la edad del paciente
    public Integer getAge() {
        return age;
    }

    // Establecer el nombre del paciente
    public void setName(String name) {
        this.name = name;
    }

    // Establecer el apellido del paciente
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Establecer el peso del paciente
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    // Establecer la altura del paciente
    public void setHeight(Integer height) {
        this.height = height;
    }

    // Establecer la edad del paciente
    public void setAge(Integer age) {
        this.age = age;
    }

    // Establecer el género del paciente
    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    // Obtener el género del paciente
    public Integer getGenre() {
        return genre;
    }

    public int getId() { return id;
    }
}