package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for bill amount input
import android.widget.TextView; // for displaying text

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final DecimalFormat weightFormat = new DecimalFormat("#,#0.0 'kg'");
    private static final DecimalFormat heightFormat = new DecimalFormat("#0 'cm'");
    private static final DecimalFormat bmiFormat = new DecimalFormat("#,##0.00");

    private double weight = 0.0;
    private int height = 0;


    private TextView bmiTextView;


    // called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

        EditText weightEditText = (EditText) findViewById(R.id.weightEditText);
        weightEditText.addTextChangedListener(weightEditTextWatcher);

        EditText heightEditText = (EditText) findViewById(R.id.heightEditText);
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        bmiTextView = (TextView) findViewById(R.id.bmiTextView);
    }

    // calculate and display tip and total amounts
    private void calculate() {
        double usedHeight = (height / 100.0);
        double usedHeightPow = Math.pow(usedHeight, 2);
        double bmi = (weight / usedHeightPow);
        bmiTextView.setText(bmiFormat.format(bmi));
    }

    // listener object for the EditText's text-changed events
    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            try {
                weight = Double.parseDouble(charSequence.toString());
            } catch (NumberFormatException e) {
                weight = 0;
            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };

    // listener object for the EditText's text-changed events
    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            try {
                height = Integer.parseInt(charSequence.toString());
            } catch (NumberFormatException e) {
                height = 0;

            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };
}


/*************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
