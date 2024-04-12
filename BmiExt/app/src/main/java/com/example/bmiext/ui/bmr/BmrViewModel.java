package com.example.bmiext.ui.bmr;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bmiext.R;
import com.example.bmiext.ui.bmi.BmiViewModel;

public class BmrViewModel extends ViewModel {
    private final MutableLiveData<Integer> mAge = new MutableLiveData<>(0);
    private final MutableLiveData<Boolean> mIsMan = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> mIsWoman = new MutableLiveData<>(false);
    private final MutableLiveData<Double> bmrResult = new MutableLiveData<>();
    private final BmiViewModel bmiViewModel;

    public BmrViewModel(BmiViewModel bmiViewModel) {
        this.bmiViewModel = bmiViewModel;
    }

    public LiveData<Double> getBmrResult() {
        return bmrResult;
    }

    public void calculateBmr() {
        Double weight = bmiViewModel.getWeight().getValue();
        Integer height = bmiViewModel.getHeight().getValue();
        Integer age = mAge.getValue();
        Boolean isManCheck = mIsMan.getValue();
        Boolean isWomanCheck = mIsWoman.getValue();

        boolean isMan = Boolean.TRUE.equals(isManCheck) && Boolean.FALSE.equals(isWomanCheck);
        boolean isWoman = Boolean.FALSE.equals(isManCheck) && Boolean.TRUE.equals(isWomanCheck);
        double bmr;

        if (weight == null || height == null || age == null || age <= 0 || weight <= 0 || height <= 0) {
            bmrResult.setValue(0.0);
            return;
        }

        if (isMan) {
            bmr = 66.5 + (13.75 * weight) + (5.003 * height) - (6.755 * age);
            bmrResult.setValue(bmr);
        } else if (isWoman) {
            bmr = 655.1 + (9.563 * weight) + (1.850 * height) - (4.676 * age);
            bmrResult.setValue(bmr);
        } else {
            bmrResult.setValue((double) R.string.bmr_select_sex);
        }
    }

    public void setAge(int age) {
        mAge.setValue(age);
        Log.d("BmrViewModel", "Age set: " + age);
    }

    public void setIsMan(boolean isMan) {
        mIsMan.setValue(isMan);
        Log.d("BmrViewModel", "isMan set: " + isMan);
    }

    public void setIsWoman(boolean isWoman) {
        mIsWoman.setValue(isWoman);
        Log.d("BmrViewModel", "isWoman set: " + isWoman);
    }


}