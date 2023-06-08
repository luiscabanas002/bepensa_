package com.gen.checklist.presenter;

import android.content.Context;

import com.gen.checklist.data.controller.CheckListDataController;
import com.gen.checklist.model.Area;
import com.gen.checklist.model.Factores;
import com.gen.checklist.views.viewcontroller.CapturaRespuestasViewController;

import java.util.List;

public class CapturaRespuestasPresenter {
    private CapturaRespuestasViewController mCapturaRespuestasViewController;
    private CheckListDataController mCheckListDataController;
    private Context mContext;

    public CapturaRespuestasPresenter(CapturaRespuestasViewController mCapturaRespuestasViewController, Context mContext) {
        this.mCapturaRespuestasViewController = mCapturaRespuestasViewController;
        this.mContext = mContext;
        this.mCheckListDataController = new CheckListDataController(this.mContext);
    }

    public void CargarVista(String tipo) {
        int idTipo = 1;
        switch (tipo){
            case Area.TIPO_ACTO_INSEGURO:
                idTipo = 1;
                break;
            case Area.TIPO_CONDICIONES:
                idTipo = 2;
                break;
            case Area.TIPO_AMBIENTAL:
                idTipo = 3;
                break;
        }
        List<Area> mAreaList = mCheckListDataController.getAreas(tipo);
        List<Factores> mFactoresList = mCheckListDataController.getFactores(idTipo);
        this.mCapturaRespuestasViewController.cargarGrid(mAreaList, mFactoresList);
    }
}
