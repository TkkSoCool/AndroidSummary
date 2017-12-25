package com.tkk.androidsummary.knowledgepoint.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created  on 2017/11/8
 * @author 唐开阔
 * @describe
 */

public class Person implements Parcelable {
    private String name;
    private int age;
    private int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public static Creator<Person> getCREATOR() {
        return CREATOR;
    }

    public Person(Parcel source) {
        this.name=source.readString();
        this.age=source.readInt();
        this.number=source.readInt();
    }

    //getter、setter method
    //...
    public Person(int age, String name, int number) {
        this.age = age;
        this.name = name;
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeInt(number);
    }
    public static final Creator<Person> CREATOR=new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", number=" + number +
                '}';
    }
}
