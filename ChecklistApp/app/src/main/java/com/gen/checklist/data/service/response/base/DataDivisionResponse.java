package com.gen.checklist.data.service.response.base;

import com.gen.checklist.model.Procesos;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataDivisionResponse extends BaseResponse{
    @SerializedName("Data")
    public List<Procesos> data;
}
