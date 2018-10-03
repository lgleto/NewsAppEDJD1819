package ipca.edjd.fakenews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Bundle bundle = getIntent().getExtras();
        String title = (String) bundle.get("news_title"); // model
        setTitle(title);
    }
}
