package com.gen.checklist.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.util.List;

public class Procesos {

    public static final String ID = "id_proceso";
    public static final String DESCRIPCION = "nombre";
    public static final String SUB_PROCESOS = "sub_procesos";

    @SerializedName(ID)
    @DatabaseField(columnName = ID, id = true, index = true)
    public int id;

    @SerializedName(DESCRIPCION)
    @DatabaseField(columnName = DESCRIPCION)
    public String nombre;

    @SerializedName(SUB_PROCESOS)
    @DatabaseField(columnName = SUB_PROCESOS)
    public List<SubProcesos> subProcesos;

}
