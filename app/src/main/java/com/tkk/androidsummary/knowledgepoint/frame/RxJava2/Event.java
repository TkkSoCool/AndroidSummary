package com.tkk.androidsummary.knowledgepoint.frame.RxJava2;

/**
 * Created  on 2018/1/23
 *
 * @author 唐开阔
 * @describe 事件实体
 */

public class Event {
    public  String tag;
    private final Object data;
    private final Boolean sticky;

    public Event(String tag, Object data, boolean sticky) {
        this.tag = tag;
        this.data = data;
        this.sticky = sticky;
    }

    public Event(String tag, Object data) {
        this(tag, data, false);
    }

    public Object getData() {
        return data;
    }

    public String getTag() {
        return tag;
    }

    public Boolean isSticky() {
        return sticky;
    }
}
