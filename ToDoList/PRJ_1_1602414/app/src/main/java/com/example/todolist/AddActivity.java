package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.jsoup.Jsoup;

public class AddActivity extends AppCompatActivity {


    EditText title;
    EditText msg;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        title = findViewById(R.id.title1);
        msg = findViewById(R.id.msj);
    }


    public void add2(View view){

        add();

        Intent intent = getIntent();
        id = intent.getStringExtra("INT_ID");

        Intent newIntent = new Intent(AddActivity.this,postlar.class);
        newIntent.putExtra("INT_ID", id );
        startActivity(newIntent);

    }
    public void add(){

        String title2 = title.getText().toString();
        String msg2 = msg.getText().toString();
        Intent intent = getIntent();
        id = intent.getStringExtra("INT_ID");

        new AsyncTask<String, String, String>()
        {

            protected String doInBackground(String... strings)
            {

                try{
                    String url = "https://tux.csicxt.com/index.php";

                    Intent intent = getIntent();
                    id = intent.getStringExtra("INT_ID");

                    String response = Jsoup.connect(url).ignoreContentType(true).data("op","add_post","id", id ,"shash","1602414", "title", title2, "txt", msg2).post().text();
                    Log.e("x","Response From Server : ");
                    Log.e("x", String.valueOf(response));



                }

                catch (Exception e){

                    Log.e("x","Fetch Err: " + e);

                }

                return null;
            }
        }.execute();



    }

}