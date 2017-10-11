package com.aungmyokyaw.www.booklistingapp;

/**
 * Created by aungmyokyaw on 9/10/17.
 */

public class book {
    private String img,title,author,description;

    public book(String new_img,String new_title,String new_author,String new_description){
        img = new_img;
        title = new_title;
        author = new_author;
        description = new_description;
    }

    public String getImg(){
        return img;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getDescription(){
        return description;
    }
}
