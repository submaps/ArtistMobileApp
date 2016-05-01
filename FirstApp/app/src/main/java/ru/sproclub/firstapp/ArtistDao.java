package ru.sproclub.firstapp;

import android.app.Activity;
import android.app.Application;
import android.widget.Toast;

import java.util.ArrayList;

import ru.sproclub.firstapp.artist.Artist;

/**
 * Created by А on 25.04.2016.
 */
public class ArtistDao {
public static ArrayList<Artist> list;
public static boolean isNetworkOnline;

    public static void searchStr(String str){
        ArrayList<Artist> newlist=new ArrayList();
        for(Artist artist:list){
            if (artist.name.equals(str.trim())||artist.getGenresAsString().contains(str.trim())){
                newlist.add(artist);
            }
        }
        list=newlist;
    }


}
