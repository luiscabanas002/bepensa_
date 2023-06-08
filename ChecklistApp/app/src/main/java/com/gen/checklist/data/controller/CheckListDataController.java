package com.gen.checklist.data.controller;

import android.content.Context;

import com.gen.checklist.data.database.AreaOrmLite;
import com.gen.checklist.data.database.FactoresOrmLite;
import com.gen.checklist.data.database.OrigenesObservacionesOrmLite;
import com.gen.checklist.data.database.RiesgosIdentificadosOrmLite;
import com.gen.checklist.data.database.SucursalOrmLite;
import com.gen.checklist.data.database.TiposFactoresOrmLite;
import com.gen.checklist.data.database.ormlite.base.OrmLiteDatabaseHelper;
import com.gen.checklist.model.Area;
import com.gen.checklist.model.Factores;
import com.gen.checklist.model.OrigenesObservaciones;
import com.gen.checklist.model.RiesgosIdentificados;
import com.gen.checklist.model.Sucursal;
import com.gen.checklist.model.TiposFactores;

import java.sql.SQLException;
import java.util.List;

public class CheckListDataController {
    private Context mContext;
    private OrmLiteDatabaseHelper mOrmLiteDatabaseHelper;
    private AreaOrmLite mAreaOrmLite;
    private FactoresOrmLite mFactoresOrmLite;
    private OrigenesObservacionesOrmLite mOrigenesObservacionesOrmLite;
    private RiesgosIdentificadosOrmLite mRiesgosIdentificadosOrmLite;
    private TiposFactoresOrmLite mTiposFactoresOrmLite;
    private SucursalOrmLite mSucursalOrmLite;

    public CheckListDataController(Context mContext) {
        this.mContext = mContext;
        mOrmLiteDatabaseHelper = new OrmLiteDatabaseHelper(this.mContext);
        mAreaOrmLite = new AreaOrmLite(mOrmLiteDatabaseHelper);
        mFactoresOrmLite = new FactoresOrmLite(mOrmLiteDatabaseHelper);
        mOrigenesObservacionesOrmLite = new OrigenesObservacionesOrmLite(mOrmLiteDatabaseHelper);
        mRiesgosIdentificadosOrmLite = new RiesgosIdentificadosOrmLite(mOrmLiteDatabaseHelper);
        mTiposFactoresOrmLite = new TiposFactoresOrmLite(mOrmLiteDatabaseHelper);
        mSucursalOrmLite = new SucursalOrmLite(mOrmLiteDatabaseHelper);
    }

    public boolean addArea(List<Area> mAreas, boolean eliminar) {
        if(eliminar) {
            mAreaOrmLite.deleteElements(mAreaOrmLite.selectAll());
        }
        if (mAreaOrmLite.addElements(mAreas) > 0) {
            return true;
        }
        return false;
    }

    public List<Area> getAreas() {
        return mAreaOrmLite.selectAll();
    }

    public List<Area> getAreas(String tipo) {
        try {
            return mAreaOrmLite.getQueryBuilder().where().eq(Area.TIPO, tipo).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addFactores(List<Factores> mFactores) {
        if (mFactoresOrmLite.addElements(mFactores) > 0) {
            return true;
        }
        return false;
    }

    public List<Factores> geFactores(int idTipoFactor) {
        try {
            return mFactoresOrmLite.getQueryBuilder().where().eq(Factores.ID_TIPO_FACTOR, idTipoFactor).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addSucursales(List<Sucursal> mSucursalList) {
        mSucursalOrmLite.deleteElements(mSucursalOrmLite.selectAll());
        if (mSucursalOrmLite.addElements(mSucursalList) > 0) {
            return true;
        }
        return false;
    }

    public List<Sucursal> getSucursales() {
        return mSucursalOrmLite.selectAll();
    }

    public Sucursal getSucursal(int id) {
        try {
            return mSucursalOrmLite.getQueryBuilder().where().eq(Sucursal.ID, id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean addOrigenesObservaciones(List<OrigenesObservaciones> mOrigenesObservaciones) {
        mOrigenesObservacionesOrmLite.deleteElements(mOrigenesObservacionesOrmLite.selectAll());
        if (mOrigenesObservacionesOrmLite.addElements(mOrigenesObservaciones) > 0) {
            return true;
        }
        return false;
    }

    public List<OrigenesObservaciones> getOrigenesObservaciones() {
        return mOrigenesObservacionesOrmLite.selectAll();
    }

    public boolean addRiesgoIdentificados(List<RiesgosIdentificados> mRiesgosIdentificados) {
        mRiesgosIdentificadosOrmLite.deleteElements(mRiesgosIdentificadosOrmLite.selectAll());
        if (mRiesgosIdentificadosOrmLite.addElements(mRiesgosIdentificados) > 0) {
            return true;
        }
        return false;
    }

    public List<RiesgosIdentificados> getRiesgoIdentificados() {
        return mRiesgosIdentificadosOrmLite.selectAll();
    }

    public boolean addTiposFactores(List<TiposFactores> mTiposFactores) {
        mTiposFactoresOrmLite.deleteElements(mTiposFactoresOrmLite.selectAll());
        mFactoresOrmLite.deleteElements(mFactoresOrmLite.selectAll());
        for (TiposFactores mTiposFactor : mTiposFactores) {
            if (mTiposFactoresOrmLite.addElement(mTiposFactor) > 0) {
                addFactores(mTiposFactor.factores);
            }
        }
        return false;
    }

    public List<TiposFactores> getTiposFactores() {
        return mTiposFactoresOrmLite.selectAll();
    }

    public String Mensaje1(String tipo) {
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
        TiposFactores mTiposFactores = mTiposFactoresOrmLite.selectById(idTipo);
        String mensaje1 = "";
        if(mTiposFactores != null){
            mensaje1 = mTiposFactores.mensaje1;
        }
        return mensaje1;
    }

    public String Mensaje2(String tipo) {
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
        TiposFactores mTiposFactores = mTiposFactoresOrmLite.selectById(idTipo);
        String mensaje2 = "";
        if(mTiposFactores != null){
            mensaje2 = mTiposFactores.mensaje2;
        }
        return mensaje2;
    }

    public List<Factores> getFactores() {
        return mFactoresOrmLite.selectAll();
    }

    public List<Factores> getFactores(int idTipoFactor) {
        try {
            return mFactoresOrmLite.getQueryBuilder().where().eq(Factores.ID_TIPO_FACTOR, idTipoFactor).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
