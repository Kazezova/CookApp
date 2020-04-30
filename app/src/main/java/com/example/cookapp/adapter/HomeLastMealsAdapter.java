package com.example.cookapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookapp.R;
import com.example.cookapp.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeLastMealsAdapter extends RecyclerView.Adapter<HomeLastMealsAdapter.RecyclerViewHolder> {
    private List<Meals.Meal> meals;
    private Context context;
    public static ClickListener clickListener;

    public HomeLastMealsAdapter(List<Meals.Meal> meals, Context context) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeLastMealsAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_last_meal_recycler, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String strMealImage=meals.get(position).getStrMealThumb();
        String strMealName=meals.get(position).getStrMeal();
        Picasso.get().load(strMealImage).placeholder(R.drawable.placeholder).into(holder.mealImage);
        holder.mealTitle.setText(strMealName);

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView mealImage;
        TextView mealTitle;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage=itemView.findViewById(R.id.mealThumb);
            mealTitle=itemView.findViewById(R.id.mealName);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }
    public void setOnItemClickListener(HomeLastMealsAdapter.ClickListener clickListener) {
        HomeLastMealsAdapter.clickListener = clickListener;
    }
    public interface ClickListener{
        void onClick(View view, int position);
    }
}
