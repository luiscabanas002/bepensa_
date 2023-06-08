package com.gen.checklist.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class MenuEjecutivo {

    public static final String ID = "ID_ejecutivo";
    public static final String DESCRIPCION = "Observaciones";

    @SerializedName(ID)
    @DatabaseField(columnName = ID, id = true, index = true)
    public int id;

    @SerializedName(DESCRIPCION)
    @DatabaseField(columnName = DESCRIPCION)
    public String nombre;

}
