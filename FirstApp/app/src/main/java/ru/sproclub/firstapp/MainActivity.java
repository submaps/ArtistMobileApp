package ru.sproclub.firstapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import ru.sproclub.firstapp.artist.Artist;
import ru.sproclub.firstapp.artist.CustomListAdapter;
import ru.sproclub.firstapp.artist.LoadBigDataTmpFile;


public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private EditText editText;
    boolean isNetworkOnlineNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);

        editText.setOnKeyListener(new View.OnKeyListener()
                                  {
                                      public boolean onKey(View v, int keyCode, KeyEvent event)
                                      {
                                          if(event.getAction() == KeyEvent.ACTION_DOWN &&
                                                  (keyCode == KeyEvent.KEYCODE_ENTER))
                                          {
                                              // сохраняем текст, введенный до нажатия Enter в переменную
                                              String strSearchName = editText.getText().toString();
                                              return true;
                                          }
                                          return false;
                                      }
                                  }
        );

        listView=(ListView)findViewById(R.id.listView);

        isNetworkOnlineNow = isNetworkOnline(this);
        Toast toast = Toast.makeText(getApplicationContext(),
                "Интернет статус: "+isNetworkOnlineNow, Toast.LENGTH_SHORT);
        toast.show();

        LoadBigDataTmpFile loadBigDataTmpFile = new LoadBigDataTmpFile();//загрузчик данных
        InputStream tmp_list_stream = getResources().openRawResource(R.raw.tmp_list);
        //источник данных по умолчанию
        if(isNetworkOnlineNow) {
            tmp_list_stream = getResources().openRawResource(R.raw.artists);
        }

        Artist[] list = loadBigDataTmpFile.parseJsonFileToObjects(tmp_list_stream);
        ArtistDao.list = new ArrayList<Artist>(Arrays.asList(list));//для доступа из ArtistActivity
        //CustomListAdapter adapter = new CustomListAdapter(this, list, isNetworkOnlineNow);
        CustomListAdapter adapter = new CustomListAdapter(this, ArtistDao.list, isNetworkOnlineNow);

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
}
