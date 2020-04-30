package com.example.cookapp.ui.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookapp.App;
import com.example.cookapp.AppDatabase;
import com.example.cookapp.Favorite;
import com.example.cookapp.FavoriteDao;
import com.example.cookapp.R;
import com.example.cookapp.Utils;
import com.example.cookapp.adapter.CategoryAdapter;
import com.example.cookapp.adapter.FavoriteAdapter;
import com.example.cookapp.adapter.HomeLastMealsAdapter;
import com.example.cookapp.model.Meals;
import com.example.cookapp.ui.detail.Detail;
import com.example.cookapp.ui.eachCategory.EachCategoryActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {
    List<Meals.Meal> mealList=new ArrayList<>();;
    List<Favorite> favoriteList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);
        if(favoriteList==null){
            LinearLayout linearLayout= root.findViewById(R.id.no_favorite);
            linearLayout.setVisibility(View.VISIBLE);
        }
        else{
            RecyclerView recyclerView=root.findViewById(R.id.recyclerFavorite);
            final FavoriteAdapter adapter=new FavoriteAdapter(mealList, getActivity());
            GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(), 4,GridLayoutManager.VERTICAL,false);

            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setNestedScrollingEnabled(true);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            adapter.setOnItemClickListener(new FavoriteAdapter.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    TextView mealName = view.findViewById(R.id.mealName);
                    Intent intent = new Intent(getActivity().getApplicationContext(), Detail.class);
                    intent.putExtra("detail", mealName.getText().toString());
                    getActivity().startActivity(intent);
                }
            });
            for (Favorite favorite : favoriteList) {
                Call<Meals> mealsCall = Utils.getApi().getMealByName(favorite.name);
                mealsCall.enqueue(new Callback<Meals>() {
                    @Override
                    public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                        if (!response.isSuccessful()) {
                            Log.e("Error:", "" + response.code());
                            return;
                        }
                        mealList.add(response.body().getMeals().get(0));
                        int insertIndex = mealList.size();
                        adapter.notifyItemInserted(insertIndex);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                        Log.e("Error:", "" + t.getLocalizedMessage());
                    }
                });
            }
        }
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FavoriteDao favoriteDao=App.getInstance().getDatabase().favoriteDao();
        favoriteList=favoriteDao.getFavorites();

    }

}
