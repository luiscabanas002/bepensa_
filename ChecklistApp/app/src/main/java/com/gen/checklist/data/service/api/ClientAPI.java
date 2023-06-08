package com.gen.checklist.data.service.api;


import com.gen.checklist.data.service.response.base.BaseResponse;
import com.gen.checklist.data.service.response.base.ContadorEncuestaResponse;
import com.gen.checklist.data.service.response.base.DataCheckListResponse;
import com.gen.checklist.data.service.response.base.DataDivisionResponse;
import com.gen.checklist.data.service.response.base.DataRegisterResponse;
import com.gen.checklist.data.service.response.base.DivisionesResponse;
import com.gen.checklist.data.service.response.base.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ClientAPI {
    @FormUrlEncoded
    @POST("recuperarUsuario")
    Call<UsuarioResponse> validarUsuario(@Field("json") String Json);

    @FormUrlEncoded
    @POST("enviarEncuesta_v2")
    Call<ContadorEncuestaResponse> enviarEncuesta(@Field("json") String Json);

    @FormUrlEncoded
    @POST("enviarEncuesta_ambientales_v2")
    Call<ContadorEncuestaResponse> enviarEncuestaAmbientales(@Field("json") String Json);

    @FormUrlEncoded
    @POST("enviarEncuesta_condiciones_v2")
    Call<ContadorEncuestaResponse> enviarEncuestaCondiciones(@Field("json") String Json);

    @FormUrlEncoded
    @POST("getDataCheckList_v2")
    Call<DataCheckListResponse> getDataCheck(@Field("json") String Json);

    @POST("getDivisiones")
    Call<DivisionesResponse> getDivisiones();

    @POST("getInfoUser")
    Call<DataRegisterResponse> getInfoUser();

    @POST("getInfoRegistro")
    Call<DataRegisterResponse> getInfoRegistro();

    @FormUrlEncoded
    @POST("getInfoDivision")
    Call<DataDivisionResponse> getInfoDivision(@Field("idDivision") int idDivision);

    @FormUrlEncoded
    @POST("registrarUsuario")
    Call<BaseResponse> registrarUsuario(@Field("json") String Json);

    @FormUrlEncoded
    @POST("sicronizar")
    Call<BaseResponse> sicronizar(@Field("json") String Json);

    @FormUrlEncoded
    @POST("registrarDispositivo")
    Call<BaseResponse> registrarDispositivo(@Field("json") String Json);
}
