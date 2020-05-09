package com.itcast.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        DateFormat format=null;
        if(source==null){
            System.out.println("请输入日期...");
        }
        try {
            format=new SimpleDateFormat("yyyy-MM-dd");
            Date date=format.parse(source);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
