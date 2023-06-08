package com.gen.checklist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataCheckListModel {
    @SerializedName("Areas")
    public List<Area> listArea;

    @SerializedName("AreasCondiciones")
    public List<Area> listAreaCondiciones;

    @SerializedName("AreasAmbiental")
    public List<Area> listAreaAmbiental;

    @SerializedName("Origenes")
    public List<OrigenesObservaciones> listOrigenes;

    @SerializedName("Riesgos")
    public List<RiesgosIdentificados> listRiesgos;

    @SerializedName("TiposFactores")
    public List<TiposFactores> listTiposFactores;

    @SerializedName("Sucursales")
    public List<Sucursal> listSucursal;
}
