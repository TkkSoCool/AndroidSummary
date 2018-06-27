package com.tkk.androidsummary.knowledgepoint.frame.Retrofit;

import com.tkk.androidsummary.knowledgepoint.frame.CommonResult;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import io.reactivex.Observable;

/**
 * Created  on 2018-05-16
 *
 * @author 唐开阔
 * @describe
 */
public interface UserService {

    /**
     * 获取申请人对象
     * @return
     */
    @GET("sssnAPP/AddressList/selectXiaoJi.do")
    Observable<BaseBean<List<SqrBean>>> getSqr();
    @FormUrlEncoded
    @POST("api/sys/user/login")
    Observable<CommonResult<UserInfo>> userLogin(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST("api/work/currentwork")
    Observable<CommonResult<WorkInfo>> getCurrentWork(@Header("token") String token, @FieldMap Map<String, String> params);
}
