package com.example.bmiext.ui.bmi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bmiext.R;
import com.example.bmiext.databinding.FragmentBmiBinding;

public class BmiFragment extends Fragment {
    private BmiViewModel bmiViewModel;
    private FragmentBmiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bmiViewModel =
                new ViewModelProvider(requireActivity()).get(BmiViewModel.class);

        binding = FragmentBmiBinding.inflate(inflater, container, false);


        binding.weightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String weightText = s.toString().trim();
                if (!weightText.isEmpty()) {
                    try {
                        updateBmi();
                    } catch (NumberFormatException e) {
                        binding.bmiTextView.setText(R.string.weight_invalid_input);
                        Log.e("BmiFragment", "Wrong weight format: " + weightText, e);
                    }
                } else {
                    bmiViewModel.setWeight(0.0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.heightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String heightText = s.toString().trim();
                if (!heightText.isEmpty()) {
                    try {
                        updateBmi();
                    } catch (NumberFormatException e) {
                        binding.bmiTextView.setText(R.string.height_invalid_input);
                        Log.e("BmiFragment", "Wrong height format: " + heightText, e);
                    }
                } else {
                    bmiViewModel.setHeight(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bmiViewModel.getBmiResult().observe(getViewLifecycleOwner(), result -> {
            if (result.equals(String.valueOf(R.string.bmi_invalid_input))) {
                binding.bmiTextView.setText(getString(R.string.bmi_invalid_input));
            } else {
                binding.bmiTextView.setText(result);
            }
        });

        return binding.getRoot();
    }

    private void updateBmi() {
        String weightString = binding.weightEditText.getText().toString();
        String heightString = binding.heightEditText.getText().toString();

        try {
            double weight = weightString.isEmpty() ? 0.0 : Double.parseDouble(weightString);
            int height = heightString.isEmpty() ? 0 : Integer.parseInt(heightString);

            bmiViewModel.setWeight(weight);
            bmiViewModel.setHeight(height);
            Log.d("BmiFragment", "Weight and Height set: " + weight + ", " + height);
            bmiViewModel.calculateBmi();
        } catch (NumberFormatException e) {
            Log.e("BmiFragment", "Error parsing weight or height", e);
            binding.bmiTextView.setText(getString(R.string.bmi_invalid_input));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}