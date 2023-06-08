package com.gen.checklist.views.utils;


import android.content.Context;

import com.gen.checklist.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class ImageUtils {

    public static DisplayImageOptions mDisplayImageOptions = new DisplayImageOptions.Builder().cacheInMemory(true)
            .cacheOnDisc(true).resetViewBeforeLoading(true)
            .showImageForEmptyUri(R.drawable.ic_default)
            .showImageOnFail(R.drawable.ic_image_broken)
            .showImageOnLoading(R.drawable.ic_loading).build();

    public static DisplayImageOptions mDisplayImageOptionsCheck = new DisplayImageOptions.Builder().cacheInMemory(true)
            .cacheOnDisc(true).resetViewBeforeLoading(true)
            .showImageForEmptyUri(R.drawable.ic_default)
            .showImageOnFail(R.drawable.ic_default_bepensa)
            .showImageOnLoading(R.drawable.ic_loading).build();

    public static DisplayImageOptions mDisplayImageOptionsCheckAmbiental = new DisplayImageOptions.Builder().cacheInMemory(true)
            .cacheOnDisc(true).resetViewBeforeLoading(true)
            .showImageForEmptyUri(R.drawable.ic_default)
            .showImageOnFail(R.drawable.ic_default_bepensa)
            .showImageOnLoading(R.drawable.ic_loading).build();

    public static String getUrlImage(Context mContext, String url){
        String urlImage = (SharedPrefsUtils.getStringPreference(mContext,mContext.getString(R.string.url_multimedia))) +  url.replace("~","");
        return urlImage;
    }
}
