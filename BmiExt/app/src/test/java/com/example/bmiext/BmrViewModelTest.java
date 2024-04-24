package com.example.bmiext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.bmiext.ui.bmi.BmiViewModel;
import com.example.bmiext.ui.bmr.BmrViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BmrViewModelTest {
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    @Mock
    private BmiViewModel bmiViewModel;
    private BmrViewModel bmrViewModel;
    @Mock
    private Observer<Double> observer;

    // used for floating point number precision in assertEquals
    private double delta;

    @Captor
    private ArgumentCaptor<Double> bmrCaptor;

    @Before
    public void setup() {
        delta = 0.001;
        double enterWeight = 70;
        int enterHeight = 175;
        when(bmiViewModel.getWeight()).thenReturn(new MutableLiveData<>(enterWeight));
        when(bmiViewModel.getHeight()).thenReturn(new MutableLiveData<>(enterHeight));

        bmrViewModel = new BmrViewModel(bmiViewModel);
        bmrViewModel.getBmrResult().observeForever(observer);
    }

    @Test
    public void calculateBmr_whenValidWoman_calculatesCorrectBmr() {
        bmrViewModel.setAge(25);
        bmrViewModel.setIsMan(false);
        bmrViewModel.setIsWoman(true);

        bmrViewModel.calculateBmr();

        verify(observer, times(2)).onChanged(bmrCaptor.capture());
        Double lastValue = bmrCaptor.getAllValues().get(bmrCaptor.getAllValues().size() - 1);
        assertEquals("getBmrResult for woman is not as expected", 1531.36, lastValue, delta);
    }

    @Test
    public void calculateBmr_whenValidMan_calculatesCorrectBmr() {
        bmrViewModel.setAge(25);
        bmrViewModel.setIsMan(true);
        bmrViewModel.setIsWoman(false);

        bmrViewModel.calculateBmr();

        verify(observer, times(2)).onChanged(bmrCaptor.capture());
        Double lastValue = bmrCaptor.getAllValues().get(bmrCaptor.getAllValues().size() - 1);
        assertEquals("getBmrResult for man is not as expected", 1735.65, lastValue, delta);
    }

    @Test
    public void calculateBmr_noBmi_setsBmrToZero() {
        when(bmiViewModel.getWeight()).thenReturn(new MutableLiveData<>(70.0));
        when(bmiViewModel.getHeight()).thenReturn(new MutableLiveData<>(0));
        bmrViewModel.setAge(25);
        bmrViewModel.setIsMan(true);
        bmrViewModel.setIsWoman(false);

        bmrViewModel.calculateBmr();

        verify(observer, times(2)).onChanged(bmrCaptor.capture());
        Double lastValue = bmrCaptor.getAllValues().get(bmrCaptor.getAllValues().size() - 1);
        assertEquals("getBmrResult is not set to 0.0 when no BMI", 0.0, lastValue, delta);
    }

    @Test
    public void calculateBmr_whenInvalidInputs_setsBmrToZero() {
        when(bmiViewModel.getWeight()).thenReturn(new MutableLiveData<>(0.0));
        when(bmiViewModel.getHeight()).thenReturn(new MutableLiveData<>(0));

        bmrViewModel.calculateBmr();

        verify(observer, times(2)).onChanged(bmrCaptor.capture());
        Double lastValue = bmrCaptor.getAllValues().get(bmrCaptor.getAllValues().size() - 1);
        assertEquals("getBmrResult is not set to 0.0 when no BMR input", 0.0, lastValue, delta);
    }
}
