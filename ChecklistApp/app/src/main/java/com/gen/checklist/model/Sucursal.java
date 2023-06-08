package com.gen.checklist.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class Sucursal implements Comparable<Sucursal>{
    public static final String ID = "id_sucursal";
    public static final String NOMBRE = "nombre";


    @SerializedName(ID)
    @DatabaseField(columnName = ID, id = true, index = true)
    public int id;

    @SerializedName(NOMBRE)
    @DatabaseField(columnName = NOMBRE)
    public String nombre;

    @Override
    public int compareTo(Sucursal item) {
        return this.nombre.toLowerCase().compareTo(item.nombre.toLowerCase());
    }
}
