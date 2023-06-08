package com.gen.checklist.presenter;

import android.content.Context;

import com.gen.checklist.data.controller.CheckListDataController;
import com.gen.checklist.model.Area;
import com.gen.checklist.model.Factores;
import com.gen.checklist.model.OrigenesObservaciones;
import com.gen.checklist.model.RiesgosIdentificados;
import com.gen.checklist.model.Sucursal;
import com.gen.checklist.model.ViewModelItem;
import com.gen.checklist.views.viewcontroller.ListaItemViewController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.gen.checklist.views.activity.ListaItemActivity.ACTO_INSEGURO;
import static com.gen.checklist.views.activity.ListaItemActivity.AREA;
import static com.gen.checklist.views.activity.ListaItemActivity.ORIGEN_OBSERVACION;
import static com.gen.checklist.views.activity.ListaItemActivity.RIESGO_IDENTIFICADO;
import static com.gen.checklist.views.activity.ListaItemActivity.SUCURSAL;

public class ListaItemPresenter {
    private ListaItemViewController mListaItemViewController;
    private CheckListDataController mCheckListDataController;
    private Context mContext;

    public ListaItemPresenter(Context mContext, ListaItemViewController mListaItemViewController) {
        this.mContext = mContext;
        this.mListaItemViewController = mListaItemViewController;
        this.mCheckListDataController = new CheckListDataController(this.mContext);
    }

    public void cargarListaItem(int tipo){
        /*
        *   public static int ORIGEN_OBSERVACION = 1;
            public static int ACTO_INSEGURO = 2;
            public static int RIESGO_IDENTIFICADO = 3;
        * */
        List<ViewModelItem> mViewModelItemList = new ArrayList<>();
        switch (tipo){
            case ORIGEN_OBSERVACION:
                mViewModelItemList = new ArrayList<>();
                List<OrigenesObservaciones> mOrigenesObservacionesList = mCheckListDataController.getOrigenesObservaciones();
                for(OrigenesObservaciones item : mOrigenesObservacionesList){
                    ViewModelItem mViewModelItem = new ViewModelItem();
                    mViewModelItem.id = item.id;
                    mViewModelItem.nombre = item.nombre;
                    mViewModelItemList.add(mViewModelItem);
                }
                break;

            case ACTO_INSEGURO:
                mViewModelItemList = new ArrayList<>();
                List<Factores> mFactoresList = mCheckListDataController.getFactores(1);
                for(Factores item : mFactoresList){
                    ViewModelItem mViewModelItem = new ViewModelItem();
                    mViewModelItem.id = item.id;
                    mViewModelItem.nombre = item.nombre;
                    mViewModelItemList.add(mViewModelItem);
                }
                break;

            case RIESGO_IDENTIFICADO:
                mViewModelItemList = new ArrayList<>();
                List<RiesgosIdentificados> mRiesgosIdentificadosList = mCheckListDataController.getRiesgoIdentificados();
                for(RiesgosIdentificados item : mRiesgosIdentificadosList){
                    ViewModelItem mViewModelItem = new ViewModelItem();
                    mViewModelItem.id = item.id;
                    mViewModelItem.nombre = item.nombre;
                    mViewModelItemList.add(mViewModelItem);
                }
                break;

            case AREA:
                mViewModelItemList = new ArrayList<>();
                List<Area> mAreas = mCheckListDataController.getAreas(Area.TIPO_ACTO_INSEGURO);
                for(Area item : mAreas){
                    ViewModelItem mViewModelItem = new ViewModelItem();
                    mViewModelItem.id = item.id;
                    mViewModelItem.nombre = item.nombre;
                    mViewModelItemList.add(mViewModelItem);
                }
                break;

            case SUCURSAL:
                mViewModelItemList = new ArrayList<>();
                List<Sucursal> mSucursals = mCheckListDataController.getSucursales();
                Collections.sort(mSucursals);
                for(Sucursal item : mSucursals){
                    ViewModelItem mViewModelItem = new ViewModelItem();
                    mViewModelItem.id = item.id;
                    mViewModelItem.nombre = item.nombre;
                    mViewModelItemList.add(mViewModelItem);
                }
                break;
        }
        this.mListaItemViewController.cargarListItem(mViewModelItemList);
    }

}
