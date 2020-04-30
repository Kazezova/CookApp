package com.example.cookapp.ui.category;

import android.content.Intent;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookapp.R;
import com.example.cookapp.adapter.CategoryAdapter;
import com.example.cookapp.model.Categories;
import com.example.cookapp.ui.detail.Detail;
import com.example.cookapp.ui.eachCategory.EachCategoryActivity;
import com.example.cookapp.ui.eachCategory.EachCategoryPresenter;

import java.util.List;

public class CategoryFragment extends Fragment implements CategoryView{


    CategoryPresenter presenter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new CategoryPresenter(this);
        presenter.getCategories();
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        RecyclerView recyclerView=this.getView().findViewById(R.id.recyclerCategory);
        CategoryAdapter categoryAdapter=new CategoryAdapter(category, getActivity());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView categoryName = view.findViewById(R.id.categoryName);
                Intent intent = new Intent(getActivity().getApplicationContext(), EachCategoryActivity.class);
                intent.putExtra("category", categoryName.getText().toString());
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void onErrorLoading(String message) {

    }
}
