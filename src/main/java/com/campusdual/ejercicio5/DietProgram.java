package com.campusdual.ejercicio5;

import com.campusdual.ejercicio5.exceptions.MaxCaloriesReachedException;
import com.campusdual.ejercicio5.exceptions.MaxCarbsReachedException;
import com.campusdual.ejercicio5.exceptions.MaxFatsReachedException;
import com.campusdual.ejercicio5.exceptions.MaxProteinsReachedException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class DietProgram {

    // Se declaran tres variables miembro privadas: diet de tipo Diet (para representar la dieta actual),
    private Diet diet=null;
    private List<Food> foodList;
    private Patient patient;

    // El constructor de la clase DietProgram inicializa foodList como una nueva instancia de ArrayList, lo que crea una lista vacía para almacenar alimentos cuando se crea una instancia de DietProgram.
    public DietProgram(){

        foodList = new ArrayList<>();
    }
    public DietProgram(Diet diet){

        foodList = new ArrayList<>();
        this.diet = diet;
    }


    // El método showMenuProgram muestra un encabezado para el programa de dietas en la consola
    public void showMenuProgram(){
        System.out.println("########################################################");
        System.out.println("################# Programa de dietas ###################");
        System.out.println("########################################################");
        Integer option;

        // El bucle do-while muestra un menú con cuatro opciones al usuario y utiliza el método getOption para obtener la elección del usuario, asegurándose de que sea un número entre 1 y 4.
        mainLoop: do {
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Reiniciar dieta.");
            System.out.println("2-Mostrar información de la dieta.");
            System.out.println("3-Agregar alimento al plan actual.");
            System.out.println("4-Volver al menú principal.");
            option = Kb.getOption(1,4);

            //El método switch se utiliza para realizar diferentes acciones según la opción ingresada por el usuario. Las opciones 1, 2 y 3
            //llaman a otros métodos (createMenu, showDetailsMenu y addFoodMenu, respectivamente), mientras que
            //la opción 4 imprime un mensaje de despedida y sale del programa.
            switch (option){
                case 1:
                    createMenu();
                    break;
                case 2:
                    showDetailsMenu();
                    break;
                case 3:
                    addFoodMenu();
                    break;
                case 4:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break mainLoop;
            }
        }while(option != 4);
    }

    // El método addFoodMenu se encarga de agregar alimentos a la dieta actual.
    private void addFoodMenu() {

        // Se verifica si diet es null. Si es null, significa que no se ha iniciado una dieta, por lo que muestra un mensaje de error y sale del método.
        if(this.diet==null){
            System.out.println("Para agregar alimentos hace falta iniciar una dieta.");
            return;
        }

        // Se muestra un encabezado para la sección de agregación de alimentos en la consola.
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Agregar Alimentos a la dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        // Se muestra un menú con dos opciones: agregar un nuevo alimento o agregar un alimento existente.
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Agregar un nuevo alimento.");
        System.out.println("2-Agregar un alimento ya existente.");

        // Se utiliza getOption nuevamente para obtener la elección del usuario y luego se utiliza una instrucción switch para
        // manejar las dos opciones disponibles.
        Integer option = Kb.getOption(1,2);
        switch (option){

            // Si el usuario elige agregar un nuevo alimento, se le solicita ingresar información sobre el nuevo alimento,
            // como el nombre, los carbohidratos, las grasas, las proteínas y los gramos.
            case 1:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Datos de nuevo alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Nombre del alimento:");
                String name = Kb.nextLine();
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                System.out.println("Gramos:");
                Integer grams = Kb.forceNextInt();

                //Se crea un nuevo objeto Food con la información ingresada por el usuario y se llama al método validateAndAddFoodToDiet
                // para validar y agregar el alimento a la dieta. Luego, el alimento se agrega a la lista foodList.
                Food newFood = new Food(name,carbs,fats,proteins);
                validateAndAddFoodToDiet(newFood,grams);
                foodList.add(newFood);
                break;

                // Si el usuario elige agregar un alimento existente, se ejecuta este bloque de código.
            case 2:
                // Se verifica si foodList está vacía. Si no hay alimentos previos en la lista, se muestra un mensaje de error y se sale del método.
                if(foodList.size()==0){
                    System.out.println("Para agregar un alimento existente, tienen que existir alimentos previos");
                    return;
                }

                // Se muestra un menú con la lista de alimentos existentes en foodList. El usuario puede elegir un alimento
                // de la lista o cancelar la operación. Se utiliza getOption nuevamente para obtener la elección del usuario.
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escoja un alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer i = 1;
                for(Food food:foodList){
                    System.out.println(i+"- "+food.getName());
                    i++;
                }
                System.out.println(i+"- Cancelar.");
                Integer element = Kb.getOption(1,i);
                if(element==i){
                    System.out.println("Cancelando alimento.");
                    return;
                }

                // Se obtiene el alimento seleccionado de foodList y se solicita al usuario ingresar la cantidad de gramos.
                // Luego, se llama al método validateAndAddFoodToDiet para validar y agregar el alimento a la dieta.
                Food storedFood = foodList.get(element-1);
                System.out.println("indique el número de gramos de "+storedFood.getName());
                Integer foodGrams = Kb.forceNextInt();
                validateAndAddFoodToDiet(storedFood,foodGrams);

                // Se cierra el bloque switch que maneja las opciones de agregar alimentos.
                break;
        }
    }

    // El método validateAndAddFoodToDiet se encarga de validar y agregar alimentos a la dieta.
    // Dentro de este método, se intenta agregar el alimento food a la dieta actual (diet) utilizando el método addFood.
    // Se manejan diferentes excepciones personalizadas (MaxCaloriesReachedException, MaxCarbsReachedException, MaxFatsReachedException,
    // MaxProteinsReachedException) que pueden ocurrir si se alcanzan ciertos límites en la dieta. Si se produce una excepción,
    // se muestra un mensaje de error correspondiente. Cualquier otra excepción no manejada se convierte en una RuntimeException.
    private void validateAndAddFoodToDiet(Food food, Integer grams){
        try{
            this.diet.addFood(food,grams);
        }catch (MaxCaloriesReachedException ecal){
            System.out.println("Se ha alcanzado el máximo valor de calorías permitido.");
        }catch (MaxCarbsReachedException ecar){
            System.out.println("Se ha alcanzado el máximo valor de carbohidratos permitido.");
        }catch (MaxFatsReachedException efat){
            System.out.println("Se ha alcanzado el máximo valor de grasas permitido.");
        }catch (MaxProteinsReachedException epro){
            System.out.println("Se ha alcanzado el máximo valor de proteínas permitido.");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // El método createMenu se encarga de crear o reiniciar la dieta.
    private void createMenu() {

        // Se muestra un encabezado para la sección de creación de la dieta en la consola.
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear/reiniciar dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        // Se muestra un menú con cuatro opciones diferentes para crear la dieta.
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Dieta sin límite.");
        System.out.println("2-Dieta por calorías.");
        System.out.println("3-Dieta por macronutrientes.");
        System.out.println("4-Dieta por datos personales.");

        // Se utiliza getOption para obtener la elección del usuario y luego se utiliza una instrucción switch para manejar
        // las cuatro opciones disponibles.
        Integer option = Kb.getOption(1,4);
        switch (option){
            case 1:
                this.diet = new Diet();
                System.out.println("Se ha creado una dieta sin límites");
                break;

                // Si el usuario elige crear una dieta por calorías, se ejecuta este bloque de código.
            case 2:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba número de calorias");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer calories = Kb.forceNextInt();
                this.diet = new Diet(calories);
                System.out.println("Se ha creado una dieta con "+calories+" calorías máximas");
                break;

                // Si el usuario elige crear una dieta por macronutrientes, se ejecuta este bloque de código.
            case 3:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los macronutrientes");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                this.diet = new Diet(fats,carbs,proteins);
                System.out.println("Se ha creado una dieta con Carbohidratos:"+carbs+", Grasas:"+fats+" ,Proteínas:"+proteins);
                break;
            case 4:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los datos personales del paciente");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Peso:");
                Integer weight = Kb.forceNextInt();
                System.out.println("Altura:");
                Integer height = Kb.forceNextInt();
                System.out.println("Edad:");
                Integer age = Kb.forceNextInt();
                System.out.println("Mujer u Hombre(m/h):");
                String sexCharacter = Kb.nextLine();
                this.diet = new Diet("m".equalsIgnoreCase(sexCharacter),age,height,weight);
                System.out.println("Se ha creado una dieta de "+this.diet.getMaxCalories()+" calorías máximas");
                break;
        }
    }

    private void showDetailsMenu() {
        if(this.diet!=null){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Detalles de la dieta");
            System.out.println(this.diet.getName());
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            if(this.diet.getMaxCalories()!=null){
                System.out.println("El número máximo de calorías es:"+this.diet.getMaxCalories());
            }
            if(this.diet.getMaxCarbs() != null || this.diet.getMaxFats() != null || this.diet.getMaxProteins() != null){
                System.out.println("Los valores máximos de macronutrientes son: Carbohidratos:"+this.diet.getMaxCarbs()+" , Grasas:"+this.diet.getMaxFats()+" , Proteinas:"+this.diet.getMaxProteins());
            }
            System.out.println("Número de alimentos de la dieta:"+this.diet.getFoodNumber());
            System.out.println("Calorías:"+this.diet.getTotalCalories());
            System.out.println("Carbohidratos:"+this.diet.getTotalCarbs());
            System.out.println("Grasas:"+this.diet.getTotalFats());
            System.out.println("Proteínas:"+this.diet.getTotalProteins());
            System.out.println("Alimentos de la dieta:"+this.diet.getDietIntakes());
        }else{
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("La dieta no esta iniciada");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
