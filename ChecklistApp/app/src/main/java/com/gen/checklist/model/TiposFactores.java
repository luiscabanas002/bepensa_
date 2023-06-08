package com.gen.checklist.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.util.List;

public class TiposFactores {

    public static final String ID = "id_tipo_factor";
    public static final String NOMBRE = "nombre";
    public static final String ID_ESTATUS = "id_estatus";
    public static final String MENSAJE_1 = "desMensaje1";
    public static final String MENSAJE_2 = "desMensaje2";

    @SerializedName(ID)
    @DatabaseField(columnName = ID, id = true, index = true)
    public int id;

    @SerializedName(NOMBRE)
    @DatabaseField(columnName = NOMBRE)
    public String nombre;

    @SerializedName(ID_ESTATUS)
    @DatabaseField(columnName = ID_ESTATUS)
    public int id_estatus;

    @SerializedName(MENSAJE_1)
    @DatabaseField(columnName = MENSAJE_1)
    public String mensaje1;

    @SerializedName(MENSAJE_2)
    @DatabaseField(columnName = MENSAJE_2)
    public String mensaje2;

    @SerializedName("factores")
    public List<Factores> factores;
}
