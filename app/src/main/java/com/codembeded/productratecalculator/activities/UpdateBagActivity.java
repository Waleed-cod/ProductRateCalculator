package com.codembeded.productratecalculator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codembeded.productratecalculator.R;
import com.codembeded.productratecalculator.database.DatabaseHelper;

public class UpdateBagActivity extends AppCompatActivity {
    EditText bag_name_et, bag_rate_et;
    Button add_bag_btn;
    DatabaseHelper databaseHelper;
    boolean insert_result;
    String bag_id, bag_name, bag_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bag);
        init();
        add_bag_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bag_name_et.getText().toString().isEmpty()){
                    bag_name_et.setError("Please enter bag name");
                    bag_name_et.setFocusable(true);
                } else if ( bag_rate_et.getText().toString().isEmpty()){
                    bag_rate_et.setError("Please enter bag price");
                    bag_rate_et.setFocusable(true);
                } else {
                    insert_result = databaseHelper.UpdateBagData(bag_name_et.getText().toString(), bag_rate_et.getText().toString(), bag_id);
                    if (insert_result){
                        Toast.makeText(UpdateBagActivity.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UpdateBagActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(UpdateBagActivity.this, "Something went wrong...!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void init(){
        bag_name_et = findViewById(R.id.bag_name_et);
        bag_rate_et = findViewById(R.id.bag_rate_et);
        add_bag_btn = findViewById(R.id.add_bag_btn);
        bag_id = getIntent().getStringExtra("bag_id");
        bag_name = getIntent().getStringExtra("bag_name");
        bag_price = getIntent().getStringExtra("bag_price");
        bag_name_et.setText(bag_name);
        bag_rate_et.setText(bag_price);
        databaseHelper = new DatabaseHelper(UpdateBagActivity.this);
    }
}