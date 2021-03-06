package ru.artistmobile;

import android.app.Activity;
import android.app.Application;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.util.ArrayList;

import artist.Artist;

/**
 * Created by А on 25.04.2016.
 */
public class ArtistDao {
public static ArrayList<Artist> listStore;//общий массив
public static  ArrayList<Artist> list;//текущий отображаемый массив
public static ArrayList<Artist> newlist=new ArrayList<>();
public static boolean isNetworkOnline;
public static Activity curActivity;
public static MediaPlayer mp = new MediaPlayer();

    public static ArrayList<Artist> searchArtist(String str){
        str=str.toLowerCase().trim();
        String[] strArr=str.split("\\s+|,\\s*|\\.\\s*");
        //разбиение строки на массив строка для поиска по словам
        String name = "";
        String genr = "";
        newlist.clear();
        for (Artist artist : listStore) {
            name = artist.name.toLowerCase();
            genr = artist.getGenresAsString().toLowerCase();
            boolean condPart=false;
                for(String strPart:strArr){
                    condPart|=name.matches(".*"+strPart+".*");
                    condPart|=genr.matches(".*"+strPart+".*");
                }
            if (condPart) {
                newlist.add(artist);
            }
        }
        String searchMsg=curActivity.getResources().getString(R.string.search_found);
        String strFinal = String.format(searchMsg, newlist.size(),listStore.size());
        showShortToast(strFinal);
//
//  showShortToast("Найдено: "+newlist.size()+" записей из "+listStore.size());
        return newlist;
    }

    public static void showShortToast(String message){
        Toast toast = Toast.makeText(curActivity,
                message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
