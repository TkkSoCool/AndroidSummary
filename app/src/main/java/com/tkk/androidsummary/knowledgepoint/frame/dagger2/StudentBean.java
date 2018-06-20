package com.tkk.androidsummary.knowledgepoint.frame.dagger2;

import javax.inject.Inject;

/**
 * Created  on 2018-05-28
 *
 * @author 唐开阔
 * @describe
 */
public class StudentBean {
    private int no;
    private String name;

    @Inject
    public StudentBean() {
        this.no = 1;
        this.name = "赵四";
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
