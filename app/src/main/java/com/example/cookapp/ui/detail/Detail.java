package com.example.cookapp.ui.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;

import com.example.cookapp.App;
import com.example.cookapp.AppDatabase;
import com.example.cookapp.Favorite;
import com.example.cookapp.FavoriteDao;
import com.example.cookapp.MainActivity;
import com.example.cookapp.R;
import com.example.cookapp.model.Meals;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Detail extends AppCompatActivity implements DetailView{
    AppDatabase db;
    FavoriteDao favoriteDao;
    List<Favorite> favorites;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView mealThumb;
    TextView instructions;
    TextView ingredients;
    TextView measures;
    CardView youtube;
    Menu optionsMenu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent=getIntent();
        String mealName=intent.getStringExtra("detail");
        DetailPresenter presenter=new DetailPresenter(this);
        presenter.getMealByName(mealName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        Intent intent=getIntent();
        String mealName=intent.getStringExtra("detail");
        db=App.getInstance().getDatabase();
        favoriteDao=db.favoriteDao();
        Favorite favorite=favoriteDao.getByName(mealName);
        if(favorite!=null){
            menu.findItem(R.id.favorite).setIcon(getResources().getDrawable(R.drawable.ic_favorite_yes));
        }

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.favorite:
                db=App.getInstance().getDatabase();
                favoriteDao=db.favoriteDao();
                Intent intent=getIntent();
                String mealName=intent.getStringExtra("detail");
                try{
                    Favorite favorite=favoriteDao.getByName(mealName);
                    if(favorite==null){
                        Favorite fav=new Favorite();
                        fav.name=mealName;
                        favoriteDao.insert(fav);
                        item.setIcon(getResources().getDrawable(R.drawable.ic_favorite_yes));
                        Toast.makeText(Detail.this, "The dish has been added to your favorites.", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        favoriteDao.delete(favorite);
                        item.setIcon(getResources().getDrawable(R.drawable.ic_favorite_no));
                        Toast.makeText(Detail.this, "The dish has been delete in your favorites.", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(Detail.this, e.toString(), Toast.LENGTH_SHORT).show();

                }
                return true;
            case android.R.id.home :
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onErrorLoading() {
        Toast.makeText(this,"Unfortunately we did not find such a dish.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Detail.this.getApplicationContext(), MainActivity.class);
        Detail.this.startActivity(intent);
    }


    @SuppressLint("WrongViewCast")
    @Override
    public void setMeal(final Meals.Meal meal) {
        mealThumb=findViewById(R.id.mealImage);
        collapsingToolbarLayout=findViewById(R.id.collapsingToolbar);
        instructions=findViewById(R.id.directions);
        measures=findViewById(R.id.measure);
        ingredients=findViewById(R.id.ingredient);
        Picasso.get().load(meal.getStrMealThumb()).into(mealThumb);
        collapsingToolbarLayout.setTitle(meal.getStrMeal());
        instructions.setText(meal.getStrInstructions());
        youtube=findViewById(R.id.youtubeCard);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(meal.getStrYoutube()));
                if(intent!=null){
                    Detail.this.startActivity(intent);
                }
                else{
                    Toast.makeText(Detail.this, "Unfortunately, there is no cooking video.", Toast.LENGTH_LONG).show();
                }

            }
        });

        if (!meal.getStrMeasure1().isEmpty() && !Character.isWhitespace(meal.getStrMeasure1().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure1());
        }
        if (!meal.getStrMeasure2().isEmpty() && !Character.isWhitespace(meal.getStrMeasure2().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure2());
        }
        if (!meal.getStrMeasure3().isEmpty() && !Character.isWhitespace(meal.getStrMeasure3().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure3());
        }
        if (!meal.getStrMeasure4().isEmpty() && !Character.isWhitespace(meal.getStrMeasure4().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure4());
        }
        if (!meal.getStrMeasure5().isEmpty() && !Character.isWhitespace(meal.getStrMeasure5().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure5());
        }
        if (!meal.getStrMeasure6().isEmpty() && !Character.isWhitespace(meal.getStrMeasure6().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure6());
        }
        if (!meal.getStrMeasure7().isEmpty() && !Character.isWhitespace(meal.getStrMeasure7().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure7());
        }
        if (!meal.getStrMeasure8().isEmpty() && !Character.isWhitespace(meal.getStrMeasure8().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure8());
        }
        if (!meal.getStrMeasure9().isEmpty() && !Character.isWhitespace(meal.getStrMeasure9().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure9());
        }
        if (!meal.getStrMeasure10().isEmpty() && !Character.isWhitespace(meal.getStrMeasure10().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure10());
        }
        if (!meal.getStrMeasure11().isEmpty() && !Character.isWhitespace(meal.getStrMeasure11().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure11());
        }
        if (!meal.getStrMeasure12().isEmpty() && !Character.isWhitespace(meal.getStrMeasure12().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure12());
        }
        if (!meal.getStrMeasure13().isEmpty() && !Character.isWhitespace(meal.getStrMeasure13().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure13());
        }
        if (!meal.getStrMeasure14().isEmpty() && !Character.isWhitespace(meal.getStrMeasure14().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure14());
        }
        if (!meal.getStrMeasure15().isEmpty() && !Character.isWhitespace(meal.getStrMeasure15().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure15());
        }
        if (!meal.getStrMeasure16().isEmpty() && !Character.isWhitespace(meal.getStrMeasure16().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure16());
        }
        if (!meal.getStrMeasure17().isEmpty() && !Character.isWhitespace(meal.getStrMeasure17().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure17());
        }
        if (!meal.getStrMeasure18().isEmpty() && !Character.isWhitespace(meal.getStrMeasure18().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure18());
        }
        if (!meal.getStrMeasure19().isEmpty() && !Character.isWhitespace(meal.getStrMeasure19().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure19());
        }
        if (!meal.getStrMeasure20().isEmpty() && !Character.isWhitespace(meal.getStrMeasure20().charAt(0))) {
            measures.append("\n" + meal.getStrMeasure20());
        }


        if (!meal.getStrIngredient1().isEmpty()) {
            ingredients.append("\n 1. " + meal.getStrIngredient1());
        }
        if (!meal.getStrIngredient2().isEmpty()) {
            ingredients.append("\n 2. " + meal.getStrIngredient2());
        }
        if (!meal.getStrIngredient3().isEmpty()) {
            ingredients.append("\n 3. " + meal.getStrIngredient3());
        }
        if (!meal.getStrIngredient4().isEmpty()) {
            ingredients.append("\n 4. " + meal.getStrIngredient4());
        }
        if (!meal.getStrIngredient5().isEmpty()) {
            ingredients.append("\n 5. " + meal.getStrIngredient5());
        }
        if (!meal.getStrIngredient6().isEmpty()) {
            ingredients.append("\n 6. " + meal.getStrIngredient6());
        }
        if (!meal.getStrIngredient7().isEmpty()) {
            ingredients.append("\n 7. " + meal.getStrIngredient7());
        }
        if (!meal.getStrIngredient8().isEmpty()) {
            ingredients.append("\n 8. " + meal.getStrIngredient8());
        }
        if (!meal.getStrIngredient9().isEmpty()) {
            ingredients.append("\n 9. " + meal.getStrIngredient9());
        }
        if (!meal.getStrIngredient10().isEmpty()) {
            ingredients.append("\n 10. " + meal.getStrIngredient10());
        }
        if (!meal.getStrIngredient11().isEmpty()) {
            ingredients.append("\n 11. " + meal.getStrIngredient11());
        }
        if (!meal.getStrIngredient12().isEmpty()) {
            ingredients.append("\n 12. " + meal.getStrIngredient12());
        }
        if (!meal.getStrIngredient13().isEmpty()) {
            ingredients.append("\n 13. " + meal.getStrIngredient13());
        }
        if (!meal.getStrIngredient14().isEmpty()) {
            ingredients.append("\n 14. " + meal.getStrIngredient14());
        }
        if (!meal.getStrIngredient15().isEmpty()) {
            ingredients.append("\n 15. " + meal.getStrIngredient15());
        }
        if (!meal.getStrIngredient16().isEmpty()) {
            ingredients.append("\n 16. " + meal.getStrIngredient16());
        }
        if (!meal.getStrIngredient17().isEmpty()) {
            ingredients.append("\n 17. " + meal.getStrIngredient17());
        }
        if (!meal.getStrIngredient18().isEmpty()) {
            ingredients.append("\n 18. " + meal.getStrIngredient18());
        }
        if (!meal.getStrIngredient19().isEmpty()) {
            ingredients.append("\n 19. " + meal.getStrIngredient19());
        }
        if (!meal.getStrIngredient20().isEmpty()) {
            ingredients.append("\n 20. " + meal.getStrIngredient20());
        }
    }

}
