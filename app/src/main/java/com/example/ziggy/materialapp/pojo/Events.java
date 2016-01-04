package com.example.ziggy.materialapp.pojo;

/**
 * Created by Ziggy on 2015-12-30.
 */
public class Events {
    private int id;
    private int  comm_id;
    private String detail;
    private String date;

    public Events(){

    }

    public Events(int id,int comm_id,String detail, String date){
        this.id = id;
        this.comm_id = comm_id;
        this.detail = detail;
        this.date = date;
    }

    public int getId(){
        return id;
    }

    public String getDetail(){
        return detail;
    }

    public String getDate(){
        return date;
    }

    @Override
    public String toString() {
        return "ID: "+ id;
    }
}

