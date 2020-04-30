package com.example.cookapp.ui.lastMeal;

import com.example.cookapp.model.Meals;

import java.util.List;

public interface LastMealView {
    void setLastMeal(List<Meals.Meal> meal);
    void onErrorLoading(String message);
}
