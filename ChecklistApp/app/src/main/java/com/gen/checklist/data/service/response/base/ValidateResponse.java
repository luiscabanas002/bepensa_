package com.gen.checklist.data.service.response.base;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ValidateResponse {
    @SerializedName("url_servicios_web")
    public String url;

    @SerializedName("url_multimedia")
    public String url_multimedia;

    @SerializedName("activo")
    public boolean activo;

    @SerializedName("mensaje")
    public String mensaje;

    @SerializedName("fecha_fin_renta")
    public Date fecha_fin_renta;

    @SerializedName("mensaje_renta")
    public String mensaje_renta;

    @SerializedName("mensaje1")
    public String mensaje1;

    @SerializedName("mensaje2")
    public String mensaje2;

    @SerializedName("des_mensaje_offline")
    public String des_mensaje_offline;
}
