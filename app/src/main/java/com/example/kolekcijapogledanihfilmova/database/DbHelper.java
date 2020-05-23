package com.example.kolekcijapogledanihfilmova.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.kolekcijapogledanihfilmova.database.Models.PogledaniFilm;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DbHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME    = "pogledani.db";
    private static final int    DATABASE_VERSION = 1;
    private Dao<PogledaniFilm, Integer> mPogledaniFilm = null;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, PogledaniFilm.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, PogledaniFilm.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Dao<PogledaniFilm, Integer> getPogledaniFilm() throws SQLException {
        if (mPogledaniFilm == null) {
            mPogledaniFilm = getDao(PogledaniFilm.class);
        }
        return mPogledaniFilm;
    }

    @Override
    public void close() {
        mPogledaniFilm = null;
        super.close();
    }
    public void deleteDatabase() throws SQLException {
        try {
            TableUtils.clearTable(connectionSource, PogledaniFilm.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
