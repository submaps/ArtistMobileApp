package ru.artistmobile;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import artist.Artist;
import adapters.CustomListAdapter;
import artist.ArtistDataProcessor;
import listeners.MyKeyListener;


public class MainActivity extends ActionBarActivity {

    public ListView listView;
    public EditText editText;
    public CustomListAdapter adapter;

    boolean isNetworkOnlineNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArtistDao.curActivity=this;
        editText = (EditText)findViewById(R.id.editText);

        MyKeyListener myKeyListener=new MyKeyListener(this);
        editText.setOnKeyListener(myKeyListener);

        listView=(ListView)findViewById(R.id.listView);
        isNetworkOnlineNow = isNetworkOnline(this);
        isNetworkOnlineNow=true; //TODO проверку на доступ к интернету

        ArtistDao.isNetworkOnline=isNetworkOnlineNow;
        ArtistDataProcessor artistDataProcessor = new ArtistDataProcessor();//загрузчик данных
        InputStream tmp_list_stream = getResources().openRawResource(R.raw.tmp_list);
        //источник данных по умолчанию

        if(isNetworkOnlineNow) {
            tmp_list_stream = getResources().openRawResource(R.raw.artists);
        }

        Artist[] list = artistDataProcessor.parseJsonFileToObjects(tmp_list_stream);
        //для доступа из ArtistActivity
        ArtistDao.listStore = new ArrayList<Artist>(Arrays.asList(list));
        ArtistDao.list = new ArrayList<Artist>(Arrays.asList(list));
        displayArtistListView(ArtistDao.list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public boolean isNetworkOnline(Context context) {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;
    }

    @Override
    public android.support.v4.app.FragmentManager getSupportFragmentManager() {
        return null;
    }


    public void displayArtistListView(ArrayList<Artist> list){
        ImageLoader imageLoader = ImageLoader.getInstance(); // Получили экземпляр
        imageLoader.init(ImageLoaderConfiguration.createDefault(this)); // Проинициализировали конфигом по умолчанию
        File cacheDir = StorageUtils.getCacheDirectory(this);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480, 800) // width, height
                .threadPoolSize(5)
                .threadPriority(Thread.MIN_PRIORITY + 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // 2 Mb
                .discCache(new UnlimitedDiskCache(cacheDir))
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
        adapter = new CustomListAdapter(this, list, isNetworkOnlineNow);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(MainActivity.this, ArtistActivity.class);
                intent.putExtra("curArtistPosition", position);
                startActivity(intent);
            }
        });
    }


    public void updateArtistListView(){
        adapter.notifyDataSetChanged();
    }

    public void showShortToast(String message){
        Toast toast = Toast.makeText(getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
