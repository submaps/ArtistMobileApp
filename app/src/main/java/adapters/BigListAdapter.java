package adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ru.artistmobile.ArtistActivity;
import ru.artistmobile.ArtistDao;
import ru.artistmobile.R;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import ru.artistmobile.R;
import artist.Artist;

public class BigListAdapter extends ArrayAdapter<Artist> {
    private final Artist[] artists_list;
    private final Activity context;
    private boolean isNetworkOnlineNow;

    public BigListAdapter(Activity context, Artist[] artists_list) {
        super(context, R.layout.my_list_item_big, artists_list);
        this.context=context;
        this.artists_list=artists_list;
        this.isNetworkOnlineNow= ArtistDao.isNetworkOnline;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.my_list_item_big, null,true);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        TextView txt1 = (TextView) rowView.findViewById(R.id.textView1);
        TextView txt2 = (TextView) rowView.findViewById(R.id.textView2);
        TextView txt3 = (TextView) rowView.findViewById(R.id.textView3);
        TextView txt4 = (TextView) rowView.findViewById(R.id.textView4);
        //WebView web4=(WebView) rowView.findViewById(R.id.web4);
        Artist curArtist=artists_list[position];


        if (isNetworkOnlineNow){
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.stub_image)
                    .showImageForEmptyUri(R.drawable.image_for_empty_url)
                    .resetViewBeforeLoading()
                    .cacheInMemory()
                    .cacheOnDisc()
//                    .decodingType(ImageScaleType.EXACTLY)
                    .build();
            ImageLoader.getInstance().displayImage(curArtist.cover.big, imageView,options);
        }else{
            int curImg = context.getResources().getIdentifier("id"+curArtist.id , "drawable", context.getPackageName());
            imageView.setImageResource(curImg);
            //если подключения к сети нет, загружаются закэшированные элементы
        }
        txt1.setText(curArtist.getGenresAsString());
        txt2.setText(curArtist.getTracksAndAlbumsAsString(context));
        txt3.setText(R.string.artist_biography_title);
        //web4.loadData(curArtist.getModifiedDescriptionAsHtml(), "text/html; charset=utf-8", "utf-8");
        txt4.setText(curArtist.getModifiedDescription());
        return rowView;
    };
}