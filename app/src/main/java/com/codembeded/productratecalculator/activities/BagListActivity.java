package com.codembeded.productratecalculator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.codembeded.productratecalculator.R;
import com.codembeded.productratecalculator.adapters.BagAdapter;
import com.codembeded.productratecalculator.adapters.ProductAdapter;
import com.codembeded.productratecalculator.database.DatabaseHelper;
import com.codembeded.productratecalculator.models.BagModel;
import com.codembeded.productratecalculator.models.ProductModel;

import java.util.ArrayList;

public class BagListActivity extends AppCompatActivity {
    RecyclerView bag_list_rv;
    DatabaseHelper databaseHelper;
    BagAdapter bagAdapter;
    ArrayList<BagModel> bagList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag_list);
        init();
    }

    private void init() {
        bag_list_rv = findViewById(R.id.bag_list_rv);
        databaseHelper = new DatabaseHelper(BagListActivity.this);
        bagList = databaseHelper.getBagList();
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(BagListActivity.this, 1);
        bag_list_rv.setLayoutManager(horizontalLayoutManager);
        bagAdapter = new BagAdapter(BagListActivity.this, bagList);
        bag_list_rv.setAdapter(bagAdapter);

    }
}