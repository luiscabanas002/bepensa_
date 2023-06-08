package com.gen.checklist.data.service;

import android.content.Context;

import com.gen.checklist.R;
import com.gen.checklist.data.service.api.ClientAPI;
import com.gen.checklist.views.utils.SharedPrefsUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Luis Cabanas on 19/03/2018.
 */

public class ClientService {
    private Retrofit mRetrofit = null;

    public static final String URL_CONFIG_PROD = "https://drive.google.com/uc?authuser=0&id=1iOfIpG-GVJFdhw1o7ObUiv__JSyg8-cr&export=download";
    public static final String URL_CONFIG_SANDBOX = "https://drive.google.com/uc?authuser=0&id=1s7RHGy9ldODsmpus7nozmtlkkBpDHAha&export=download";

    public ClientAPI getAPI(Context mContext) {
        if (mRetrofit == null) {

            OkHttpClient client = new OkHttpClient
                    .Builder().connectTimeout(10, TimeUnit.HOURS)
                    .writeTimeout(10, TimeUnit.HOURS)
                    .readTimeout(10, TimeUnit.HOURS)
                    .build();


            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .serializeNulls()
                    .create();

            String URL = SharedPrefsUtils.getStringPreference(mContext,mContext.getString(R.string.url));

            mRetrofit = new Retrofit
                    .Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }

        return mRetrofit.create(ClientAPI.class);
    }
}
