package com.campusdual.ejercicio5;

import java.util.ArrayList;

public class Patient {
    // ATRIBUTOS DE PACIENTE
    private String name; // Nombre del paciente
    private String surName; // Apellido del paciente
    private Integer weight; // Peso del paciente
    private Integer height; // Altura del paciente
    private Integer age; // Edad del paciente
    private Integer genre; // Género del paciente (1 para hombre, 2 para mujer)
    private ArrayList<Diet> weekDiet; // Lista de dietas para la semana del paciente

    // CONSTRUCTOR DE LA PACIENTE
    public Patient(String name, String surName, Integer weight, Integer height, Integer age, Integer genre) {
        // Inicializar los atributos del paciente con los valores proporcionados al crear una instancia de la clase
        this.name = name;
        this.surName = surName;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.genre = genre;
        this.weekDiet = null; // Inicializar la lista de dietas como nula al principio
    }


    public void showInfo(){
        System.out.println(name);
        System.out.println(surName);
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
    public String getSurName() {
        return surName;
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
    public void setSurName(String surName) {
        this.surName = surName;
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
}