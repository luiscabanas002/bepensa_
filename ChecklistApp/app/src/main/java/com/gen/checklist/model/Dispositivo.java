package com.gen.checklist.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class Dispositivo {

    public static final String ID = "id_dispositivo";
    public static final String TOKEN = "token";
    public static final String SO = "so";

    @SerializedName(ID)
    @DatabaseField(columnName = ID, id = true, index = true)
    public int id;

    @SerializedName(TOKEN)
    @DatabaseField(columnName = TOKEN)
    public String token;

    @SerializedName(SO)
    @DatabaseField(columnName = SO)
    public String so;

}
