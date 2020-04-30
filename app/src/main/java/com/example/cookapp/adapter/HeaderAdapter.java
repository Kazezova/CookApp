package com.example.cookapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.cookapp.R;
import com.example.cookapp.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HeaderAdapter extends PagerAdapter {
    private List<Meals.Meal> meals;
    private Context context;
    private static ClickListener clickListener;
    public HeaderAdapter(List<Meals.Meal> meals, Context context) {
        this.meals = meals;
        this.context = context;
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        HeaderAdapter.clickListener = clickListener;
    }
    @Override
    public int getCount() {
        return meals.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_header, container, false);
        ImageView mealImage=view.findViewById(R.id.mealThumb);
        TextView mealTitle=view.findViewById(R.id.mealName);
        String strMealImage=meals.get(position).getStrMealThumb();
        String strMealName=meals.get(position).getStrMeal();
        Picasso.get().load(strMealImage).placeholder(R.drawable.placeholder).into(mealImage);
        mealTitle.setText(strMealName);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(v, position);
            }
        });
        container.addView(view, 0);
        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
    public interface ClickListener {
        void onClick(View v, int position);
    }
}
