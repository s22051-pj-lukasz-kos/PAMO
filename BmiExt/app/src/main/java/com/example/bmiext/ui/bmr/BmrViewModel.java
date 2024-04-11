package com.example.bmiext.ui.bmr;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BmrViewModel extends ViewModel {

    private final MutableLiveData<Double> bmrResult = new MutableLiveData<>();

    public LiveData<Double> getBmrResult() {
        return bmrResult;
    }

    public void calculateBmr(double weight, int height, int age, boolean isMale) {
        double bmr;
        if (isMale) {
            bmr = 66.5 + (13.75 * weight) + (5.003 * height) - (6.755 * age);
        } else {
            bmr = 655.1 + (9.563 * weight) + (1.850 * height) - (4.676 * age);
        }
        bmrResult.setValue(bmr);
    }
}