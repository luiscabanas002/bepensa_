package com.gen.checklist.data.service.response.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luis Cabanas on 19/03/2018.
 */
public class BaseResponse {

    public BaseResponse(int status, String mensaje) {
        this.status = status;
        this.mensaje = mensaje;
    }

    public BaseResponse() {
    }

    @SerializedName("Estatus")
    public int status;

    @SerializedName("Mensaje")
    public String mensaje;
}
