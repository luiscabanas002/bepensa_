package com.gen.checklist.data.database.ormlite.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gen.checklist.model.Area;
import com.gen.checklist.model.Factores;
import com.gen.checklist.model.OrigenesObservaciones;
import com.gen.checklist.model.RiesgosIdentificados;
import com.gen.checklist.model.Sucursal;
import com.gen.checklist.model.TiposFactores;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


public class OrmLiteDatabaseHelper extends OrmLiteSqliteOpenHelper {

    public OrmLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        for (Class<?> currentClass : mDbClasses) {
            try {
                TableUtils.createTableIfNotExists(connectionSource, currentClass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dropDataBase() {
        dropDatabase(getConnectionSource());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        updateDatabase(connectionSource);
    }

    private void updateDatabase(ConnectionSource connSource) {
        for (Class<?> currentClass : mDbClasses) {
            try {
                TableUtils.createTableIfNotExists(connSource, currentClass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void dropDatabase(ConnectionSource connectionSource) {
        for (Class<?> currentClass : mDbClasses) {
            try {
                TableUtils.dropTable(connectionSource, currentClass, true);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        onCreate(getWritableDatabase(), connectionSource);
    }

    private static Class<?>[] mDbClasses = {Area.class, Factores.class, OrigenesObservaciones.class, RiesgosIdentificados.class, TiposFactores.class, Sucursal.class};

    private static final String DATABASE_NAME = "check_v2.0.1.db";
    private static final int DATABASE_VERSION = 1;
}

