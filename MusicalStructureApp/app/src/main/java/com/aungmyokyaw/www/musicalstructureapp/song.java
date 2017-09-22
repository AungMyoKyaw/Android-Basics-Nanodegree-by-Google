package com.aungmyokyaw.www.musicalstructureapp;

/**
 * Created by aungmyokyaw on 21/9/17.
 */

public class song {
    private String Name,Artist,Albums,Genre;
    private int ResSong,ResArt;

    public song(String name,String artist,String albums,String genre,int resSong,int resArt){
        Name = name;
        Artist = artist;
        Albums = albums;
        Genre = genre;
        ResSong = resSong;
        ResArt = resArt;
    }

    public String getName(){
        return Name;
    }

    public String getArtist(){
        return Artist;
    }

    public String getAlbums(){
        return Albums;
    }

    public String getGenre(){
        return Genre;
    }

    public int getResSong(){
        return ResSong;
    }

    public int getResArt(){
        return ResArt;
    }
}
