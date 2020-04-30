package com.example.cookapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.cookapp.MainActivity;
import com.example.cookapp.R;
import com.example.cookapp.Utils;
import com.example.cookapp.adapter.HeaderAdapter;
import com.example.cookapp.adapter.HomeLastMealsAdapter;
import com.example.cookapp.model.Meals;
import com.example.cookapp.ui.detail.Detail;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements HomeView {
    HomePresenter presenter;
    private HomeView view;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new HomePresenter(this);
        presenter.getRandomMeals();
        presenter.getLastMeals();
    }

    @Override
    public void setRandomMeal(@NonNull List<Meals.Meal> meal) {
        ViewPager viewPager=this.getView().findViewById(R.id.viewPagerHeader);
        HeaderAdapter headerAdapter=new HeaderAdapter(meal, getActivity());
        viewPager.setAdapter(headerAdapter);
        viewPager.setPadding(20,0,100,0);
        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener(new HeaderAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView mealName = view.findViewById(R.id.mealName);
                Intent intent = new Intent(getActivity().getApplicationContext(), Detail.class);
                intent.putExtra("detail", mealName.getText().toString());
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void setLastMeal(List<Meals.Meal> meal) {
        RecyclerView recycler=this.getView().findViewById(R.id.recyclerLatestMeals);
        HomeLastMealsAdapter homeLastMealsAdapter=new HomeLastMealsAdapter(meal, getActivity());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(gridLayoutManager);
        recycler.setNestedScrollingEnabled(true);
        recycler.setAdapter(homeLastMealsAdapter);
        homeLastMealsAdapter.notifyDataSetChanged();
        homeLastMealsAdapter.setOnItemClickListener(new HomeLastMealsAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView mealName = view.findViewById(R.id.mealName);
                Intent intent = new Intent(getActivity().getApplicationContext(), Detail.class);
                intent.putExtra("detail", mealName.getText().toString());
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void onErrorLoading(String message) {

    }

}
