package com.example.cookapp.ui.category;

import com.example.cookapp.model.Categories;

import java.util.List;

public interface CategoryView {
    void setCategory(List<Categories.Category> category);
    void onErrorLoading(String message);
}
