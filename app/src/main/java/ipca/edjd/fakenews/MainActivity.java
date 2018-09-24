package ipca.edjd.fakenews;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ipca.edjd.fakenews.models.News;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    List<News> newsList=new ArrayList<>();
    NewListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        News n1 = new News("Lourenço",
                "Nova notícia",
                "sdzfsdfsa",
                "url",
                new Date(),
                "Nlkfsfklshd ",
                "scds",
                1);

        News n2 = new News("Lourenço",
                "Nova notícia 2",
                "vsdfvlksdnv slkdjvlsdkfjklsd",
                "url",
                new Date(),
                "slkedfjselkfsdsdlf  dslfsld ",
                "scds",
                1);

        News n3 = new News("Lourenço",
                "Nova notícia 3",
                "lsedkfsdklfhskdfhkdshf",
                "url",
                new Date(),
                "slkedfjselkfsdsdlf  dslfsld ",
                "scds",
                1);



        newsList.add(n1);
        newsList.add(n2);
        newsList.add(n3);
        newsList.add(n3);
        newsList.add(n3);newsList.add(n3);newsList.add(n3);newsList.add(n3);newsList.add(n3);
        newsList.add(n3);newsList.add(n3);







        listView = findViewById(R.id.listViewNews);
        adapter = new NewListAdapter();
        listView.setAdapter(adapter);




    }

    class NewListAdapter extends BaseAdapter {

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

            return view;
        }
    }
}
