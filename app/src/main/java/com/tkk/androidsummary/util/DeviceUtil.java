package com.tkk.androidsummary.util;

import android.content.Context;
import android.provider.Settings;

/**
 * Created  on 2018-05-16
 * @author 唐开阔
 * @describe
 */
public class DeviceUtil {
    /**
     * 获得AndroidId
     * @param context 上下文
     * @return AndroidId
     */
    public static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }
}
