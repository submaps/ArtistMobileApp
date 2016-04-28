package ru.sproclub.firstapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import ru.sproclub.firstapp.artist.Artist;
import ru.sproclub.firstapp.artist.BigListAdapter;
import ru.sproclub.firstapp.artist.CustomListAdapter;


public class ArtistActivity extends ActionBarActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        int position=getIntent().getIntExtra("curArtistPosition", 0);
        Artist[] list=new Artist[1];
        list[0]=ArtistDao.list.get(position);
        this.setTitle(list[0].name);
        BigListAdapter adapter=new BigListAdapter(this,list);
        listView=(ListView)findViewById(R.id.listView);
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
}
