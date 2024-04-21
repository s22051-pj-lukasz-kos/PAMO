package com.example.bmiext.ui.recipes;

import java.util.List;

public class Recipe {
    private String name;
    private String imagePath;
    private List<String> ingredients;
    private List<String> instructions;

    public Recipe(String name, String imagePath, List<String> ingredients, List<String> instructions) {
        this.name = name;
        this.imagePath = imagePath;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Recipe() {
    }

    public String getName() {
        return this.name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}