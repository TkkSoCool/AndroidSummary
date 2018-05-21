package com.tkk.androidsummary.knowledgepoint.frame;


import java.io.Serializable;

/**
 * Description:
 *
 * @author YuShuangJiang
 * @version 1.0
 * @created 18/1/12
 */

public class CommonResult<T> implements Serializable {

    private int status;
    private String message;
    private T body;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }


}
