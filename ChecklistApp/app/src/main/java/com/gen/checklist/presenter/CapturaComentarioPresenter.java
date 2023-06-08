package com.gen.checklist.presenter;

import android.content.Context;

import com.gen.checklist.R;
import com.gen.checklist.data.service.ClientService;
import com.gen.checklist.data.service.response.base.ContadorEncuestaResponse;
import com.gen.checklist.model.RegistroToc;
import com.gen.checklist.views.viewcontroller.CapturaComentariosViewController;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CapturaComentarioPresenter {
    private CapturaComentariosViewController mCapturaComentariosViewController;
    private ClientService mClientService;
    private Context mContext;

    public CapturaComentarioPresenter(CapturaComentariosViewController mCapturaComentariosViewController, Context mContext) {
        this.mCapturaComentariosViewController = mCapturaComentariosViewController;
        this.mContext = mContext;
        if (this.mClientService == null) {
            this.mClientService = new ClientService();
        }
    }

    public void enviarEncuesta(final RegistroToc mRegistroToc) {
        mCapturaComentariosViewController.showProgressDialog("Enviando datos");
        Gson gson = new Gson();
        String regiroTocJson = gson.toJson(mRegistroToc);
        mClientService
                .getAPI(mContext)
                .enviarEncuesta(regiroTocJson)
                .enqueue(new Callback<ContadorEncuestaResponse>() {
                    @Override
                    public void onResponse(Call<ContadorEncuestaResponse> call, Response<ContadorEncuestaResponse> response) {
                        ContadorEncuestaResponse mResponse = response.body();
                        if (mResponse != null) {
                            if (mResponse.status == 1) {
                                mCapturaComentariosViewController.dissmissProgressDialog();
                                mCapturaComentariosViewController.successWS(mResponse.data);
                            } else {
                                mCapturaComentariosViewController.dissmissProgressDialog();
                                mCapturaComentariosViewController.failureWS(mResponse.mensaje);
                            }
                        } else {
                            mCapturaComentariosViewController.dissmissProgressDialog();
                            mCapturaComentariosViewController.failureWS(mContext.getString(R.string.message_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ContadorEncuestaResponse> call, Throwable t) {
                        mCapturaComentariosViewController.dissmissProgressDialog();
                        mCapturaComentariosViewController.failureWS(mContext.getString(R.string.message_error));
                    }
                });
    }

    public void enviarEncuestaCondiciones(final RegistroToc mRegistroToc) {
        mCapturaComentariosViewController.showProgressDialog("Enviando datos");
        Gson gson = new Gson();
        String regiroTocJson = gson.toJson(mRegistroToc);
        mClientService
                .getAPI(mContext)
                .enviarEncuestaCondiciones(regiroTocJson)
                .enqueue(new Callback<ContadorEncuestaResponse>() {
                    @Override
                    public void onResponse(Call<ContadorEncuestaResponse> call, Response<ContadorEncuestaResponse> response) {
                        ContadorEncuestaResponse mResponse = response.body();
                        if (mResponse != null) {
                            if (mResponse.status == 1) {
                                mCapturaComentariosViewController.dissmissProgressDialog();
                                mCapturaComentariosViewController.successWS(mResponse.data);
                            } else {
                                mCapturaComentariosViewController.dissmissProgressDialog();
                                mCapturaComentariosViewController.failureWS(mResponse.mensaje);
                            }
                        } else {
                            mCapturaComentariosViewController.dissmissProgressDialog();
                            mCapturaComentariosViewController.failureWS(mContext.getString(R.string.message_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ContadorEncuestaResponse> call, Throwable t) {
                        mCapturaComentariosViewController.dissmissProgressDialog();
                        mCapturaComentariosViewController.failureWS(mContext.getString(R.string.message_error));
                    }
                });
    }

    public void enviarEncuestaAmbiental(final RegistroToc mRegistroToc) {
        mCapturaComentariosViewController.showProgressDialog("Enviando datos");
        Gson gson = new Gson();
        String regiroTocJson = gson.toJson(mRegistroToc);
        mClientService
                .getAPI(mContext)
                .enviarEncuestaAmbientales(regiroTocJson)
                .enqueue(new Callback<ContadorEncuestaResponse>() {
                    @Override
                    public void onResponse(Call<ContadorEncuestaResponse> call, Response<ContadorEncuestaResponse> response) {
                        ContadorEncuestaResponse mResponse = response.body();
                        if (mResponse != null) {
                            if (mResponse.status == 1) {
                                mCapturaComentariosViewController.dissmissProgressDialog();
                                mCapturaComentariosViewController.successWS(mResponse.data);
                            } else {
                                mCapturaComentariosViewController.dissmissProgressDialog();
                                mCapturaComentariosViewController.failureWS(mResponse.mensaje);
                            }
                        } else {
                            mCapturaComentariosViewController.dissmissProgressDialog();
                            mCapturaComentariosViewController.failureWS(mContext.getString(R.string.message_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ContadorEncuestaResponse> call, Throwable t) {
                        mCapturaComentariosViewController.dissmissProgressDialog();
                        mCapturaComentariosViewController.failureWS(mContext.getString(R.string.message_error));
                    }
                });
    }

}
