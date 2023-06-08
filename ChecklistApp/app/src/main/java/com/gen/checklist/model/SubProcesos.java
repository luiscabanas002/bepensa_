package com.gen.checklist.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.util.List;

public class SubProcesos {

    public static final String ID = "id_sub_proceso";
    public static final String DESCRIPCION = "nombre";
    public static final String SUCURSALES = "sucursales";

    @SerializedName(ID)
    @DatabaseField(columnName = ID, id = true, index = true)
    public int id;

    @SerializedName(DESCRIPCION)
    @DatabaseField(columnName = DESCRIPCION)
    public String nombre;

    @SerializedName(SUCURSALES)
    @DatabaseField(columnName = SUCURSALES)
    public List<Sucursal> sucursales;
}
