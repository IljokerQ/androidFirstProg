package com.example.marce.primaapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter {

    private LayoutInflater mInflater;
    private ArrayList<Food> data = new ArrayList<>();



    private OnQuantityChange onQuantityChange;

    public void setData(ArrayList<Food> foodArrayList) {
        this.data = foodArrayList;
        notifyDataSetChanged();
    }


    public interface OnQuantityChange{
        public void onItemAdded(float price);
        public void onItemRemoved(float price);
    }

    public void setOnQuantityChange(OnQuantityChange onQuantityChange) {
        this.onQuantityChange = onQuantityChange;
    }


    public  FoodAdapter(Context context, ArrayList<Food> data){
        this.data=data;
        mInflater = LayoutInflater.from(context);
    }
    public FoodAdapter(Context context){
        mInflater= LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = mInflater.inflate(R.layout.row_item,viewGroup,false);
        return new FoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        FoodViewHolder foodViewHolder = (FoodViewHolder)viewHolder;
        foodViewHolder.productName.setText(data.get(i).getNameProductor());
        foodViewHolder.productPrice.setText(toString().valueOf(data.get(i).getPrice()));
        foodViewHolder.productQuantity.setText(toString().valueOf(data.get(i).getQuantity()));

    }

    @Override
    public int getItemCount() {
        return data.size();

    }

    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView productName, productPrice, productQuantity;
        public Button plus_btn, minus_btn;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.product);
            productPrice = itemView.findViewById(R.id.price);
            productQuantity = itemView.findViewById(R.id.integer_number);
            plus_btn = itemView.findViewById(R.id.increase);
            minus_btn = itemView.findViewById(R.id.decrease);

            plus_btn.setOnClickListener(this);
            minus_btn.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.increase){
                Food food = data.get(getAdapterPosition());
                food.incrementQuantity();
                notifyItemChanged(getAdapterPosition());

                onQuantityChange.onItemAdded(food.getPrice());
            }else if (v.getId() == R.id.decrease){
                Food food = data.get(getAdapterPosition());
                food.decrementQuantity();
                notifyItemChanged(getAdapterPosition());

                onQuantityChange.onItemRemoved(food.getPrice());
            }
        }
    }
}


