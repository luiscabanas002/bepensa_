package com.gen.checklist.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class OrigenesObservaciones {

    public static final String ID = "id_origen_observacion";
    public static final String NOMBRE = "nombre";
    public static final String ID_ESTATUS = "id_estatus";

    @SerializedName(ID)
    @DatabaseField(columnName = ID, id = true, index = true)
    public int id;

    @SerializedName(NOMBRE)
    @DatabaseField(columnName = NOMBRE)
    public String nombre;

    @SerializedName(ID_ESTATUS)
    @DatabaseField(columnName = ID_ESTATUS)
    public int id_estatus;
}
