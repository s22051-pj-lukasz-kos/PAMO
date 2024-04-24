package com.example.bmiext;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import androidx.lifecycle.ViewModel;

import com.example.bmiext.ui.bmi.BmiViewModel;
import com.example.bmiext.ui.bmr.BmrViewModel;
import com.example.bmiext.ui.bmr.BmrViewModelFactory;

public class BmrViewModelFactoryTest {

    @Test
    public void create_returnsBmrViewModel() {
        BmiViewModel bmiViewModel = mock(BmiViewModel.class);
        BmrViewModelFactory factory = new BmrViewModelFactory(bmiViewModel);

        ViewModel createdViewModel = factory.create(BmrViewModel.class);

        assertTrue(createdViewModel instanceof BmrViewModel);
    }
}
