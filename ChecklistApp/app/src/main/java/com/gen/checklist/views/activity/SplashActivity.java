package com.gen.checklist.views.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.gen.checklist.R;
import com.gen.checklist.data.service.ClientService;
import com.gen.checklist.data.service.response.base.BaseResponse;
import com.gen.checklist.data.service.response.base.ValidateResponse;
import com.gen.checklist.model.DataCheckListModel;
import com.gen.checklist.model.Divisiones;
import com.gen.checklist.model.Usuario;
import com.gen.checklist.presenter.LoginUserPresenter;
import com.gen.checklist.views.activity.base.BaseActivity;
import com.gen.checklist.views.utils.SharedPrefsUtils;
import com.gen.checklist.views.utils.Utils;
import com.gen.checklist.views.viewcontroller.LoginViewController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity implements LoginViewController {

    private LoginUserPresenter mLoginUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        mLoginUserPresenter = new LoginUserPresenter(this, SplashActivity.this);

        hideKeyboard();

        if (checkDatasMovil() || checkWifi()) {
            SharedPrefsUtils.setBooleanPreference(getApplicationContext(), "sync", false);
            ValidateUrl mValidateUrl = new ValidateUrl();
            mValidateUrl.execute();
        } else {
            goToLogin();
        }
    }

    private void goToLogin() {
        Intent mIntent = new Intent(SplashActivity.this, LoginUserActivity.class);
        startActivity(mIntent);
        finish();
    }

    @Override
    public void successDataCheckWS(DataCheckListModel dataCheckListModel) {

    }

    @Override
    public void successDivisiones(List<Divisiones> list) {
    }

    @Override
    public void datosOffLine(boolean item) {

    }

    @Override
    public void successSincronizacionLevantamientos(String msj) {

    }

    @Override
    public void failureWS(String message) {

    }

    @Override
    public void successWS(Usuario parametro) {

    }

    @Override
    public void showProgressDialog(String message) {
        showOnProgressDialog(message);
    }

    @Override
    public void dissmissProgressDialog() {
        dismissProgressDialog();
    }

    public void tryDownloadConfig() {
        showConfirmDialog("Error", "Ocurrio un error al descargar las configuraciones", "Reintentar", "Cerrar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ValidateUrl mValidateUrl = new ValidateUrl();
                        mValidateUrl.execute();
                    }
                },
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
    }

    private class ValidateUrl extends AsyncTask<Void, Void, Void> {
        ValidateResponse mValidateResponse;

        @Override
        protected void onPreExecute() {
            //showOnProgressDialog("Obteniendo parámetros");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String jsonConfig = Utils.getJSON(ClientService.URL_CONFIG_PROD);
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd")
                        .serializeNulls()
                        .create();
                mValidateResponse = gson.fromJson(jsonConfig, ValidateResponse.class);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dissmissProgressDialog();
            try {
                if (mValidateResponse != null) {
                    if (mValidateResponse.activo) {
                        if (Utils.getDate().before(mValidateResponse.fecha_fin_renta) || Utils.getDate().equals(mValidateResponse.fecha_fin_renta)) {
                            //SharedPrefsUtils.setStringPreference(SplashActivity.this, getString(R.string.url), "http://bbebidas-001-site1.etempurl.com/sostenibilidapp/Service/BepensaService.asmx/");
                            //SharedPrefsUtils.setStringPreference(SplashActivity.this, getString(R.string.url_multimedia), "http://192.168.1.71/Sostenibilidapp/");
                            SharedPrefsUtils.setStringPreference(SplashActivity.this, getString(R.string.url), mValidateResponse.url);
                            SharedPrefsUtils.setStringPreference(SplashActivity.this, getString(R.string.url_multimedia), mValidateResponse.url_multimedia);
                            SharedPrefsUtils.setStringPreference(SplashActivity.this, getString(R.string.menssaje1), mValidateResponse.mensaje1);
                            SharedPrefsUtils.setStringPreference(SplashActivity.this, getString(R.string.menssaje2), mValidateResponse.mensaje2);
                            SharedPrefsUtils.setStringPreference(SplashActivity.this, getString(R.string.infoSincronizacion), mValidateResponse.des_mensaje_offline);

                            String token = SharedPrefsUtils.getStringPreference(SplashActivity.this, "TOKEN");
                            if (token == null || token.isEmpty()) {
                                goToLogin();
                            } else {
                                ClientService mClientService = new ClientService();
                                mClientService
                                        .getAPI(getApplicationContext())
                                        .registrarDispositivo(token)
                                        .enqueue(new Callback<BaseResponse>() {
                                            @Override
                                            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                                SharedPrefsUtils.setStringPreference(getApplicationContext(), "TOKEN", "");
                                                goToLogin();
                                            }

                                            @Override
                                            public void onFailure(Call<BaseResponse> call, Throwable t) {
                                                goToLogin();
                                            }
                                        });
                            }
                        } else {
                            showInfoDialog("Información", mValidateResponse.mensaje_renta, "OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                        }
                    } else {
                        showInfoDialog("Información", mValidateResponse.mensaje, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                    }
                } else {
                    tryDownloadConfig();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                tryDownloadConfig();
            }
        }
    }
}
