package com.itcast.doman;

public class Account {
    private String sex;
    private String address;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Account{" +
                "sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
