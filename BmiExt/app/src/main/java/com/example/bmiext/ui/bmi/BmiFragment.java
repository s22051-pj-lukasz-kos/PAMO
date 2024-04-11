package com.example.bmiext.ui.bmi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bmiext.R;
import com.example.bmiext.databinding.FragmentBmiBinding;

public class BmiFragment extends Fragment {
    private FragmentBmiBinding binding;
    private BmiViewModel bmiViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bmiViewModel =
                new ViewModelProvider(requireActivity()).get(BmiViewModel.class);

        binding = FragmentBmiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText weightEditText = binding.weightEditText;
        final EditText heightEditText = binding.heightEditText;


        weightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String weightText = s.toString().trim();
                if (!weightText.isEmpty()) {
                    try {
                        double weight = Double.parseDouble(weightText);
                        bmiViewModel.setWeight(weight);
                    } catch (NumberFormatException e) {
                        Log.e("BmiFragment", "Wrong weight format: " + weightText, e);
                    }
                } else {
                    bmiViewModel.setWeight(0.0);
//                    updateBmi();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        heightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String heightText = s.toString().trim();
                if (!heightText.isEmpty()) {
                    try {
                        int height = Integer.parseInt(heightText);
                        bmiViewModel.setHeight(height);
                    } catch (NumberFormatException e) {
                        Log.e("BmiFragment", "Wrong height format: " + heightText, e);
                    }
                } else {
                    bmiViewModel.setHeight(0);
                }

//                updateBmi();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bmiViewModel.getBmiResult().observe(getViewLifecycleOwner(), result -> binding.bmiTextView.setText(result));

        return root;
    }

    private void updateBmi() {
        try {
            double weight = Double.parseDouble(binding.weightEditText.getText().toString());
            int height = Integer.parseInt(binding.heightEditText.getText().toString());
            bmiViewModel.setWeight(weight);
            bmiViewModel.setHeight(height);
            Log.d("BmiFragment", "Weight and Height set: " + weight + ", " + height);
            bmiViewModel.calculateBmi(weight, height);
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