package com.gen.checklist.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class Area implements Comparable<Area>{

    public static final String TIPO_ACTO_INSEGURO = "ACTO INSEGURO";
    public static final String TIPO_CONDICIONES = "CONDICIONES";
    public static final String TIPO_AMBIENTAL = "AMBIENTAL";

    public static final String ID = "id_area";
    public static final String NOMBRE = "nombre";
    public static final String ID_ESTATUS = "id_estatus";
    public static final String PATH_ICONO = "path_icono";
    public static final String NUM_POSICION = "num_posicion";
    public static final String TIPO = "tipo";

    @SerializedName(ID)
    @DatabaseField(columnName = ID, id = true, index = true)
    public int id;

    @SerializedName(NOMBRE)
    @DatabaseField(columnName = NOMBRE)
    public String nombre;

    @SerializedName(PATH_ICONO)
    @DatabaseField(columnName = PATH_ICONO)
    public String path_icono;

    @SerializedName(NUM_POSICION)
    @DatabaseField(columnName = NUM_POSICION)
    public int num_posicion;

    @SerializedName(ID_ESTATUS)
    @DatabaseField(columnName = ID_ESTATUS)
    public int id_estatus;

    @SerializedName(TIPO)
    @DatabaseField(columnName = TIPO)
    public String tipo;

    public boolean seleccionado = false;

    @Override
    public int compareTo(Area factura) {
        if (num_posicion < factura.num_posicion) {
            return -1;
        }
        if (num_posicion > factura.num_posicion) {
            return 1;
        }
        return 0;
    }
}
