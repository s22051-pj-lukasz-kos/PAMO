package com.example.bmiext.ui.recipes;

import java.util.List;

public class Recipe {
    private String name;
    private List<String> ingredients;
    private String instructions;
    private String nutritionalInfo;

    public Recipe(String name, List<String> ingredients, String instructions, String nutritionalInfo) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.nutritionalInfo = nutritionalInfo;
    }

    public String getName() {
        return this.name;
    }
}