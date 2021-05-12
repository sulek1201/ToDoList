package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class personal_info extends AppCompatActivity {




    EditText oldPassword;
    EditText newPassword;
    JSONArray dS = new JSONArray();
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);

        Intent intent = getIntent();
        id = intent.getStringExtra("INT_ID");

        getData();
    }

    public void Save(View view){
        getData();

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    void getData()
    {

        String oldPassword1 = oldPassword.getText().toString();
        String newPassword1 = newPassword.getText().toString();
        new AsyncTask<String, String, String>()
        {

            protected String doInBackground(String... strings)
            {

                try{
                    String url = "https://tux.csicxt.com/index.php";




                    Document response = Jsoup.connect(url).ignoreContentType(true).data("op","change_password","id", id,"opw",oldPassword1,"npw",newPassword1,"shash", "1602414").post();
                    Log.e("x","Response From Server : ");
                    Log.e("x", String.valueOf(response));
                    dS = new JSONArray(response);
                    Log.e("x","deneme:  " + dS.length() );
                }

                catch (Exception e){

                    Log.e("x","Fetch Err: " + e);

                }


                return null;
            }
        }.execute();


    }
}