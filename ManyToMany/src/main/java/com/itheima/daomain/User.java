package com.itheima.daomain;

import java.util.Date;
import java.util.List;

public class User {
    private Integer id;
    private String username;
    private String address;
    private String sex;
    private Date birthday;
    private List<Account> Account;

    public List<com.itheima.daomain.Account> getAccount() {
        return Account;
    }

    public void setAccount(List<com.itheima.daomain.Account> account) {
        Account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
