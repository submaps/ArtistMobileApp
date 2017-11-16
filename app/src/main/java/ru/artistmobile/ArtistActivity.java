package ru.artistmobile;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;

import artist.Artist;
import adapters.BigListAdapter;

import static ru.artistmobile.ArtistDao.showShortToast;
import static ru.artistmobile.R.id.play_button;
import static ru.artistmobile.ArtistDao.mp;

public class ArtistActivity extends ActionBarActivity {
    private ListView listView;
    public Artist curArtist;
    private boolean playStatus = false;
    private boolean playStatusGlobal = false;

    //    Button onButton = (Button) this.findViewById(R.id.play_button);
    private final HashMap<Integer, String> songs = new HashMap<>();

    {
        songs.put(100500, "jaysean_rideit");
        songs.put(74614, "kellyrowland_layitonmefeat_bigsean");
        songs.put(1150, "kerihilson_turningmeonftlilwayne");
        songs.put(2915, "ne_yo_sosick");
        songs.put(91546, "usher_niceandslow");
        songs.put(160970, "noize_mars");
        songs.put(161010, "nyusha_");
        songs.put(10001703, "oxxxymiron_vsegolishpisatel");
        songs.put(1156, "timbaland_apologize");
        songs.put(1080505, "tovelo_screammyname");
        songs.put(166300, "bjanka_absolutno");
        songs.put(41110, "dimbalan_jgan");
        songs.put(451523, "pizza_oruje");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        int position = getIntent().getIntExtra("curArtistPosition", 0);
        Artist[] list = new Artist[1];
        list[0] = ArtistDao.list.get(position);
        curArtist = ArtistDao.list.get(position);
        this.setTitle(list[0].name);
        BigListAdapter adapter = new BigListAdapter(this, list);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.artist_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onMyButtonClick(View view) {
        String str;
        URL curUrl = curArtist.link;
        if (curUrl == null) {
            str = "https://yandex.ru/search/?text=" + curArtist.name;
        } else {
            str = curArtist.link.toString();
        }
        Uri address = Uri.parse(str);
        Intent openlink = new Intent(Intent.ACTION_VIEW, address);
        startActivity(openlink);
    }


    public void onPlayClick(View view) {
        if (!playStatus) {
            try {
                mp.reset();
//                String uri = "http://soundcloud.com/storynory/the-valentine-witch-mp3/download.mp3";
//                mp.setDataSource(this, Uri.parse(uri));
//                mp.prepare();
//                mp.start();
                String mp3NameStr = songs.get(curArtist.id);
                int mp3Name = getResources().getIdentifier(mp3NameStr,
                        "raw", getPackageName());
                showShortToast("started!!!");

                mp = MediaPlayer.create(this, mp3Name);
                mp.setLooping(true);
                mp.start();
//                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    public void onCompletion(MediaPlayer mp) {
////                        finish(); // finish current activity
//                        mp.start();
//                    }
//                });

                playStatus = true;
//                onButton.setText("Stop");
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        } else {
            mp.stop();
            playStatus = false;
            showShortToast("stopped!!!");
        }
//        } else {
//            if (playStatus) {
//                showShortToast("stopped!!!");
//                mp.stop();
////            onButton.setText("Start");
//                playStatus = false;
//            }
//        }


//        String str;
//        URL curUrl=curArtist.link;
//        if (curUrl==null){
//            str="https://yandex.ru/search/?text="+curArtist.name;
//        }else{
//            str=curArtist.link.toString();
//        }
//        Uri address = Uri.parse(str);
//        Intent openlink = new Intent(Intent.ACTION_VIEW, address);
//        startActivity(openlink);
    }
}
