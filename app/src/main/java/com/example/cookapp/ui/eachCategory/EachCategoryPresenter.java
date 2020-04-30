package com.example.cookapp.ui.eachCategory;

import com.example.cookapp.Utils;
import com.example.cookapp.model.Categories;
import com.example.cookapp.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EachCategoryPresenter {
    EachCategoryView view;

    public EachCategoryPresenter(EachCategoryView view) {
        this.view = view;
    }
    void getMealByCategory(String category){
        Call<Meals> mealsCall= Utils.getApi().getMealByCategory(category);
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()){
                    view.setMeals(response.body().getMeals());
                }
                else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
