package com.gen.checklist.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class Region {

    public static final String ID = "id_region";
    public static final String DESCRIPCION = "Region_nombre";

    @SerializedName(ID)
    @DatabaseField(columnName = ID, id = true, index = true)
    public int id;

    @SerializedName(DESCRIPCION)
    @DatabaseField(columnName = DESCRIPCION)
    public String nombre;

}
