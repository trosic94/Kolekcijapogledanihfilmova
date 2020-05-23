package com.example.kolekcijapogledanihfilmova.database.Models;

import com.j256.ormlite.field.DatabaseField;

public class PogledaniFilm {
    public static final String TABLE_NAME = "pogledanifilm";

    public static final String TABLE_ID = "id";
    public static final String TABLE_COLUMN_FILM     = "filmid";
    public static final String TABLE_COLUMN_NASLOV    = "naslov";
    public static final String TABLE_COLUMN_GODINA    = "godina";
    public static final String TABLE_COLUMN_TRAJANJE     = "trajanje";
    public static final String TABLE_COLUMN_ZANR     = "zanr";
    public static final String TABLE_COLUMN_PLOT     = "plot";
    public static final String TABLE_COLUMN_POSTER     = "poster";
    public static final String TABLE_COLUMN_JEZIK     = "jezik";
    public static final String TABLE_COLUMN_NAGRADE     = "nagrade";

    public static final String TABLE_COLUMN_OCENA     = "ocena";
    public static final String TABLE_COLUMN_DATUM_GLEDANJA    = "datumgledanja";

    @DatabaseField(columnName = TABLE_ID, generatedId = true)
    private int id;
    @DatabaseField(columnName = TABLE_COLUMN_FILM)
    private String filmid;
    @DatabaseField(columnName = TABLE_COLUMN_NASLOV)
    private String naslov;
    @DatabaseField(columnName = TABLE_COLUMN_GODINA)
    private String godina;
    @DatabaseField(columnName = TABLE_COLUMN_TRAJANJE)
    private String trajanje;
    @DatabaseField(columnName = TABLE_COLUMN_ZANR)
    private String zanr;
    @DatabaseField(columnName = TABLE_COLUMN_PLOT)
    private String plot;
    @DatabaseField(columnName = TABLE_COLUMN_POSTER)
    private String poster;
    @DatabaseField(columnName = TABLE_COLUMN_JEZIK)
    private String jezik;
    @DatabaseField(columnName = TABLE_COLUMN_NAGRADE)
    private String nagrade;

    @DatabaseField(columnName = TABLE_COLUMN_OCENA)
    private String ocena;
    @DatabaseField(columnName = TABLE_COLUMN_DATUM_GLEDANJA)
    private String datumgledanja;

    public PogledaniFilm(int id, String filmid, String naslov, String godina, String trajanje, String zanr, String plot, String poster, String jezik, String nagrade, String ocena, String datumgledanja) {
        this.id = id;
        this.filmid = filmid;
        this.naslov = naslov;
        this.godina = godina;
        this.trajanje = trajanje;
        this.zanr = zanr;
        this.plot = plot;
        this.poster = poster;
        this.jezik = jezik;
        this.nagrade = nagrade;
        this.ocena = ocena;
        this.datumgledanja = datumgledanja;
    }
    public PogledaniFilm(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilmid() {
        return filmid;
    }

    public void setFilmid(String filmid) {
        this.filmid = filmid;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getGodina() {
        return godina;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

    public String getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(String trajanje) {
        this.trajanje = trajanje;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getJezik() {
        return jezik;
    }

    public void setJezik(String jezik) {
        this.jezik = jezik;
    }

    public String getNagrade() {
        return nagrade;
    }

    public void setNagrade(String nagrade) {
        this.nagrade = nagrade;
    }

    public String getOcena() {
        return ocena;
    }

    public void setOcena(String ocena) {
        this.ocena = ocena;
    }

    public String getDatumgledanja() {
        return datumgledanja;
    }

    public void setDatumgledanja(String datumgledanja) {
        this.datumgledanja = datumgledanja;
    }
}
