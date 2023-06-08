package com.gen.checklist.views.viewcontroller;

import com.gen.checklist.model.DataCheckListModel;
import com.gen.checklist.model.Divisiones;
import com.gen.checklist.model.Usuario;

import java.util.List;

public interface LoginViewController extends ViewControllerBase<Usuario> {
    void successDataCheckWS(DataCheckListModel dataCheckListModel);
    void successDivisiones(List<Divisiones> list);
    void datosOffLine(boolean item);
    void successSincronizacionLevantamientos(String msj);
}
