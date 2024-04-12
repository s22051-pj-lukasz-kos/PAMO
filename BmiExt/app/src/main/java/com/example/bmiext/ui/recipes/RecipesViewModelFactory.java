package com.example.bmiext.ui.recipes;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bmiext.ui.bmi.BmiViewModel;
import com.example.bmiext.ui.bmr.BmrViewModel;

public class RecipesViewModelFactory implements ViewModelProvider.Factory {
    private final BmiViewModel bmiViewModel;
    private final BmrViewModel bmrViewModel;

    public RecipesViewModelFactory(BmiViewModel bmiViewModel, BmrViewModel bmrViewModel) {
        this.bmiViewModel = bmiViewModel;
        this.bmrViewModel = bmrViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RecipesViewModel.class)) {
            return (T) new RecipesViewModel(bmiViewModel, bmrViewModel);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
