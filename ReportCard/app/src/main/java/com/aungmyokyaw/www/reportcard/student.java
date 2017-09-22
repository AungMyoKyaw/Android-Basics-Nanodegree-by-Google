package com.aungmyokyaw.www.reportcard;

/**
 * Created by aungmyokyaw on 22/9/17.
 */

public class student {

    private String Name;
    private int ProfilePic,Burmese,English,Maths,Physics,Chemistry,Bio;

    public student(String name,int pic,int burmese,int english,int maths,int physics,int chemistry,int bio){
        Name = name;
        ProfilePic = pic;
        Burmese = burmese;
        English = english;
        Maths = maths;
        Physics = physics;
        Chemistry = chemistry;
        Bio = bio;
        ProfilePic = pic;
    }

    public String getName(){
        return Name;
    }

    public int getProfilePic(){
        return ProfilePic;
    }

    public int getBurmese(){
        return Burmese;
    }

    public int getEnglish(){
        return English;
    }

    public int getMaths(){
        return Maths;
    }

    public int getPhysics(){
        return Physics;
    }

    public int getChemistry(){
        return Chemistry;
    }

    public int getBio(){
        return Bio;
    }
}
