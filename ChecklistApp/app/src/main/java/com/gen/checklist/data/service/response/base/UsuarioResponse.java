package com.gen.checklist.data.service.response.base;

import com.gen.checklist.model.Usuario;
import com.google.gson.annotations.SerializedName;

public class UsuarioResponse extends BaseResponse{
    @SerializedName("Data")
    public Usuario data;
}
