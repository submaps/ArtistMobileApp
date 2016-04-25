package ru.sproclub.firstapp;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

public class LoadBigData extends AsyncTask<Void, Void, Void> {
    private TextView _textView1;
    String _url="download.cdn.yandex.net/mobilization-2016/artists.json";

    public LoadBigData(String url, TextView textView){
        _url=url;
        _textView1=textView;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        //открывам потом на чтение данных
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        //и сразу направляем его в десериализатор
//            InputStreamReader reader = new InputStreamReader(stream);
//        Gson gson=new Gson();
//            ru.sproclub.firstapp.artist.Artist[] list=gson.fromJson(reader, ru.sproclub.firstapp.artist.Artist[].class);

//        ru.sproclub.firstapp.artist.Artist[] list=gson.fromJson(str, ru.sproclub.firstapp.artist.Artist[].class);
//
//        for (ru.sproclub.firstapp.artist.Artist a:list){
//            _textView1.setText(a.name);
//        }
        //тестовый вывод
//            for (HumorItem message:list.Items){
//                Log.d("Текст: ", message.text);
//                Log.d("Ссылка: ",message.url);
//                Log.d("----------","---------");
//            }
//            Log.d("ВСЕГО СКАЧАНО ", String.valueOf(list.Items.size()));

        return null;
    }

    protected static void getImg(String url){
        //скачивание данных
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity httpEntity=response.getEntity();
        try {
            InputStream stream = AndroidHttpClient.getUngzippedContent(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}