package com.gen.checklist.data.service.response.base;

import com.gen.checklist.model.Divisiones;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DivisionesResponse extends BaseResponse{

    @SerializedName("Data")
    public List<Divisiones> data;
}
