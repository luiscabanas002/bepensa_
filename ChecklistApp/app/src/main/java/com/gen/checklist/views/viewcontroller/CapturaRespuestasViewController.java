package com.gen.checklist.views.viewcontroller;

import com.gen.checklist.model.Area;
import com.gen.checklist.model.Factores;
import com.gen.checklist.model.RegistroToc;

import java.util.List;

public interface CapturaRespuestasViewController extends ViewControllerBase<RegistroToc> {
    void cargarGrid(List<Area> listAreas, List<Factores> listFactores);
}
