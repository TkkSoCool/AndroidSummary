package com.tkk.androidsummary.knowledgepoint.frame.Retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created  on 2018\3\14 0014
 *
 * @author 唐开阔
 * @describe
 */

public interface BlogService {
    @GET("blog/{id}") //这里的{id} 表示是一个变量
    Call<ResponseBody> getBlog(/** 这里的id表示的是上面的{id} */@Path("id") int id);
}
