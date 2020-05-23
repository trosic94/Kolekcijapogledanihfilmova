package com.example.kolekcijapogledanihfilmova.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.kolekcijapogledanihfilmova.R;
import com.example.kolekcijapogledanihfilmova.database.DbHelper;
import com.example.kolekcijapogledanihfilmova.net.Service;
import com.example.kolekcijapogledanihfilmova.net.models.DetailSearch;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieProfile extends AppCompatActivity {
    TextView title,year,runtime,genre,plot,language;
    ImageView poster;
    private DbHelper databaseHelper;
    String idFilma;
    TimePicker timePicker;
    TextView cena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        idFilma = intent.getStringExtra("IMDBID");
        getMoviesById(idFilma);
        title = findViewById(R.id.txtProfilTitle);
        year = findViewById(R.id.txtProfilYear);
        runtime = findViewById(R.id.txtProfilTrajanje);
        genre = findViewById(R.id.txtProfilZanr);
        plot = findViewById(R.id.txtProfilPlot);
        language = findViewById(R.id.txtProfilLanguage);
        poster = findViewById(R.id.imgProfilPoster);
       

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profil, menu);
        return true;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.obrisiIzPogledanih:
//                try {
//                    dodajURepertoar();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
                break;
            default:
                break;
        }

        return true;
    }
    public DbHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(MovieProfile.this, DbHelper.class);
        }
        return databaseHelper;
    }
    private void getMoviesById(String imdbid){
        Map<String, String> query = new HashMap<String, String>();
        query.put("apikey", "edc44f33");
        query.put("i", imdbid);

        Call<DetailSearch> call = Service.apiInterface().getMoviesById(query);
        call.enqueue(new Callback<DetailSearch>() {
            @Override
            public void onResponse(Call<DetailSearch> call, Response<DetailSearch> response) {

                if (response.code() == 200){
                    DetailSearch detailSearch = response.body();
                    title.setText(detailSearch.getTitle());
                    year.setText(detailSearch.getYear());
                    runtime.setText(detailSearch.getRuntime());
                    genre.setText(detailSearch.getGenre());
                    plot.setText(detailSearch.getPlot());
                    language.setText(detailSearch.getLanguage());
                    Picasso.with(MovieProfile.this).load(detailSearch.getPoster()).into(poster);
                }
            }

            @Override
            public void onFailure(Call<DetailSearch> call, Throwable t) {
                //u slucaju da je nesto poslo po zlu, ispisemo sta nije u redu tj sta je poruka greske
            }
        });
    }
}
