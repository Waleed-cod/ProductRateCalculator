package com.codembeded.productratecalculator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.codembeded.productratecalculator.R;
import com.codembeded.productratecalculator.database.DatabaseHelper;
import com.codembeded.productratecalculator.models.BagModel;

import java.util.ArrayList;

public class UpdateProductActivity extends AppCompatActivity {
    Spinner bag_spinner;
    EditText product_name_et, product_weight_et, product_expense_et;
    Button add_product_btn;
    DatabaseHelper databaseHelper;
    boolean insert_result;
    String product_id, product_name, product_weight, product_expense;
    ArrayList<BagModel> bagList = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    String bag_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        init();
        bag_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bag_id = String.valueOf(bagList.get(i).getBagId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        add_product_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (product_name_et.getText().toString().isEmpty()) {
                    product_name_et.setError("Please enter bag name");
                    product_name_et.setFocusable(true);
                } else if (product_weight_et.getText().toString().isEmpty()) {
                    product_weight_et.setError("Please enter bag price");
                    product_weight_et.setFocusable(true);
                } else if (product_expense_et.getText().toString().isEmpty()) {
                    product_expense_et.setError("Please enter expense");
                    product_expense_et.setFocusable(true);
                } else {
                    insert_result = databaseHelper.UpdateProductData(product_name_et.getText().toString(), product_weight_et.getText().toString(), product_id, bag_id, product_expense_et.getText().toString());
                    if (insert_result) {
                        Toast.makeText(UpdateProductActivity.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UpdateProductActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(UpdateProductActivity.this, "Something went wrong...!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void init() {
        bag_spinner = findViewById(R.id.bag_spinner);
        product_name_et = findViewById(R.id.bag_name_et);
        product_weight_et = findViewById(R.id.bag_rate_et);
        add_product_btn = findViewById(R.id.add_bag_btn);
        product_expense_et = findViewById(R.id.expense_et);
        product_id = getIntent().getStringExtra("product_id");
        product_name = getIntent().getStringExtra("product_name");
        product_weight = getIntent().getStringExtra("product_weight");
        product_expense = getIntent().getStringExtra("product_expense");
        product_name_et.setText(product_name);
        product_weight_et.setText(product_weight);
        product_expense_et.setText(product_expense);
        databaseHelper = new DatabaseHelper(UpdateProductActivity.this);
        bagList = databaseHelper.getBagList();
        for (int i = 0; i < bagList.size(); i++) {
            names.add(bagList.get(i).getBagName());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(UpdateProductActivity.this, R.layout.support_simple_spinner_dropdown_item, names);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        bag_spinner.setAdapter(spinnerArrayAdapter);
    }
}