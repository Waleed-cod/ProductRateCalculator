package com.codembeded.productratecalculator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.codembeded.productratecalculator.R;
import com.codembeded.productratecalculator.adapters.ProductWithPriceAdapter;
import com.codembeded.productratecalculator.database.DatabaseHelper;
import com.codembeded.productratecalculator.models.BagModel;
import com.codembeded.productratecalculator.models.ProductModel;

import java.util.ArrayList;

public class CalculatorActivity extends AppCompatActivity {
    Spinner bag_spinner;
    RecyclerView product_list_with_rate_rv;
    DatabaseHelper databaseHelper;
    ArrayList<BagModel> bagList = new ArrayList<>();
    ArrayList<ProductModel> productList = new ArrayList<>();
    ArrayList<ProductModel> productListWithRate = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    EditText bag_rate_et;
    ProductWithPriceAdapter adapter;
    double bagPrice, productPricePerGram;
    Button calculate_btn;
    String bag_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        init();
        bag_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bagPrice = bagList.get(i).getBagPrice();
                bag_id = String.valueOf(bagList.get(i).getBagId());
                bag_rate_et.setText(String.valueOf(bagPrice));
                productList = databaseHelper.getProductListByBag(bag_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productListWithRate.clear();
                productPricePerGram = bagPrice / 25000;
                for (int i = 0; i < productList.size(); i++) {
                    productList.get(i).setPerGramPrice(productList.get(i).getExpense() + (productList.get(i).getWeight() * productPricePerGram));
                    productListWithRate.add(new ProductModel(productList.get(i).getProductName(), productList.get(i).getWeight(), productList.get(i).getExpense(), productList.get(i).getPerGramPrice()));
                }
                adapter = new ProductWithPriceAdapter(CalculatorActivity.this, productListWithRate);
                product_list_with_rate_rv.setAdapter(adapter);

            }
        });
    }

    private void init() {
        bag_rate_et = findViewById(R.id.bag_rate_et);
        bag_spinner = findViewById(R.id.bag_spinner);
        calculate_btn = findViewById(R.id.calculator_btn);
        product_list_with_rate_rv = findViewById(R.id.product_list_with_rate_rv);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(CalculatorActivity.this, 1);
        product_list_with_rate_rv.setLayoutManager(horizontalLayoutManager);

        databaseHelper = new DatabaseHelper(CalculatorActivity.this);
        bagList = databaseHelper.getBagList();
        //productList = databaseHelper.getProductListByBag(bag_id);
        for (int i = 0; i < bagList.size(); i++) {
            names.add(bagList.get(i).getBagName());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(CalculatorActivity.this, R.layout.support_simple_spinner_dropdown_item, names);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        bag_spinner.setAdapter(spinnerArrayAdapter);
    }
}