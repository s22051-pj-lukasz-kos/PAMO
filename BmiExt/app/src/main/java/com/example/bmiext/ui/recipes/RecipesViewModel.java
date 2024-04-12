package com.example.bmiext.ui.recipes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bmiext.ui.bmi.BmiViewModel;
import com.example.bmiext.ui.bmr.BmrViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipesViewModel extends ViewModel {
    private final MutableLiveData<List<Recipe>> recipes = new MutableLiveData<>();
    private final BmiViewModel bmiViewModel;
    private final BmrViewModel bmrViewModel;

    public RecipesViewModel(BmiViewModel bmiViewModel, BmrViewModel bmrViewModel) {
        this.bmiViewModel = bmiViewModel;
        this.bmrViewModel = bmrViewModel;
        // Observe changes
        bmiViewModel.getBmiResult().observeForever(this::updateRecipesBasedOnBmi);
        bmrViewModel.getBmrResult().observeForever(this::updateRecipesBasedOnBmr);
    }

    private void updateRecipesBasedOnBmi(String bmi) {
        // Logic to choose recipes based on BMI
        // This is just a placeholder
        List<Recipe> selectedRecipes = new ArrayList<>();
        // Example logic
        if (Double.parseDouble(bmi) < 18.5) {
            selectedRecipes.add(new Recipe("Weight Gain Smoothie", Arrays.asList("Banana", "Peanut Butter", "Whole Milk"), "Blend ingredients", "Calories: 500"));
        } else {
            selectedRecipes.add(new Recipe("Healthy Salad", Arrays.asList("Lettuce", "Tomato", "Cucumber", "Olive Oil"), "Mix ingredients", "Calories: 200"));
        }
        recipes.setValue(selectedRecipes);
    }

    private void updateRecipesBasedOnBmr(Double bmr) {
        // Additional logic if needed
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }
}
