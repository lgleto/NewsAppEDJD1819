package ipca.edjd.fakenews;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

import ipca.edjd.fakenews.models.News;

public class MainActivity extends AppCompatActivity {

    static String urlString = "https://newsapi.org/v2/top-headlines?country=pt&apiKey=1765f87e4ebc40229e80fd0f75b6416c";

    ListView listView;

    List<News> newsList=new ArrayList<>(); // model
    NewListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewNews);
        adapter = new NewListAdapter();
        listView.setAdapter(adapter);


        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result;
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

                    Log.d("fakenews", result);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                return null;
            }
        }.execute(null,null,null);



    }

    class NewListAdapter extends BaseAdapter implements View.OnClickListener{

        LayoutInflater layoutInflater;

        public NewListAdapter(){
            layoutInflater=(LayoutInflater) getApplicationContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return newsList.size();
        }

        @Override
        public Object getItem(int i) {
            return newsList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null)
                view = layoutInflater.inflate(R.layout.row_news, null);

            TextView textViewTitle = view.findViewById(R.id.textViewTitle);
            TextView textViewDescription = view.findViewById(R.id.textViewSubTitle);
            ImageView imageView= view.findViewById(R.id.imageViewNews);

            textViewTitle.setText(newsList.get(i).getTitle());
            textViewDescription.setText(newsList.get(i).getDescription());

            view.setTag(new Integer(i));
            view.setClickable(true);
            view.setOnClickListener(this);

            return view;
        }

        @Override
        public void onClick(View view) {

            int position = (int)view.getTag();
            Log.d("fakenews", "Position:"+position);

            Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);
            intent.putExtra("news_title",newsList.get(position).getTitle());
            startActivity(intent);

        }
    }
}
