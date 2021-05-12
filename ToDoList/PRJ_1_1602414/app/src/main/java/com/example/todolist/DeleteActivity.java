package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import static com.example.todolist.postlar.deletingObjects;

public class DeleteActivity extends AppCompatActivity {


    EditText title4;
    JSONArray dS = new JSONArray();
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        title4 = findViewById(R.id.title2);
        System.out.println("title için " + deletingObjects.isEmpty() + " size: " + deletingObjects.size() + " title: " + deletingObjects.get(0).getTitle());
    }

    public void delete(View view){


        String title3 = title4.getText().toString();
        System.out.println("deneme mi: " + title3);
        for(int i = 0; i < deletingObjects.size(); i++){

            if(deletingObjects.get(i).getTitle().equals(title3)){

                String post = deletingObjects.get(i).getPost_id();
                Intent intent = getIntent();
                id = intent.getStringExtra("INT_ID");
                System.out.println("buraya giriyor mu");
                new AsyncTask<String, String, String>()
                {

                    protected String doInBackground(String... strings)
                    {

                        try{
                            String url = "https://tux.csicxt.com/index.php";

                            String response = Jsoup.connect(url).ignoreContentType(true).data("op","del_post","account_id", id ,"shash","1602414", "post_id", post).post().text();
                            Log.e("x","Response From Server : ");
                            Log.e("x", String.valueOf(response));
                            dS = new JSONArray(response);
                        }

                        catch (Exception e){

                            Log.e("x","Fetch Err: " + e);

                        }

                        return null;
                    }
                }.execute();

            }
            Intent newIntent = new Intent(DeleteActivity.this,postlar.class);
            newIntent.putExtra("INT_ID", id );
            startActivity(newIntent);
        }
    }

}