package com.gen.checklist.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class Divisiones {

    public static final String ID = "id_division";
    public static final String DESCRIPCION = "descripcion";
    public static final String APLICA_CODIGO = "aplica_codigo";
    public static final String APLICA_SUCURSAL = "aplica_sucursal";

    @SerializedName(ID)
    @DatabaseField(columnName = ID, id = true, index = true)
    public int id;

    @SerializedName(DESCRIPCION)
    @DatabaseField(columnName = DESCRIPCION)
    public String nombre;

    @SerializedName(APLICA_CODIGO)
    @DatabaseField(columnName = APLICA_CODIGO)
    public int aplica_codigo;

    @SerializedName(APLICA_SUCURSAL)
    @DatabaseField(columnName = APLICA_SUCURSAL)
    public int aplica_sucursal;

}
