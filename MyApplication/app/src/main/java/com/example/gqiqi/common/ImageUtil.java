package com.example.gqiqi.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtil {

    public static final int NODEFAULT = 0;
    public static void displayGlide(Context context, ImageView imgView, String uri, int flag) {
        switch (flag) {
            case NODEFAULT:
                Glide.with(context)
                        .load(uri)
                        .fitCenter()
                        .crossFade()
                        .into(imgView);
                break;

        }
    }

    /**
     * 清除内存缓存
     */
    public static void clearImageCache(Context context) {
        Glide.get(context).clearDiskCache();
        Glide.get(context).clearMemory();
        System.gc();
    }
}