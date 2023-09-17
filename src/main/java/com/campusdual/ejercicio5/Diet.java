package com.campusdual.ejercicio5;

import com.campusdual.ejercicio5.exceptions.*;

import java.util.ArrayList;
import java.util.List;
public class Diet {
    // Constantes para los tipos de errores posibles
    public static final String OK = "OK";
    public static final String MAX_CALORIES_REBASE = "MAX_CALORIES_REBASE";
    public static final String MAX_CARBS_REBASE = "MAX_CARBS_REBASE";
    public static final String MAX_FATS_REBASE = "MAX_FATS_REBASE";
    public static final String MAX_PROTEINS_REBASE = "MAX_PROTEINS_REBASE";

    private Integer maxCalories;        // Máximo de calorías permitidas en la dieta
    private Integer maxCarbs;       // Máximo de carbohidratos permitidos en la dieta
    private Integer maxFats;        // Máximo de grasas permitidas en la dieta
    private Integer maxProteins;        // Máximo de proteínas permitidas en la dieta
    private String name; // Nombre de la dieta
    private List<Intake> intakes;       // Lista de ingestas de alimentos en la dieta

    // Constructor sin parámetros
    public Diet(){
        this.intakes = new ArrayList<>();
    }

    // Constructor con el máximo de calorías como parámetro
    public Diet(Integer maxCalories){
        this.maxCalories=maxCalories;
        this.intakes = new ArrayList<>();
    }

    // Constructor con el máximo de grasas, carbohidratos y proteínas como parámetros
    public Diet(Integer maxFats, Integer maxCarbs, Integer maxProteins){
        this.maxCarbs=maxCarbs;
        this.maxFats=maxFats;
        this.maxProteins=maxProteins;
        this.intakes = new ArrayList<>();
    }

    // Constructor que calcula el máximo de calorías en función del género y otros datos del paciente
    public Diet(Boolean women, Integer age, Integer height, Integer weight){
        if(women){
            maxCalories = (int) ((10*weight) + (6.25*height))-(5*age)-161;
        }else{
            maxCalories = (int) ((10*weight) + (6.25*height))-(5*age)+5;
        }
        this.intakes = new ArrayList<>();
    }

    // Método para agregar una comida a la dieta con la cantidad en gramos
    public void addFood(Food food, Integer grams) throws MaxValuedReachedException {
        Intake intake = new Intake(food,grams);
        String validation = isValidIntake(intake);
        if(OK.equalsIgnoreCase(validation)){
            intakes.add(intake);
        }else{
            if(MAX_CALORIES_REBASE.equalsIgnoreCase(validation)){
                throw new MaxCaloriesReachedException();
            }
            if(MAX_CARBS_REBASE.equalsIgnoreCase(validation)){
                throw new MaxCarbsReachedException();
            }
            if(MAX_FATS_REBASE.equalsIgnoreCase(validation)){
                throw new MaxFatsReachedException();
            }
            if(MAX_PROTEINS_REBASE.equalsIgnoreCase(validation)){
                throw new MaxProteinsReachedException();
            }
        }
    }

    // Método para validar si una ingesta es válida
    private String isValidIntake(Intake intake){
        Integer actualCaories = getTotalCalories();
        if(this.maxCalories != null && this.maxCalories < (actualCaories + intake.calculatedCalories())){
            return MAX_CALORIES_REBASE;
        }
        Integer actualCarbs = getTotalCarbs();
        if(this.maxCarbs != null && this.maxCarbs < actualCarbs + intake.calculatedCarbos()){
            return MAX_CARBS_REBASE;
        }
        Integer actualFats = getTotalFats();

        if(this.maxFats != null && this.maxFats < actualFats + intake.calculatedFats()){
            return MAX_FATS_REBASE;
        }
        Integer actualProteins = getTotalProteins();
        if(this.maxProteins != null && this.maxProteins < actualProteins + intake.calculatedProteins()){
            return MAX_PROTEINS_REBASE;
        }
        return OK;
    }

    // Método para obtener el total de calorías en la dieta
	public Integer getTotalCalories(){
        Integer totalCalories = 0;
        for(Intake intake:intakes){
            totalCalories = totalCalories+ intake.calculatedCalories();
        }
        return totalCalories;
    }


    // Método para obtener el total de carbohidratos en la dieta
	public Integer getTotalCarbs(){
        Integer totalCarbs = 0;
        for(Intake intake:intakes){
            totalCarbs = totalCarbs + intake.calculatedCarbos();
        }
        return totalCarbs;
    }

    // Método para obtener el total de grasas en la dieta
	public Integer getTotalFats(){
        Integer totalFats = 0;
        for(Intake intake:intakes){
            totalFats = totalFats + intake.calculatedFats();
        }
        return totalFats;
    }

    // Método para obtener el total de proteínas en la dieta
	public Integer getTotalProteins(){
        Integer totalProtein = 0;
        for(Intake intake: intakes){
            totalProtein = totalProtein + intake.calculatedProteins();
        }
        return totalProtein;
    }

    // Método para obtener el número de alimentos en la dieta
    public Integer getFoodNumber(){
        return intakes.size();
    }

    // Métodos para obtener y establecer los máximos de calorías, carbohidratos, grasas y proteínas
    public Integer getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(Integer maxCalories) {
        this.maxCalories = maxCalories;
    }

    public Integer getMaxCarbs() {
        return maxCarbs;
    }

    public void setMaxCarbs(Integer maxCarbs) {
        this.maxCarbs = maxCarbs;
    }

    public Integer getMaxFats() {
        return maxFats;
    }

    public void setMaxFats(Integer maxFats) {
        this.maxFats = maxFats;
    }

    public Integer getMaxProteins() {
        return maxProteins;
    }

    public void setMaxProteins(Integer maxProteins) {
        this.maxProteins = maxProteins;
    }

    // Método para obtener la lista de ingestas de alimentos en la dieta
    public List<Intake> getIntakes() {
        return intakes;
    }

    // Método para establecer la lista de ingestas de alimentos en la dieta
    public void setIntakes(List<Intake> intakes) {
        this.intakes = intakes;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // Método para obtener la descripción de las ingestas de alimentos en la dieta
    public String getDietIntakes() {
        String result = "";
        boolean first=true;
        for(Intake intake:intakes){
            if(first){
                first = false;
                result = intake.getName()+":"+intake.getGrams();
            }else{
                result = result + ", "+intake.getName()+":"+intake.getGrams();
            }
        }
        return result;
    }
}
