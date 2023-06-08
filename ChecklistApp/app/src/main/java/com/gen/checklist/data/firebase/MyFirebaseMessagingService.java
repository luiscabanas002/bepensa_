package com.gen.checklist.data.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.gen.checklist.R;
import com.gen.checklist.data.service.ClientService;
import com.gen.checklist.data.service.response.base.BaseResponse;
import com.gen.checklist.model.Dispositivo;
import com.gen.checklist.views.utils.SharedPrefsUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static String TOKEN_FIREBASE = "TOKEN_FIREBASE";

    @Override
    public void onNewToken(@NonNull String s) {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.i("Refreshed token: ", refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        SharedPrefsUtils.setStringPreference(getApplicationContext(), TOKEN_FIREBASE, refreshedToken);

        ClientService mClientService = new ClientService();

        Dispositivo mDispositivo = new Dispositivo();
        mDispositivo.so = "ANDROID";
        mDispositivo.token = refreshedToken;
        Gson mGson = new Gson();
        String json = mGson.toJson(mDispositivo);
        String URL = SharedPrefsUtils.getStringPreference(getApplicationContext(), getApplicationContext().getString(R.string.url));
        if (URL != null && !URL.isEmpty()) {
            mClientService
                    .getAPI(getApplicationContext())
                    .registrarDispositivo(json)
                    .enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            BaseResponse mResponse = response.body();
                            if (mResponse != null) {
                                if (mResponse.status == 1) {

                                } else {

                                }
                            } else {

                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {

                        }
                    });
        }else{
            SharedPrefsUtils.setStringPreference(getApplicationContext(),"TOKEN",json);
        }
    }
}
