package com.example.ziggy.materialapp.pojo;

import java.util.Date;

/**
 * Created by Ziggy on 2015-12-28.
 */
public class News {
    private int id;
    private String headline;
    private String detail;
    private String date;

    public News(){

    }

    public News(int id,String headline,String detail, String date){
        this.id = id;
        this.headline = headline;
        this.detail = detail;
        this.date = date;
    }

    public int getId(){
        return id;
    }

    public String getHeadline(){
        return headline;
    }

    public String getDetail(){
        return detail;
    }

    public String getDate(){
        return date;
    }

    @Override
    public String toString() {
        return "ID: "+ id+
                "Headline: "+ headline;
    }
}


