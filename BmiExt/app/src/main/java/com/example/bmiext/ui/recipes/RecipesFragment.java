package com.example.bmiext.ui.recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmiext.R;
import com.example.bmiext.databinding.FragmentBmrBinding;
import com.example.bmiext.ui.bmi.BmiViewModel;
import com.example.bmiext.ui.bmr.BmrViewModel;
import com.example.bmiext.ui.bmr.BmrViewModelFactory;

import java.util.ArrayList;

public class RecipesFragment extends Fragment {
    private FragmentBmrBinding binding;
    private BmrViewModel bmrViewModel;
    private BmiViewModel bmiViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bmiViewModel = new ViewModelProvider(requireActivity()).get(BmiViewModel.class);
        BmrViewModelFactory bmrFactory = new BmrViewModelFactory(bmiViewModel);
        bmrViewModel = new ViewModelProvider(this, bmrFactory).get(BmrViewModel.class);

        binding = FragmentBmrBinding.inflate(inflater, container, false);
        RecipesViewModelFactory factory = new RecipesViewModelFactory(bmiViewModel, bmrViewModel);

        RecipesViewModel recipeViewModel = new ViewModelProvider(this, factory).get(RecipesViewModel.class);

        View root = inflater.inflate(R.layout.fragment_recipes, container, false);
        RecyclerView recipesRecyclerView = root.findViewById(R.id.recipesRecyclerView);
        TextView emptyTextView = root.findViewById(R.id.emptyTextView);

        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecipeAdapter adapter = new RecipeAdapter(new ArrayList<>());  // Initially empty or populated based on your logic
        recipesRecyclerView.setAdapter(adapter);

        boolean recipesAvailable = adapter.getItemCount() > 0;
        if (recipesAvailable) {
            emptyTextView.setVisibility(View.GONE);
            recipesRecyclerView.setVisibility(View.VISIBLE);
        } else {
            emptyTextView.setVisibility(View.VISIBLE);
            recipesRecyclerView.setVisibility(View.GONE);
        }
        return root;
    }
}
