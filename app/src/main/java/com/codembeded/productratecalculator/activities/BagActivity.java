package com.codembeded.productratecalculator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codembeded.productratecalculator.R;
import com.codembeded.productratecalculator.database.DatabaseHelper;

public class BagActivity extends AppCompatActivity {
    EditText bag_name_et, bag_rate_et;
    Button add_bag_btn;
    DatabaseHelper databaseHelper;
    boolean insert_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);
        init();
        add_bag_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bag_name_et.getText().toString().isEmpty()) {
                    bag_name_et.setError("Please enter bag name");
                    bag_name_et.setFocusable(true);
                } else if (bag_rate_et.getText().toString().isEmpty()) {
                    bag_rate_et.setError("Please enter bag price");
                    bag_rate_et.setFocusable(true);
                } else {
                    insert_result = databaseHelper.InsertBagData(bag_name_et.getText().toString(), bag_rate_et.getText().toString());
                    if (insert_result) {
                        Toast.makeText(BagActivity.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BagActivity.this, HomeActivity.class));
                    } else {
                        Toast.makeText(BagActivity.this, "Something went wrong...!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void init() {
        bag_name_et = findViewById(R.id.bag_name_et);
        bag_rate_et = findViewById(R.id.bag_rate_et);
        add_bag_btn = findViewById(R.id.add_bag_btn);
        databaseHelper = new DatabaseHelper(BagActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bag_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.list:
                startActivity(new Intent(BagActivity.this, BagListActivity.class));
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


}