package com.example.kolekcijapogledanihfilmova.ui.detaljifilma;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.kolekcijapogledanihfilmova.R;
import com.example.kolekcijapogledanihfilmova.database.DbHelper;
import com.example.kolekcijapogledanihfilmova.database.DbWork;
import com.example.kolekcijapogledanihfilmova.database.Models.PogledaniFilm;
import com.example.kolekcijapogledanihfilmova.net.Service;
import com.example.kolekcijapogledanihfilmova.net.models.DetailSearch;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieProfileFragment extends Fragment {
    TextView title,year,runtime,genre,plot,language;
    ImageView poster;
    private DbHelper databaseHelper;
    String idFilma;
    TimePicker timePicker;
    TextView cena;
    Context cnt;
    String urlPoster;

    public MovieProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_movie_profile, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profil, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cnt = getContext();
        idFilma = getArguments().getString("IMDBID");
        getMoviesById(idFilma);
        title = view.findViewById(R.id.txtProfilTitle);
        year = view.findViewById(R.id.txtProfilYear);
        runtime = view.findViewById(R.id.txtProfilTrajanje);
        genre = view.findViewById(R.id.txtProfilZanr);
        plot = view.findViewById(R.id.txtProfilPlot);
        language = view.findViewById(R.id.txtProfilLanguage);
        poster = view.findViewById(R.id.imgProfilPoster);
        try {
            dodajUPogledano();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public DbHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(cnt, DbHelper.class);
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
                    urlPoster = detailSearch.getPoster();
                    Picasso.with(cnt).load(detailSearch.getPoster()).into(poster);
                }
            }

            @Override
            public void onFailure(Call<DetailSearch> call, Throwable t) {
                //u slucaju da je nesto poslo po zlu, ispisemo sta nije u redu tj sta je poruka greske
                Log.d("MOJAPRETRAGA",""+t.getMessage());
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public  void dodajUPogledano() throws SQLException {

        DbWork dbWork = new DbWork();
        dbWork.dodajUPregledano(getDatabaseHelper(),urlPoster,title.getText().toString(),year.getText().toString(),idFilma);
        List<PogledaniFilm> repertoars = getDatabaseHelper().getPogledaniFilm().queryForAll();
        for (PogledaniFilm r:repertoars
        ) {
            Log.d("REPLIST",""+r.getFilmid());
        }
    }
}
