package ru.sproclub.firstapp.artist;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class Artist {
    public int id;
    public String name;
    String[] genres;
    int tracks;
    int albums;
    public URL link;
    String description;
    Cover cover;

    @Override
    public String toString() {
        String str;
        if (link == null) {
            try {
                link =new URL("https://yandex.ru/search/?text=" + name.replace(" ", "_"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        String str_genres = getGenresAsString();
        str = name + "\n" + cover.big + "\n" + link + "\n" + str_genres + "\nБиография" + description;
        return str;
    }

    public String getGenresAsString() {
        String str_genres = "";
        for (int i = 0; i < genres.length; i++) {
            if (i != genres.length - 1) {
                str_genres += (genres[i] + ", ");
            } else {
                str_genres += (genres[i]);
            }
        }
        return str_genres;
    }

    public String getTracksAndAlbumsAsString() {
        String ans = "";
        String ans_albums = albums + " " + pluralForm(albums, "альбом", "альбома", "альбомов");
        String ans_tracks = tracks + " " + pluralForm(tracks, "песня", "песни", "песен");
        ans += ans_albums + ", " + ans_tracks;
        return ans;
    }

    //"альбом", "альбома", "альбомов"
    public String pluralForm(int n, String form1, String form2, String form5) {
        n = Math.abs(n) % 100;
        int n1 = n % 10;
        if (n > 10 && n < 20) return form5;
        if (n1 > 1 && n1 < 5) return form2;
        if (n1 == 1) return form1;
        return form5;
    }

    public String getModifiedDescription() {
        String ans = Character.toUpperCase(description.charAt(0)) + description.substring(1);
        return ans;
    }

    public String getModifiedDescriptionAsHtml() {
        String prefix="<html><body style=\"text-align:justify;\">";
        String postfix=" </body></html>";
        String ans = prefix+Character.toUpperCase(description.charAt(0)) + description.substring(1)+postfix;
        return ans;
    }
}
