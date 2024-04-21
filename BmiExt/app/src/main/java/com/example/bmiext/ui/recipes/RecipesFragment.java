package com.example.bmiext.ui.recipes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bmiext.R;
import com.example.bmiext.ui.bmi.BmiViewModel;
import com.example.bmiext.ui.bmr.BmrViewModel;
import com.example.bmiext.ui.bmr.BmrViewModelFactory;

import java.util.List;

public class RecipesFragment extends Fragment {
    private ExpandableListView expandableListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        expandableListView = view.findViewById(R.id.recipesExpandableListView);

        BmiViewModel bmiViewModel = new ViewModelProvider(requireActivity()).get(BmiViewModel.class);
        BmrViewModelFactory factory = new BmrViewModelFactory(bmiViewModel);
        BmrViewModel bmrViewModel = new ViewModelProvider(requireActivity(), factory).get(BmrViewModel.class);
        Log.d("RecipesFragment", "onCreateView works");
        Log.d("RecipesFragment", "getBmrResult: " + bmrViewModel.getBmrResult().getValue());
        bmrViewModel.getBmrResult().observe(getViewLifecycleOwner(), bmr -> {
            Log.d("RecipesFragment", "bmrViewModel.getBmrResult():" + bmr);
            if (bmr == null || bmr == 0.0) {
                showInitializationNeededMessage();
            } else {
                int recipeResource = bmr < 1800 ? R.xml.recipes_lower_bmr : R.xml.recipes_higher_bmr;
                List<Recipe> recipes = RecipeParser.parseRecipes(getContext(), recipeResource);
                Log.d("RecipesFragment", "recipes: " + recipes);
                RecipeExpandableListAdapter adapter = new RecipeExpandableListAdapter(getContext(), recipes);
                expandableListView.setAdapter(adapter);
            }
        });

        return view;
    }

    private void showInitializationNeededMessage() {
        Toast.makeText(getContext(), R.string.recipe_no_bmr, Toast.LENGTH_LONG).show();
    }
}
