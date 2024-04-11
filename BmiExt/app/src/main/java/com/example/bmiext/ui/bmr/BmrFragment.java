package com.example.bmiext.ui.bmr;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.bmiext.R;
import com.example.bmiext.databinding.FragmentBmrBinding;
import com.example.bmiext.ui.bmi.BmiViewModel;

import java.text.DecimalFormat;

public class BmrFragment extends Fragment {

    private static final DecimalFormat bmrFormat = new DecimalFormat("#,##0.00");
    private FragmentBmrBinding binding;
    private BmrViewModel bmrViewModel;
    private BmiViewModel bmiViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bmrViewModel =
                new ViewModelProvider(this).get(BmrViewModel.class);

        bmiViewModel = new ViewModelProvider(requireActivity()).get(BmiViewModel.class);

        binding = FragmentBmrBinding.inflate(inflater, container, false);

        binding.bmrRadioGroup.setOnCheckedChangeListener((group, checkedId) ->
                calculateBmr()
        );

        bmrViewModel.getBmrResult().observe(getViewLifecycleOwner(), result -> {
            String formattedResult = bmrFormat.format(result);
            binding.bmrTextView.setText(getString(R.string.bmr_result, formattedResult));
        });

        return binding.getRoot();
    }

    private void calculateBmr() {
        double weight = 0.0;
        int height = 0;
        int age = 0;
        String ageInput = binding.ageEditText.getText().toString();
        if (!ageInput.isEmpty()) {
            try {
                age = Integer.parseInt(ageInput);
            } catch (NumberFormatException e) {
                binding.bmrTextView.setText(getString(R.string.bmr_invalid_input));
                Log.e("BmrFragment", "Error parsing age", e);
                return;
            }
        } else {
            binding.bmrTextView.setText(getString(R.string.bmr_invalid_input));
            Log.d("BmrFragment", "Age is empty");
            return;
        }


        Double weightObj = bmiViewModel.getWeight().getValue();
        if (weightObj != null) {
            weight = weightObj;
        }

        Integer heightObj = bmiViewModel.getHeight().getValue();
        if (heightObj != null) {
            height = heightObj;
        }

        Log.d("BmrFragment", "Using Weight and Height: " + weight + ", " + height);

        if (weight > 0 && height > 0 && age > 0) {
            boolean isMale = binding.manRadio.isChecked();
            Log.d("BmrFragment", "Gender is Male: " + isMale);
            bmrViewModel.calculateBmr(weight, height, age, isMale);
        } else {
            binding.bmrTextView.setText(getString(R.string.bmr_invalid_input));
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}