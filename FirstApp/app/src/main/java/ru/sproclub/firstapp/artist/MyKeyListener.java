package ru.sproclub.firstapp.artist;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;

import ru.sproclub.firstapp.ArtistDao;
import ru.sproclub.firstapp.MainActivity;
import ru.sproclub.firstapp.R;

public class MyKeyListener implements View.OnKeyListener {
    MainActivity curActivity;

    public MyKeyListener(MainActivity curActivity){
        this.curActivity=curActivity;
    }
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN &&
                (keyCode == KeyEvent.KEYCODE_ENTER)){
            // сохраняем текст, введенный до нажатия Enter в переменную

            String strSearch = curActivity.editText.getText().toString().trim();
            //при вводе пустой строки отображается всё
            if (strSearch.equals("")){
                curActivity.adapter.getData().clear();
                curActivity.adapter.getData().addAll(ArtistDao.listStore);
            }else {
                String searchMsg=curActivity.getResources().getString(R.string.search_process);
                String strFinal = String.format(searchMsg, strSearch);
                ArtistDao.showShortToast(strFinal);
                ArtistDao.searchArtist(strSearch);
                curActivity.adapter.getData().clear();
                curActivity.adapter.getData().addAll(ArtistDao.newlist);
            }
            curActivity.adapter.notifyDataSetChanged();
            curActivity.listView.invalidateViews();
            return true;
        }
        return false;
    }
}
