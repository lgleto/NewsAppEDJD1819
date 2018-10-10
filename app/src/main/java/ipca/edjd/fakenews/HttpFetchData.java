package ipca.edjd.fakenews;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class HttpFetchData extends AsyncTask<String,Void,String>{

    public interface HttpListener {
        public void onHttpResponseEvent(String res);
    }

    List<HttpListener> listeners = new ArrayList();

    public void onHttpResponseEvent(HttpListener listener){
        listeners.add(listener);
    }

    private void fireSuccessResponseEvent(String res){
        for (HttpListener listener:listeners){
            listener.onHttpResponseEvent(res);
        }
    }

    String urlString;

    public HttpFetchData(String url){
        this.urlString=url;
        execute(null,null,null);
    }


    @Override
    protected String doInBackground(String... strings) {
        String result="";
        HttpURLConnection urlConnection;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpsURLConnection)url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");

            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine())!=null) {
                stringBuilder.append(line).append('\n');
            }

            result = stringBuilder.toString();

            //Log.d("fakenews", result);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        fireSuccessResponseEvent(s);
    }
}
