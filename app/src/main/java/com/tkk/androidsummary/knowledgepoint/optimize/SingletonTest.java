package com.tkk.androidsummary.knowledgepoint.optimize;

import android.content.Context;

/**
 * Created  on 2017/11/27
 *
 * @author 唐开阔
 * @describe
 */

public class SingletonTest {
    private static  SingletonTest ourInstance;
    private Context context;
    public static SingletonTest getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = new SingletonTest(context);
        }
        return ourInstance;
    }

    private SingletonTest(Context context) {
        this.context = context;
    }


}
