package com.example.kolekcijapogledanihfilmova.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kolekcijapogledanihfilmova.R;
import com.example.kolekcijapogledanihfilmova.database.DbHelper;
import com.example.kolekcijapogledanihfilmova.database.Models.PogledaniFilm;
import com.example.kolekcijapogledanihfilmova.net.models.DetailSearch;
import com.example.kolekcijapogledanihfilmova.ui.pretraga.AdapterSearchedMoviesMaster;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements AdapterPogledaniFilmovi.ItemClickListener {
    RecyclerView recyclerView;
    AdapterPogledaniFilmovi adapterSearchedMoviesMaster;
    Context cnt;
    private DbHelper databaseHelper;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        cnt = getContext();
        recyclerView = root.findViewById(R.id.rvPogledani);
        ArrayList<PogledaniFilm> repertoars = new ArrayList<PogledaniFilm>();
        try {
           List<PogledaniFilm> pogledaniFilms = getDatabaseHelper().getPogledaniFilm().queryForAll();
            for (PogledaniFilm f:pogledaniFilms
                 ) {
                Log.d("TESTQ",""+f.getGodina());
                repertoars.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapterSearchedMoviesMaster = new AdapterPogledaniFilmovi(cnt, repertoars,HomeFragment.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(cnt));
        recyclerView.setAdapter(adapterSearchedMoviesMaster);
        return root;
    }
    public DbHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(cnt, DbHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onItemClick(String id) {

    }
}
