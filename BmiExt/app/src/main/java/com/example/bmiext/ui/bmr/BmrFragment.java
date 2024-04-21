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
        binding = FragmentBmrBinding.inflate(inflater, container, false);

        BmiViewModel bmiViewModel = new ViewModelProvider(requireActivity()).get(BmiViewModel.class);
        bmrViewModel =
                new ViewModelProvider(requireActivity(), new BmrViewModelFactory(bmiViewModel)).get(BmrViewModel.class);

        setupUI();

        bmrViewModel.getBmrResult().observe(getViewLifecycleOwner(), result -> {
            if (result != null) {
                String formattedBmr = bmrFormat.format(result);
                binding.bmrTextView.setText(getString(R.string.bmr_result, formattedBmr));
            } else {
                binding.bmrTextView.setText(getString(R.string.bmr_invalid_input));
            }

        });

        return binding.getRoot();
    }

    private void setupUI() {
        binding.bmrRadioGroup.setOnCheckedChangeListener((group, checkedId) -> updateBmr());

        binding.ageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    updateBmr();
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void updateBmr() {
        try {
            int age = Integer.parseInt(binding.ageEditText.getText().toString().trim());
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