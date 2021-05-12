package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Register extends AppCompatActivity {

    EditText email;
    EditText phone;
    EditText country;
    EditText name;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        country = findViewById(R.id.country);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);

    }

    public void click_register(View view){


        register();

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }


    void register(){



        String email1 = email.getText().toString();
        String phone1 = phone.getText().toString();
        String country1 = country.getText().toString();
        String name1 = name.getText().toString();
        String password1 = password.getText().toString();
        new AsyncTask<String,String,String>(){
            @Override
            protected String doInBackground(String... strings) {


                try{
                    String url = "https://tux.csicxt.com/index.php";


                    Document response = Jsoup.connect(url).ignoreContentType(true).data("op","register","shash", "1602414" ,"un",email1,"pw",password1, "phone", phone1 ,"country", country1,"dn",name1).post();
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