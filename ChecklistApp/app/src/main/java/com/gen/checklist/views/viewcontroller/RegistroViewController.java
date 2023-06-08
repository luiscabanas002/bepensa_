package com.gen.checklist.views.viewcontroller;

import com.gen.checklist.data.service.response.base.DataDivisionResponse;
import com.gen.checklist.model.DataRegisterModel;

public interface RegistroViewController extends ViewControllerBase<DataRegisterModel> {
    void successRegisterUser();
    void successDataDivision(DataDivisionResponse response);
}
