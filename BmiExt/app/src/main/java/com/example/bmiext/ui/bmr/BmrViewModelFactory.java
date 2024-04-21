package com.example.bmiext.ui.bmr;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bmiext.ui.bmi.BmiViewModel;

public class BmrViewModelFactory implements ViewModelProvider.Factory {
    private final BmiViewModel bmiViewModel;

    public BmrViewModelFactory(BmiViewModel bmiViewModel) {
        this.bmiViewModel = bmiViewModel;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BmrViewModel.class)) {
            return (T) new BmrViewModel(bmiViewModel);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
