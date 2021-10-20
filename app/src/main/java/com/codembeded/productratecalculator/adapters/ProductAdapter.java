package com.codembeded.productratecalculator.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codembeded.productratecalculator.R;
import com.codembeded.productratecalculator.activities.UpdateProductActivity;
import com.codembeded.productratecalculator.models.ProductModel;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProductModel> product_list;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public ProductAdapter(Context context, ArrayList<ProductModel> product) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.product_list = product;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_layout_for_product_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.textView.setText(product.get(position).getName());
        holder.bind(product_list.get(position));
    }
    @Override
    public int getItemCount() {
        return product_list.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_name_tv, product_weight_tv, product_expense_tv;

        ViewHolder(View itemView) {
            super(itemView);
            product_name_tv = itemView.findViewById(R.id.product_name_tv);
            product_weight_tv = itemView.findViewById(R.id.product_weight_tv);
            product_expense_tv = itemView.findViewById(R.id.product_expense_tv);
        }

        public void bind(final ProductModel product) {
            product_name_tv.setText(product.getProductName());
            product_weight_tv.setText(String.valueOf(product.getWeight()));
            product_expense_tv.setText(String.valueOf(product.getExpense()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UpdateProductActivity.class);
                    intent.putExtra("product_id", String.valueOf(product_list.get(getAdapterPosition()).getProductId()));
                    intent.putExtra("product_name", String.valueOf(product_list.get(getAdapterPosition()).getProductName()));
                    intent.putExtra("product_weight", String.valueOf(product_list.get(getAdapterPosition()).getWeight()));
                    intent.putExtra("product_expense",String.valueOf((product_list.get(getAdapterPosition()).getExpense())));
                    context.startActivity(intent);
                }
            });
        }

    }
}
