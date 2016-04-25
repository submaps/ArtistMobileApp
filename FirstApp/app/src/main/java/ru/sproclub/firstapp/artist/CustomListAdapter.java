package ru.sproclub.firstapp.artist;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ru.sproclub.firstapp.R;

public class CustomListAdapter extends ArrayAdapter<Artist> {
    private final Artist[] artists_list;
    private final Activity context;

    public CustomListAdapter(Activity context, Artist[] artists_list) {
        super(context, R.layout.my_list_item, artists_list);
        this.context=context;
        this.artists_list=artists_list;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.my_list_item, null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt1 = (TextView) rowView.findViewById(R.id.textView1);
        TextView extratxt2 = (TextView) rowView.findViewById(R.id.textView2);
        Artist curArtist=artists_list[position];
        txtTitle.setText(curArtist.name);
        int curImg = context.getResources().getIdentifier("id"+curArtist.id , "drawable", context.getPackageName());
        imageView.setImageResource(curImg);
        extratxt1.setText(curArtist.getGenresAsString());
        extratxt2.setText(curArtist.getTracksAndAlbumsAsString());
        return rowView;

    };
}