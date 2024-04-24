package com.example.bmiext;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
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
public class BmiBehaviorTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void onAppStart_bmiScreenIsDisplayed() {
        onView(withId(R.id.bmiScreen)).check(matches(isDisplayed()));
    }

    @Test
    public void onAppStart_bmrScreenIsNotDisplayed() {
        onView(withId(R.id.bmrScreen)).check(doesNotExist());
    }

    @Test
    public void calculateBmi_displaysCorrectBmi() {
        onView(withId(R.id.weightEditText)).perform(typeText("70"));
        closeSoftKeyboard();
        onView(withId(R.id.heightEditText)).perform(typeText("175"));
        closeSoftKeyboard();

        onView(withId(R.id.bmiTextView)).check(matches(withText("22.86")));
    }

    @Test
    public void inputWeightWithoutHeight_invalidBmi() {
        onView(withId(R.id.weightEditText)).perform(typeText("70"));
        closeSoftKeyboard();

        onView(withId(R.id.bmiTextView)).check(matches(withText(R.string.bmi_invalid_input)));
    }


}
