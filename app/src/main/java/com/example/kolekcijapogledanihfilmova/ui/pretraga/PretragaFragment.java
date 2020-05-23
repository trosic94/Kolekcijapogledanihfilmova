package com.example.kolekcijapogledanihfilmova.ui.pretraga;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kolekcijapogledanihfilmova.MainActivity;
import com.example.kolekcijapogledanihfilmova.R;
import com.example.kolekcijapogledanihfilmova.activities.MovieProfile;
import com.example.kolekcijapogledanihfilmova.net.Service;
import com.example.kolekcijapogledanihfilmova.net.models.DetailSearch;
import com.example.kolekcijapogledanihfilmova.net.models.MasterSearch;
import com.example.kolekcijapogledanihfilmova.ui.detaljifilma.MovieProfileFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PretragaFragment extends Fragment implements AdapterSearchedMoviesMaster.ItemClickListener {
    RecyclerView rvLista;
    Button btnPretrazi;
    TextView txtPretraga;
    String tekstPretrage;
    Context cnt;
    AdapterSearchedMoviesMaster adapterSearchedMoviesMaster;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pretraga_filmovi, container, false);
        cnt = getContext();
        rvLista = root.findViewById(R.id.rvSearch);
        btnPretrazi = root.findViewById(R.id.btnSearch);
        txtPretraga = root.findViewById(R.id.txtSearch);
        btnPretrazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tekstPretrage = txtPretraga.getText().toString();
                if(tekstPretrage.length()>2){
                    getMoviesByName(tekstPretrage);
                }else{
                    Toast.makeText(cnt, "Morate uneti minimum 3 karaktera!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }
    private void getMoviesByName(String name){
        Map<String, String> query = new HashMap<String, String>();
        query.put("apikey", "edc44f33");
        query.put("s", name);

        Call<MasterSearch> call = Service.apiInterface().getMoviesByName(query);
        call.enqueue(new Callback<MasterSearch>() {
            @Override
            public void onResponse(Call<MasterSearch> call, Response<MasterSearch> response) {
                //obavezno proveriti da li je upit zavrsen uspesno 200 kod

                if (response.code() == 200){
                    MasterSearch masterPretraga = response.body();
                    DetailSearch pretraga = new DetailSearch();
                    ArrayList<DetailSearch> pretragas = new ArrayList<DetailSearch>();
                    for(DetailSearch e : masterPretraga.getSearch()){
                        pretragas.add(e);
                    }
                    adapterSearchedMoviesMaster = new AdapterSearchedMoviesMaster(cnt, pretragas,PretragaFragment.this);
                    rvLista.setLayoutManager(new LinearLayoutManager(cnt));
                    rvLista.setAdapter(adapterSearchedMoviesMaster);
                    for (DetailSearch e: pretragas
                    ) {
                        Log.d("MOJAPRETRAGA2",e.getTitle());
                    }

                }
            }

            @Override
            public void onFailure(Call<MasterSearch> call, Throwable t) {
                //u slucaju da je nesto poslo po zlu, ispisemo sta nije u redu tj sta je poruka greske
                Log.d("MOJAPRETRAGA",""+t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(String id) {
//        Intent profil = new Intent(cnt, MovieProfile.class);
//        profil.putExtra("IMDBID",id);
//        startActivity(profil);
        Bundle bundle = new Bundle();
        bundle.putString("IMDBID", id);
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        MovieProfileFragment fragobj = new MovieProfileFragment();
        fragobj.setArguments(bundle);
        ft.replace(R.id.nav_host_fragment, fragobj, "NewFragmentTag");
        ft.addToBackStack(null);
        ft.commit();
    }
}
