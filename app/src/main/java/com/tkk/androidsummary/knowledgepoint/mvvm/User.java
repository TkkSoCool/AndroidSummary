package com.tkk.androidsummary.knowledgepoint.mvvm;

/**
 * Created  on 2017/11/29
 *
 * @author 唐开阔
 * @describe MVVM模式的实体对象
 */

public class User {
    private  String firstName;
    private  String lastName;
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }

}
