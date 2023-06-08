package com.gen.checklist.data.service.response.base;

import com.gen.checklist.model.DataCheckListModel;
import com.google.gson.annotations.SerializedName;

public class DataCheckListResponse extends BaseResponse{

    @SerializedName("Data")
    public DataCheckListModel data;
}
