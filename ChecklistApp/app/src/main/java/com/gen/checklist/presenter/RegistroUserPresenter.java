package com.gen.checklist.presenter;

import android.content.Context;

import com.gen.checklist.R;
import com.gen.checklist.data.service.ClientService;
import com.gen.checklist.data.service.response.base.BaseResponse;
import com.gen.checklist.data.service.response.base.DataDivisionResponse;
import com.gen.checklist.data.service.response.base.DataRegisterResponse;
import com.gen.checklist.model.ViewModelRegistro;
import com.gen.checklist.views.viewcontroller.RegistroViewController;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroUserPresenter {
    private RegistroViewController mViewController;
    private ClientService mClientService;
    private Context mContext;

    public RegistroUserPresenter(RegistroViewController mViewController, Context mContext) {
        this.mViewController = mViewController;
        this.mContext = mContext;
        if (this.mClientService == null) {
            this.mClientService = new ClientService();
        }
    }

    public void registrar(ViewModelRegistro mUser) {
        mViewController.showProgressDialog("Espere un momento");
        Gson gson = new Gson();
        String userJson = gson.toJson(mUser);
        mClientService
                .getAPI(mContext)
                .registrarUsuario(userJson)
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        BaseResponse mResponse = response.body();
                        if (mResponse != null) {
                            if (mResponse.status == 1) {
                                mViewController.dissmissProgressDialog();
                                mViewController.successRegisterUser();
                            } else {
                                mViewController.dissmissProgressDialog();
                                mViewController.failureWS(mResponse.mensaje);
                            }
                        } else {
                            mViewController.dissmissProgressDialog();
                            mViewController.failureWS(mContext.getString(R.string.message_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        mViewController.dissmissProgressDialog();
                        mViewController.failureWS(mContext.getString(R.string.message_error));
                    }
                });
    }

    public void descargarDatos() {
        mViewController.showProgressDialog("Obteniendo datos");
        mClientService
                .getAPI(mContext)
                .getInfoRegistro()
                .enqueue(new Callback<DataRegisterResponse>() {
                    @Override
                    public void onResponse(Call<DataRegisterResponse> call, Response<DataRegisterResponse> response) {
                        DataRegisterResponse mResponse = response.body();
                        if (mResponse != null) {
                            if (mResponse.status == 1) {
                                mViewController.successWS(mResponse.data);
                                mViewController.dissmissProgressDialog();
                            } else {
                                mViewController.dissmissProgressDialog();
                                mViewController.failureWS(mResponse.mensaje);
                            }
                        } else {
                            mViewController.dissmissProgressDialog();
                            mViewController.failureWS(mContext.getString(R.string.message_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<DataRegisterResponse> call, Throwable t) {
                        mViewController.dissmissProgressDialog();
                        mViewController.failureWS(mContext.getString(R.string.message_error));
                    }
                });
    }

    public void datosDivision(int idDivision) {
        mViewController.showProgressDialog("Obteniendo datos");
        mClientService
                .getAPI(mContext)
                .getInfoDivision(idDivision)
                .enqueue(new Callback<DataDivisionResponse>() {
                    @Override
                    public void onResponse(Call<DataDivisionResponse> call, Response<DataDivisionResponse> response) {
                        DataDivisionResponse mResponse = response.body();
                        if (mResponse != null) {
                            if (mResponse.status == 1) {
                                mViewController.successDataDivision(mResponse);
                                mViewController.dissmissProgressDialog();
                            } else {
                                mViewController.dissmissProgressDialog();
                                mViewController.failureWS(mResponse.mensaje);
                            }
                        } else {
                            mViewController.dissmissProgressDialog();
                            mViewController.failureWS(mContext.getString(R.string.message_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<DataDivisionResponse> call, Throwable t) {
                        mViewController.dissmissProgressDialog();
                        mViewController.failureWS(mContext.getString(R.string.message_error));
                    }
                });
    }
}
