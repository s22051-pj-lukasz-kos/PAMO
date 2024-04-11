package com.example.bmiext.ui.bmi;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.DecimalFormat;

public class BmiViewModel extends ViewModel {

    private static final DecimalFormat bmiFormat = new DecimalFormat("#,##0.00");
    private final MutableLiveData<Double> mWeight = new MutableLiveData<>(0.0);
    private final MutableLiveData<Integer> mHeight = new MutableLiveData<>(0);
    private final MutableLiveData<String> mBmiResult = new MutableLiveData<>();


    public LiveData<String> getBmiResult() {
        return mBmiResult;
    }

    public void calculateBmi(double weight, int height) {
        if (height <= 0 || weight <= 0) {
            mBmiResult.setValue("Invalid input");
        } else {
            double usedHeight = height / 100.0;
            double bmi = weight / Math.pow(usedHeight, 2);
            mBmiResult.setValue(bmiFormat.format(bmi));
        }
    }

    public LiveData<Double> getWeight() {
        return mWeight;
    }

    public LiveData<Integer> getHeight() {
        return mHeight;
    }

    public void setWeight(double weight) {
        mWeight.setValue(weight);
        Log.d("BmiViewModel", "Weight set: " + weight);
    }

    public void setHeight(int height) {
        mHeight.setValue(height);
        Log.d("BmiViewModel", "Height set: " + height);
    }
}