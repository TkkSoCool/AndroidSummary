package com.tkk.androidsummary.knowledgepoint.frame.dagger2;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created  on 2018-05-28
 *
 * @author 唐开阔
 * @describe
 */
@Module
public class ActivityModule {
    private int timeOut;

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    @Provides
    OkHttpClient getOkHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.build();
    }
}
