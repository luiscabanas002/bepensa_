package com.gen.checklist.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class SubSubProcesos {

    public static final String ID = "id_sub_sub_proceso";
    public static final String DESCRIPCION = "nombre";

    @SerializedName(ID)
    @DatabaseField(columnName = ID, id = true, index = true)
    public int id;

    @SerializedName(DESCRIPCION)
    @DatabaseField(columnName = DESCRIPCION)
    public String nombre;

}
