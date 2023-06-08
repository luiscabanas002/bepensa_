package com.gen.checklist.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class Factores implements Comparable<Factores>{

    public static final String ID = "id_factor";
    public static final String NOMBRE = "nombre";
    public static final String ID_TIPO_FACTOR = "id_tipo_factor";
    public static final String ID_ESTATUS = "id_estatus";
    public static final String PATH_ICONO = "path_icono";
    public static final String NUM_POSICION = "num_posicion";

    @SerializedName(ID)
    @DatabaseField(columnName = ID, id = true, index = true)
    public int id;

    @SerializedName(NOMBRE)
    @DatabaseField(columnName = NOMBRE)
    public String nombre;

    @SerializedName(PATH_ICONO)
    @DatabaseField(columnName = PATH_ICONO)
    public String path_icono;

    @SerializedName(ID_TIPO_FACTOR)
    @DatabaseField(columnName = ID_TIPO_FACTOR)
    public int id_tipo_factor;

    @SerializedName(NUM_POSICION)
    @DatabaseField(columnName = NUM_POSICION)
    public int num_posicion;

    @SerializedName(ID_ESTATUS)
    @DatabaseField(columnName = ID_ESTATUS)
    public int id_estatus;

    public boolean seleccionado = false;

    @Override
    public int compareTo(Factores factura) {
        if (num_posicion < factura.num_posicion) {
            return -1;
        }
        if (num_posicion > factura.num_posicion) {
            return 1;
        }
        return 0;
    }
}
