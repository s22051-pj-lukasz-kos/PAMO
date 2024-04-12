package com.example.bmiext.ui.bmr;

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
import com.example.bmiext.databinding.FragmentBmrBinding;
import com.example.bmiext.ui.bmi.BmiViewModel;

import java.text.DecimalFormat;

public class BmrFragment extends Fragment {
    private FragmentBmrBinding binding;
    private BmrViewModel bmrViewModel;
    private static final DecimalFormat bmrFormat = new DecimalFormat("#,##0.00");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BmiViewModel bmiViewModel = new ViewModelProvider(requireActivity()).get(BmiViewModel.class);
        bmrViewModel =
                new ViewModelProvider(this, new BmrViewModelFactory(bmiViewModel)).get(BmrViewModel.class);

        binding = FragmentBmrBinding.inflate(inflater, container, false);

        binding.bmrRadioGroup.setOnCheckedChangeListener((group, checkedId) ->
                updateBmr()
        );

        binding.ageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ageText = s.toString().trim();
                if (!ageText.isEmpty()) {
                    try {
                        updateBmr();
                    } catch (NumberFormatException e) {
                        binding.bmrTextView.setText(R.string.age_invalid_input);
                        Log.e("BmrFragment", "Wrong age format: " + ageText, e);
                    }
                } else {
                    bmrViewModel.setAge(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bmrViewModel.getBmrResult().observe(getViewLifecycleOwner(), result -> {
            String formattedBmr = bmrFormat.format(result);
            binding.bmrTextView.setText(getString(R.string.bmr_result, formattedBmr));
        });

        return binding.getRoot();
    }

    private void updateBmr() {
        String ageString = binding.ageEditText.getText().toString();

        try {
            int age = ageString.isEmpty() ? 0 : Integer.parseInt(ageString);
            boolean isMan = binding.manRadio.isChecked();
            boolean isWoman = binding.womanRadio.isChecked();
            bmrViewModel.setAge(age);
            bmrViewModel.setIsMan(isMan);
            bmrViewModel.setIsWoman(isWoman);
            bmrViewModel.calculateBmr();
        } catch (NumberFormatException e) {
            binding.bmrTextView.setText(getString(R.string.bmr_invalid_input));
            Log.e("BmrFragment", "Error parsing age", e);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}