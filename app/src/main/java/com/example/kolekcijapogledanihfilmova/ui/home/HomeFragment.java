package com.example.kolekcijapogledanihfilmova.ui.home;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.kolekcijapogledanihfilmova.R;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.rvPogledani);
//        adapterSearchedMoviesMaster = new AdapterSearchedMoviesMaster(cnt, pretragas,PretragaFragment.this);
//        rvLista.setLayoutManager(new LinearLayoutManager(cnt));
//        rvLista.setAdapter(adapterSearchedMoviesMaster);
        return root;
    }
}
