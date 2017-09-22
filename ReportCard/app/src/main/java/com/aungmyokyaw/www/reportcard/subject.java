package com.aungmyokyaw.www.reportcard;

/**
 * Created by aungmyokyaw on 22/9/17.
 */

public class subject {
    private String Name;
    private int Mark;

    public subject(String name,int mark){
        Name = name;
        Mark = mark;
    }

    public String  getName(){
        return Name;
    }

    public int getMark(){
        return Mark;
    }
}
