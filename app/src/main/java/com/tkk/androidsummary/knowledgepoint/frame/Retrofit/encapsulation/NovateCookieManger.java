package com.tkk.androidsummary.knowledgepoint.frame.Retrofit.encapsulation;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created  on 2018-05-11
 *
 * @author 唐开阔
 * @describe Cookie管理器
 */

public class NovateCookieManger implements CookieJar {
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return null;
    }
//    private static final String TAG = "NovateCookieManger";
//    private static Context mContext;
//    private static PersistentCookieStore cookieStore;
//
//    /**
//     * Mandatory constructor for the NovateCookieManger
//     */
//    public NovateCookieManger(Context context) {
//        mContext = context;
//        if (cookieStore == null) {
//            cookieStore = new PersistentCookieStore(mContext);
//        }
//    }
//
//    @Override
//    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//        if (cookies != null && cookies.size() > 0) {
//            for (Cookie item : cookies) {
//                cookieStore.add(url, item);
//            }
//        }
//    }
//
//    @Override
//    public List<Cookie> loadForRequest(HttpUrl url) {
//        List<Cookie> cookies = cookieStore.get(url);
//        return cookies;
//    }
}


