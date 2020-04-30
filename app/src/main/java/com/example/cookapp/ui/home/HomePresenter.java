package com.example.cookapp.ui.home;
import com.example.cookapp.Utils;
import com.example.cookapp.model.Meals;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {
    private HomeView view;

    public HomePresenter(HomeView view) {
        this.view = view;
    }
    void getRandomMeals(){
        Call<Meals> mealsCall = Utils.getApi().getRandomMeal();
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {

                if(response.isSuccessful()){
                    view.setRandomMeal(response.body().getMeals());
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

    void getLastMeals(){
        Call<Meals> mealsCall=Utils.getApi().getLastMeal();
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if(response.isSuccessful()){
                    view.setLastMeal(response.body().getMeals());
                }
                else{
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
