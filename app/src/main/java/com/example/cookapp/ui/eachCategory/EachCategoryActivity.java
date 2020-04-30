package com.example.cookapp.ui.eachCategory;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookapp.R;
import com.example.cookapp.adapter.HomeLastMealsAdapter;
import com.example.cookapp.adapter.MealByCategoryAdapter;
import com.example.cookapp.model.Categories;
import com.example.cookapp.model.Meals;
import com.example.cookapp.ui.detail.Detail;
import com.example.cookapp.ui.detail.DetailPresenter;

import java.util.List;

public class EachCategoryActivity extends AppCompatActivity implements EachCategoryView{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.each_category_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent=getIntent();
        String categoryName=intent.getStringExtra("category");
        getSupportActionBar().setTitle(categoryName);
        EachCategoryPresenter presenter=new EachCategoryPresenter(this);
        presenter.getMealByCategory(categoryName);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void setMeals(List<Meals.Meal> meals) {
        MealByCategoryAdapter adapter=new MealByCategoryAdapter(meals,this);
        RecyclerView recyclerView=findViewById(R.id.eachCategoryRecycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new MealByCategoryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView mealName = view.findViewById(R.id.mealName);
                Intent intent = new Intent(EachCategoryActivity.this.getApplicationContext(), Detail.class);
                intent.putExtra("detail", mealName.getText().toString());
                EachCategoryActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public void onErrorLoading(String message) {

    }
}
