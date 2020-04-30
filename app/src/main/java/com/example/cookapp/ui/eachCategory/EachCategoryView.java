package com.example.cookapp.ui.eachCategory;

import com.example.cookapp.model.Categories;
import com.example.cookapp.model.Meals;

import java.util.List;

public interface EachCategoryView {
    void setMeals(List<Meals.Meal> meals);
    void onErrorLoading(String message);
}
