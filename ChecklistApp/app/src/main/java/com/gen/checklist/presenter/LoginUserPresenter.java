package com.gen.checklist.presenter;

import android.content.Context;

import com.gen.checklist.R;
import com.gen.checklist.data.controller.CheckListDataController;
import com.gen.checklist.data.service.ClientService;
import com.gen.checklist.data.service.response.base.BaseResponse;
import com.gen.checklist.data.service.response.base.DataCheckListResponse;
import com.gen.checklist.data.service.response.base.DivisionesResponse;
import com.gen.checklist.data.service.response.base.UsuarioResponse;
import com.gen.checklist.model.Area;
import com.gen.checklist.model.DataCheckListModel;
import com.gen.checklist.model.Divisiones;
import com.gen.checklist.model.Factores;
import com.gen.checklist.model.RegistroToc;
import com.gen.checklist.model.Usuario;
import com.gen.checklist.views.utils.SharedPrefsUtils;
import com.gen.checklist.views.viewcontroller.LoginViewController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUserPresenter {
    private LoginViewController mLoginViewController;
    private ClientService mClientService;
    private CheckListDataController mCheckListDataController;
    private Context mContext;

    public LoginUserPresenter(LoginViewController mLoginViewController, Context mContext) {
        this.mLoginViewController = mLoginViewController;
        this.mContext = mContext;
        if (this.mClientService == null) {
            this.mClientService = new ClientService();
        }

        if (this.mCheckListDataController == null) {
            this.mCheckListDataController = new CheckListDataController(mContext);
        }
    }

    public void validarCodigo(Usuario mUser) {
        mLoginViewController.showProgressDialog("Espere un momento");
        Gson gson = new Gson();
        String userJson = gson.toJson(mUser);
        mClientService
                .getAPI(mContext)
                .validarUsuario(userJson)
                .enqueue(new Callback<UsuarioResponse>() {
                    @Override
                    public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                        UsuarioResponse mResponse = response.body();
                        if (mResponse != null) {
                            if (mResponse.status == 1) {
                                mLoginViewController.dissmissProgressDialog();
                                mLoginViewController.successWS(mResponse.data);
                            } else {
                                mLoginViewController.dissmissProgressDialog();
                                mLoginViewController.failureWS(mResponse.mensaje);
                            }
                        } else {
                            mLoginViewController.dissmissProgressDialog();
                            mLoginViewController.failureWS(mContext.getString(R.string.message_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                        mLoginViewController.dissmissProgressDialog();
                        mLoginViewController.failureWS(mContext.getString(R.string.message_error));
                    }
                });
    }

    public void obtenerDivisiones() {
        mLoginViewController.showProgressDialog("Obteniendo datos");
        mClientService
                .getAPI(mContext)
                .getDivisiones()
                .enqueue(new Callback<DivisionesResponse>() {
                    @Override
                    public void onResponse(Call<DivisionesResponse> call, Response<DivisionesResponse> response) {
                        DivisionesResponse mResponse = response.body();
                        if (mResponse != null) {
                            if (mResponse.status == 1) {
                                mLoginViewController.dissmissProgressDialog();
                                mLoginViewController.successDivisiones(mResponse.data);
                            } else {
                                mLoginViewController.dissmissProgressDialog();
                                mLoginViewController.failureWS(mResponse.mensaje);
                            }
                        } else {
                            mLoginViewController.dissmissProgressDialog();
                            mLoginViewController.failureWS(mContext.getString(R.string.message_error));
                        }


                    }

                    @Override
                    public void onFailure(Call<DivisionesResponse> call, Throwable t) {
                        mLoginViewController.dissmissProgressDialog();
                        mLoginViewController.failureWS(mContext.getString(R.string.message_error));
                    }
                });
    }

    public void sincronizar(String json) {
        mLoginViewController.showProgressDialog("Sincronizando datos");

        mClientService
                .getAPI(mContext)
                .sicronizar(json)
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        BaseResponse mResponse = response.body();
                        if (mResponse != null) {
                            if (mResponse.status == 1) {
                                mLoginViewController.dissmissProgressDialog();
                                mLoginViewController.successSincronizacionLevantamientos(mResponse.mensaje);
                            } else {
                                mLoginViewController.dissmissProgressDialog();
                                mLoginViewController.failureWS(mResponse.mensaje);
                            }
                        } else {
                            mLoginViewController.dissmissProgressDialog();
                            mLoginViewController.failureWS(mContext.getString(R.string.message_error));
                        }


                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        mLoginViewController.dissmissProgressDialog();
                        mLoginViewController.failureWS(mContext.getString(R.string.message_error));
                    }
                });
    }

    public void descargarDatosCheck() {
        Divisiones division = new Divisiones();
        division.id = SharedPrefsUtils.getIntegerPreference(mContext,Divisiones.ID, -1);
        Gson gson = new Gson();
        String json = gson.toJson(division);
        mLoginViewController.showProgressDialog("Obteniendo datos");
        mClientService
                .getAPI(mContext)
                .getDataCheck(json)
                .enqueue(new Callback<DataCheckListResponse>() {
                    @Override
                    public void onResponse(Call<DataCheckListResponse> call, Response<DataCheckListResponse> response) {
                        DataCheckListResponse mResponse = response.body();
                        if (mResponse != null) {
                            if (mResponse.status == 1) {
                                GuardarDatosCheck(mResponse.data);
                                mLoginViewController.successDataCheckWS(mResponse.data);
                                mLoginViewController.dissmissProgressDialog();
                            } else {
                                mLoginViewController.dissmissProgressDialog();
                                mLoginViewController.failureWS(mResponse.mensaje);
                            }
                        } else {
                            mLoginViewController.dissmissProgressDialog();
                            mLoginViewController.failureWS(mContext.getString(R.string.message_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<DataCheckListResponse> call, Throwable t) {
                        mLoginViewController.dissmissProgressDialog();
                        mLoginViewController.failureWS(mContext.getString(R.string.message_error));
                    }
                });
    }

    public void GuardarDatosCheck(DataCheckListModel mDataCheckListModel){
        mCheckListDataController.addArea(mDataCheckListModel.listArea, true);
        mCheckListDataController.addArea(mDataCheckListModel.listAreaCondiciones, false);
        mCheckListDataController.addArea(mDataCheckListModel.listAreaAmbiental, false);
        mCheckListDataController.addOrigenesObservaciones(mDataCheckListModel.listOrigenes);
        mCheckListDataController.addRiesgoIdentificados(mDataCheckListModel.listRiesgos);
        mCheckListDataController.addTiposFactores(mDataCheckListModel.listTiposFactores);
        mCheckListDataController.addSucursales(mDataCheckListModel.listSucursal);

    }

    public void validarDatosOffLine() {
        List<Area> mAreaList = mCheckListDataController.getAreas();
        List<Factores> mFactoresList = mCheckListDataController.getFactores();
        String jsonDivsiones = SharedPrefsUtils.getStringPreference(mContext, Divisiones.class.getName());
        Gson mGson = new Gson();
        List<Divisiones> mDivisionesList = mGson.fromJson(jsonDivsiones, new TypeToken<List<Divisiones>>() {
        }.getType());

        boolean success = ((mAreaList != null && mAreaList.size() > 0) && (mFactoresList != null && mFactoresList.size() > 0) && (mDivisionesList != null && mDivisionesList.size() > 0));
        mLoginViewController.datosOffLine(success);
    }

    public boolean datosOfflineDescargados() {



        List<Area> mAreaList = mCheckListDataController.getAreas();
        List<Factores> mFactoresList = mCheckListDataController.getFactores();
        String jsonDivsiones = SharedPrefsUtils.getStringPreference(mContext, Divisiones.class.getName());
        Gson mGson = new Gson();
        List<Divisiones> mDivisionesList = mGson.fromJson(jsonDivsiones, new TypeToken<List<Divisiones>>() {
        }.getType());

        return  ((mAreaList != null && mAreaList.size() > 0) && (mFactoresList != null && mFactoresList.size() > 0) && (mDivisionesList != null && mDivisionesList.size() > 0));
    }

    public int levantamientosPendientesSincronizar(){
        Gson mGson = new Gson();
        List<RegistroToc> mRegistroTocList;
        String jsonRegistros = SharedPrefsUtils.getStringPreference(mContext, RegistroToc.OBJ);

        if (jsonRegistros != null && !jsonRegistros.isEmpty()) {
            mRegistroTocList = mGson.fromJson(jsonRegistros, new TypeToken<List<RegistroToc>>() {
            }.getType());

            if (mRegistroTocList != null && mRegistroTocList.size() > 0) {
                return mRegistroTocList.size();
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }
}
