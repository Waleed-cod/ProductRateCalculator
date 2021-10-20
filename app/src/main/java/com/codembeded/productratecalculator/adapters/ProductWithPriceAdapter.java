package com.codembeded.productratecalculator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codembeded.productratecalculator.R;
import com.codembeded.productratecalculator.models.ProductModel;

import java.util.ArrayList;

public class ProductWithPriceAdapter extends RecyclerView.Adapter<ProductWithPriceAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProductModel> product;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public ProductWithPriceAdapter(Context context, ArrayList<ProductModel> product) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.product = product;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_layout_for_calculator_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.textView.setText(product.get(position).getName());
        holder.bind(product.get(position));
    }
    @Override
    public int getItemCount() {
        return product.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_name_tv, product_weight_tv,product_expense_tv,product_total_tv;

        ViewHolder(View itemView) {
            super(itemView);
            product_name_tv = itemView.findViewById(R.id.calculator_product_name_tv);
            product_weight_tv = itemView.findViewById(R.id.calculator_product_weight_tv);
            product_expense_tv = itemView.findViewById(R.id.calculator_product_expense_tv);
            product_total_tv = itemView.findViewById(R.id.calculator_product_total_tv);
        }

        public void bind(final ProductModel product) {
            product_name_tv.setText(product.getProductName());
            product_weight_tv.setText(String.valueOf(product.getWeight()));
            product_expense_tv.setText(String.valueOf(product.getExpense()));
            product_total_tv.setText(String.valueOf(product.getPerGramPrice()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(context, ProductListingScreen.class);
//                    intent.putExtra("sub_category_id", product.get(getAdapterPosition()).getId());
//                    intent.putExtra("sub_category_name", product.get(getAdapterPosition()).getName());
//                    context.startActivity(intent);
                }
            });
        }

    }
}
