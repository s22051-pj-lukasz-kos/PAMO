package com.example.bmiext;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BmrBehaviorTest {


    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void goToBmrScreen_selectSomething_invalidOutput(){
        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        onView(withId(R.id.nav_bmr)).perform(click());
        onView(withId(R.id.womanRadio)).perform(click());
        onView(withId(R.id.bmrTextView)).check(matches(withText(R.string.bmr_invalid_input)));
    }

    @Test
    public void enterValues_goToBmrScreen_enterValues_ValidOutput() {
        //BMI
        onView(withId(R.id.weightEditText)).perform(typeText("70"));
        closeSoftKeyboard();
        onView(withId(R.id.heightEditText)).perform(typeText("175"));
        closeSoftKeyboard();
        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        onView(withId(R.id.nav_bmr)).perform(click());
        // BMR
        onView(withId(R.id.womanRadio)).perform(click());
        onView(withId(R.id.ageEditText)).perform(typeText("25"));
        closeSoftKeyboard();

        onView(withId(R.id.bmrTextView)).check(matches(withText("BMR result: 1,531.36")));
    }
}
