package com.tkk.androidsummary.knowledgepoint.frame.Retrofit;

import android.os.Build;

import com.tkk.androidsummary.BuildConfig;
import com.tkk.androidsummary.LeakApplication;
import com.tkk.androidsummary.util.DeviceUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created  on 2018-05-17
 *
 * @author 唐开阔
 * @describe
 */
public class ParmMapUtils {
    public static Map<String,String> getParmMap(){
        Map<String,String> mapParams = new HashMap<>();
        mapParams.put("version", "1.0");
        mapParams.put("appCode", BuildConfig.VERSION_NAME);
        mapParams.put("deviceType", Build.MODEL);
        mapParams.put("deviceId", DeviceUtil.getAndroidId(LeakApplication.getContext()));
        return mapParams;
    }
}
