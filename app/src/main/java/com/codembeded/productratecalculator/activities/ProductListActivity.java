package com.codembeded.productratecalculator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.codembeded.productratecalculator.R;
import com.codembeded.productratecalculator.adapters.ProductAdapter;
import com.codembeded.productratecalculator.database.DatabaseHelper;
import com.codembeded.productratecalculator.models.ProductModel;

import org.json.JSONStringer;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {
    RecyclerView product_list_rv;
    DatabaseHelper databaseHelper;
    ProductAdapter productAdapter;
    ArrayList<ProductModel> productList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        init();
    }

    private void init(){
        product_list_rv = findViewById(R.id.product_list_rv);
        databaseHelper = new DatabaseHelper(ProductListActivity.this);
        productList = databaseHelper.getProductList();

        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(ProductListActivity.this, 1);
        product_list_rv.setLayoutManager(horizontalLayoutManager);
        productAdapter = new ProductAdapter(ProductListActivity.this, productList);
        product_list_rv.setAdapter(productAdapter);


    }
}