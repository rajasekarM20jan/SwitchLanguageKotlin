package com.example.switchlanguagekotlin.model;

public class RecipeListModel {
    String RecipeName, Ingredients, preparationTime, procedure, dietary, mealType;

    public RecipeListModel(String recipeName, String ingredients, String preparationTime, String procedure, String dietary, String mealType) {
        RecipeName = recipeName;
        Ingredients = ingredients;
        this.preparationTime = preparationTime;
        this.procedure = procedure;
        this.dietary = dietary;
        this.mealType=mealType;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getDietary() {
        return dietary;
    }

    public void setDietary(String dietary) {
        this.dietary = dietary;
    }
}
