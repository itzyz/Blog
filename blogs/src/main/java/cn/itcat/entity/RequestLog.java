package cn.itcat.entity;

import java.util.Arrays;

public class RequestLog {
    private String url;
    private String ip;
    private String classMethod;
    private Object[] ages;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public Object[] getAges() {
        return ages;
    }

    public void setAges(Object[] ages) {
        this.ages = ages;
    }

    @Override
    public String toString() {
        return "RequestLog{" +
                "url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", ages=" + Arrays.toString(ages) +
                '}';
    }

    public RequestLog(String url, String ip, String classMethod, Object[] ages) {
        this.url = url;
        this.ip = ip;
        this.classMethod = classMethod;
        this.ages = ages;
    }
}
