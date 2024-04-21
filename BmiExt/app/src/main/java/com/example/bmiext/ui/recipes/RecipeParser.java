package com.example.bmiext.ui.recipes;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class RecipeParser {

    public static List<Recipe> parseRecipes(Context context, int xmlResourceId) {
        List<Recipe> recipes = new ArrayList<>();
        Recipe currentRecipe = null;
        List<String> ingredients = null;
        List<String> instructions = null;
        boolean isParsingIngredients = false;
        boolean isParsingInstructions = false;
        String text = "";

        try {
            XmlResourceParser parser = context.getResources().getXml(xmlResourceId);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("recipe".equals(tagName)) {
                            currentRecipe = new Recipe();
                            ingredients = new ArrayList<>();
                            instructions = new ArrayList<>();
                        } else if ("ingredients".equals(tagName)) {
                            isParsingIngredients = true;
                        } else if ("instructions".equals(tagName)) {
                            isParsingInstructions = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if ("recipe".equals(tagName)) {
                            if (currentRecipe != null) {
                                currentRecipe.setIngredients(ingredients);
                                currentRecipe.setInstructions(instructions);
                                recipes.add(currentRecipe);
                            }
                        } else if ("name".equals(tagName)) {
                            if (currentRecipe != null) currentRecipe.setName(text);
                        } else if ("imagePath".equals(tagName)) {
                            if (currentRecipe != null) currentRecipe.setImagePath(text);
                        } else if ("ingredient".equals(tagName) && isParsingIngredients) {
                            ingredients.add(text);
                        } else if ("instruction".equals(tagName) && isParsingInstructions) {
                            instructions.add(text);
                        } else if ("ingredients".equals(tagName)) {
                            isParsingIngredients = false;
                        } else if ("instructions".equals(tagName)) {
                            isParsingInstructions = false;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            Log.e("RecipeParser", "XML Parser error", e);
        }

        return recipes;
    }
}
