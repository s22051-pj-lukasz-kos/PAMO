package com.example.bmiext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.bmiext.ui.bmi.BmiViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BmiViewModelTest {
    /**
     * A JUnit Test Rule that swaps the background executor
     * used by the Architecture Components with a different one
     * which executes each task synchronously.
     */
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    private BmiViewModel viewModel;

    @Mock
    private Observer<String> observer;

    // used for floating point number precision in assertEquals
    private double delta;

    @Before
    public void setup() {
        delta = 0.001;
        viewModel = new BmiViewModel();
    }

    @Test
    public void setWeight_getWeight_Equals() {
        double enterWeight = 60.2;

        viewModel.setWeight(enterWeight);
        double receivedWeight = viewModel.getWeight().getValue();

        assertEquals("setWeight and getWeight values not equal", enterWeight, receivedWeight, delta);
    }

    @Test
    public void setHeight_getHeight_Equals() {
        int enterHeight = 173;

        viewModel.setHeight(enterHeight);
        int receivedHeight = viewModel.getHeight().getValue();

        assertEquals("setHeight and getHeight values not equal", enterHeight, receivedHeight);
    }

    @Test
    public void calculateBmi_validInput_bmiCalculated() {
        viewModel.getBmiResult().observeForever(observer);
        viewModel.setWeight(70);
        viewModel.setHeight(175);

        viewModel.calculateBmi();

        verify(observer).onChanged("22.86");
    }

    @Test
    public void calculateBmi_noWeight_bmiInvalid() {
        viewModel.getBmiResult().observeForever(observer);
        viewModel.setHeight(175);

        viewModel.calculateBmi();

        verify(observer).onChanged(String.valueOf(R.string.bmi_invalid_input));  // Expected to show invalid input message
    }

    @Test
    public void calculateBmi_noHeight_bmiInvalid() {
        viewModel.getBmiResult().observeForever(observer);
        viewModel.setWeight(60);

        viewModel.calculateBmi();

        verify(observer).onChanged(String.valueOf(R.string.bmi_invalid_input));  // Expected to show invalid input message
    }

}
