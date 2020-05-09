package com.itheima.serviceImpl;

import com.itheima.service.Iservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class serviceImpl implements Iservice {
    private List<String> name;
    private ArrayList<String> age;
    private Map<String,String> sex;

    public void setName(List name) {
        this.name = name;
    }

    public void setAge(ArrayList<String> age) {
        this.age = age;
    }

    public void setSex(Map sex) {
        this.sex = sex;
    }

    public void UserSave() {
        System.out.println("Service Run..."+"-----"+name+"----"+age+"----"+sex+"");
    }


}
