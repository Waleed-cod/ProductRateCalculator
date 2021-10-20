package com.codembeded.productratecalculator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codembeded.productratecalculator.R;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    CardView bag_cv, product_cv, calculator_cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        bag_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, BagActivity.class));

            }
        });
        product_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ProductActivity.class));

            }
        });
        calculator_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CalculatorActivity.class));

            }
        });
    }

    private void init() {
        bag_cv = findViewById(R.id.bag_cv);
        product_cv = findViewById(R.id.product_cv);
        calculator_cv = findViewById(R.id.calculator_cv);
    }
}