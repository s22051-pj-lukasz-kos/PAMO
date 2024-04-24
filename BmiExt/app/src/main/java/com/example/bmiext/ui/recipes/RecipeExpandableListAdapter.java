package com.example.bmiext.ui.recipes;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bmiext.R;

import java.util.List;

public class RecipeExpandableListAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private final List<Recipe> recipes;

    public RecipeExpandableListAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public int getGroupCount() {
        return recipes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Recipe getGroup(int groupPosition) {
        return recipes.get(groupPosition);
    }

    @Override
    public Recipe getChild(int groupPosition, int childPosition) {
        return getGroup(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_recipe, parent, false);
        }
        TextView title = convertView.findViewById(R.id.recipeTitle);
        title.setText(getGroup(groupPosition).getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.recipe_detail, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.recipeImage);
        TextView ingredientsView = convertView.findViewById(R.id.ingredients);
        TextView instructionsView = convertView.findViewById(R.id.instructions);

        Recipe recipe = getChild(groupPosition, childPosition);
        int imageId = context.getResources().getIdentifier(recipe.getImagePath(), "drawable", context.getPackageName());
        imageView.setImageResource(imageId);
        ingredientsView.setText(getBulletedList(recipe.getIngredients()));
        instructionsView.setText(getBulletedList(recipe.getInstructions()));

        return convertView;
    }

    private CharSequence getBulletedList(List<String> items) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                String item = items.get(i);
                SpannableString spanItem = new SpannableString(item);
                spanItem.setSpan(new BulletSpan(16), 0, spanItem.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                builder.append(spanItem);
                if (i < items.size() - 1) {
                    builder.append("\n");
                }
            }
        }
        return builder;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}