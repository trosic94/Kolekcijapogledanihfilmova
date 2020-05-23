package com.example.kolekcijapogledanihfilmova.database;

import com.example.kolekcijapogledanihfilmova.database.Models.PogledaniFilm;

public class DbWork {
    public void dodajUPregledano(DbHelper getDatabaseHelper,String poster, String naslov, String godina,String idFilma){
        PogledaniFilm pogledaniFilm = new PogledaniFilm();
        pogledaniFilm.setFilmid(idFilma);
        pogledaniFilm.setPoster(poster);
        pogledaniFilm.setNaslov(naslov);
        pogledaniFilm.setGodina(godina);
        try {
            getDatabaseHelper.getPogledaniFilm().create(pogledaniFilm);
        } catch (java.sql.SQLException e) {
            //Log.d("TEST4",""+e.getMessage());
            e.printStackTrace();
        }
    }
}
