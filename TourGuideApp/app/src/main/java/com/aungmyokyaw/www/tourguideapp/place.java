package com.aungmyokyaw.www.tourguideapp;

/**
 * Created by aungmyokyaw on 23/9/17.
 */

public class place {
    private int ImgRes;
    private String Name,Description,Location;

    public place(String name,String des,int imgRes,String location){
        ImgRes = imgRes;
        Name = name;
        Description = des;
        Location = location;
    }

    public int getImgRes(){
        return ImgRes;
    }

    public String getName(){
        return Name;
    }

    public String getDescription(){
        return Description;
    }

    public String getLocation(){
        return Location;
    }
}
