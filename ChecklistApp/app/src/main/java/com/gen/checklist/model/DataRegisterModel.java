package com.gen.checklist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataRegisterModel {

    @SerializedName("Paises")
    public List<String> listPais;

    @SerializedName("Divisiones")
    public List<Divisiones> listDivisiones;

    @SerializedName("Puestos")
    public List<Puestos> listPuestos;

/*  @SerializedName("Procesos")
    public List<Procesos> listProcesos;

    @SerializedName("SubSubProcesos")
    public List<SubSubProcesos> listSubSubProcesos;

    @SerializedName("Sucursales")
    public List<Sucursal> listSucursal;

    @SerializedName("Regiones")
    public List<Region> listRegiones;

    @SerializedName("MenuEjecutivo")
    public List<MenuEjecutivo> listMenuEjecutivo;*/
}
