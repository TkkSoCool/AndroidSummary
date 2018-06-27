package com.tkk.androidsummary.knowledgepoint.frame.Retrofit;

import java.io.Serializable;

/**
 * Created by lily on 2018/1/17 0017.
 * Function :
 */

public class BaseBean<T> implements Serializable{
    public String status;//y,n
    public String errMsg;
    public String errorCode;
    public String token;
    public int teacherType=0;
    public T data;//jsonobject,jsonarray

    @Override
    public String toString() {
        return "BaseEntity{" +
                "status='" + status + '\'' +
                ", error='" + errMsg + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", token='" + token + '\'' +
                ", data=" + data +
                '}';
    }
}
