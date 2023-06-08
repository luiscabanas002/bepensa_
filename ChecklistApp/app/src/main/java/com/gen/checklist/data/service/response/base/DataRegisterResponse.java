package com.gen.checklist.data.service.response.base;

import com.gen.checklist.model.DataRegisterModel;
import com.google.gson.annotations.SerializedName;

public class DataRegisterResponse extends BaseResponse{

    @SerializedName("Data")
    public DataRegisterModel data;
}
