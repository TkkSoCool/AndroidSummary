package com.tkk.androidsummary.knowledgepoint.frame.EventBus;

/**
 * Created  on 2017/12/29
 *
 * @author 唐开阔
 * @describe
 */

public class MessageEvent {

    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
