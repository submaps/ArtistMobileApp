package ru.sproclub.firstapp.artist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ru.sproclub.firstapp.R;

public class ArtistArrayAdapter extends ArrayAdapter<Artist> {
   // private final Activity context;
//    private final String[] itemname;
//    private final Integer[] imgid;
//
    public ArtistArrayAdapter(Context context, Artist[] list) {
        //super(context, android.R.layout.simple_list_item_2, list);

        super(context, R.layout.my_list_item, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Artist artist = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    //.inflate(android.R.layout.simple_list_item_2, null);
                    .inflate(R.layout.my_list_item, null);
        }
        ((TextView) convertView.findViewById(android.R.id.text1))
                .setText(artist.name);
        ((TextView) convertView.findViewById(android.R.id.text2))
                .setText(artist.getGenresAsString()+"\n"+artist.getTracksAndAlbumsAsString());
//        ((TextView) convertView.findViewById(android.R.id.text2))
//                .setText(artist.getTracksAndAlbumsAsString());

//        LayoutInflater inflater=context.getLayoutInflater();
//        View rowView=inflater.inflate(R.layout.my_list_item, null,true);
//
//        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
//        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
//        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
//
//        txtTitle.setText(itemname[position]);
//        imageView.setImageResource(imgid[position]);
//        extratxt.setText("Description "+itemname[position]);
//        return rowView;



        return convertView;
    }
}