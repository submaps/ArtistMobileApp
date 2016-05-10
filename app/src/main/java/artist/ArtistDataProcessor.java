package artist;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import artist.Artist;

public class ArtistDataProcessor {
    Artist[] list;
    String _url = "";
    File cache_dir = new File("C:\\MobileApp\\tmp");

    public ArtistDataProcessor() {
        //_url=url;
        //cache_dir = getExternalCacheDir();
    }


    protected Artist[] doInBackground() {
        try {
            //скачивание данных
            //HttpClient httpclient = new DefaultHttpClient();
            //HttpPost httppost = new HttpPost(_url);
            //HttpResponse response = httpclient.execute(httppost);
            //HttpEntity httpEntity=response.getEntity();
            //InputStream stream = AndroidHttpClient.getUngzippedContent(httpEntity);

            //открываем временный файл для записи
            BufferedReader bufferedReader = new BufferedReader(new FileReader("artists.json"));
            File file = new File(cache_dir, "temp_json_new.json");
            //File file=new File("");
            if (file.exists()) { //если таковой уже есть - удаляем и создаём новый
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

            char[] buff = new char[1024 * 1024];
            int read;
            long FullSize = 0;
            while ((read = bufferedReader.read(buff)) != -1) {
                bufferedWriter.write(buff, 0, read);    //запись в файл
                FullSize += read;
                System.out.println("скачано " + (FullSize));
            }
            bufferedWriter.flush();
            fileOutputStream.close();

            //парсинг из файла
            System.out.println("начали парсинг...");
            //list=parseJsonFileToObjects(file);
            System.out.println("закончили парсинг.");
            System.out.println("_______________________");
            System.out.println("ВСЕГО СКАЧАНО " + list.length);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ошибка " + e.getMessage());
        }

        return list;
    }

    void writeFromObjectsInJsonTmpFile(ArrayList<Artist> tmp_list){
        //из объектов в json
        Gson gson =new Gson();
        String tmp_list_text=gson.toJson(tmp_list);
        File tmp_list_file=new File("tmp/tmp_list.json");
        //Определяем файл
        try {
            if(!tmp_list_file.exists()){
                tmp_list_file.createNewFile();
            }
            PrintWriter out = new PrintWriter(tmp_list_file.getAbsoluteFile());
            try {
                out.print(tmp_list_text);
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Artist[] parseJsonFileToObjects(InputStream fileInputStream) {
        //FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(fileInputStream);
        Gson gson = new Gson();
        list = gson.fromJson(reader, Artist[].class);
        return list;
    }


    static void saveImgH(long id, URL url){
        String strPath="tmp/img/"+id+".jpg";
        int buffSize=512;
        try {
            HttpURLConnection urlconn;
            urlconn = (HttpURLConnection) url.openConnection();
            urlconn.setRequestMethod("GET");
            urlconn.connect();
            InputStream in = null;
            in = urlconn.getInputStream();

            OutputStream writer = new FileOutputStream(strPath);
            byte buffer[] = new byte[buffSize];
            int c = in.read(buffer);
            while (c > 0) {
                writer.write(buffer, 0, c);
                c = in.read(buffer);
            }
            writer.flush();
            writer.close();
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}