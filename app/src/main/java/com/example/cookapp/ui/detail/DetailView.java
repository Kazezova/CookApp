package com.example.cookapp.ui.detail;

import com.example.cookapp.model.Meals;

public interface DetailView {
    void setMeal(Meals.Meal meal);
    void onErrorLoading();
}
