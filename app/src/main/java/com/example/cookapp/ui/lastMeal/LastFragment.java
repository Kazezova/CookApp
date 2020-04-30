package com.example.cookapp.ui.lastMeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookapp.R;
import com.example.cookapp.adapter.HomeLastMealsAdapter;
import com.example.cookapp.model.Meals;

import java.util.List;

public class LastFragment extends Fragment implements LastMealView{
    LastMealPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_last, container, false);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new LastMealPresenter(this);
        presenter.getLastMeals();
    }

    @Override
    public void setLastMeal(List<Meals.Meal> meal) {
        RecyclerView recycler=this.getView().findViewById(R.id.recyclerLatestMeals);
        HomeLastMealsAdapter homeLastMealsAdapter=new HomeLastMealsAdapter(meal, getActivity());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(), 5,GridLayoutManager.HORIZONTAL,false);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setNestedScrollingEnabled(true);
        recycler.setAdapter(homeLastMealsAdapter);
        homeLastMealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onErrorLoading(String message) {

    }
}
