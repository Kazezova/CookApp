package com.example.cookapp.ui.home;

import com.example.cookapp.model.Meals;

import java.util.List;

public interface HomeView {

    void setRandomMeal(List<Meals.Meal> meal);
    void setLastMeal(List<Meals.Meal> meal);
    void onErrorLoading(String message);
}
