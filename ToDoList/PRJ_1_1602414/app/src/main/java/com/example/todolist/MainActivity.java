package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {


    JSONArray dS = new JSONArray();
    EditText editText;
    EditText editText1;
    JSONObject user;
    JSONObject user2;
    String deneme4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextTextPersonName);
        editText1 = findViewById(R.id.editTextTextPassword);
        //register();
        //getData();

    }

    public void toaster(){

        Toast.makeText(MainActivity.this,"Login Incorrect",Toast.LENGTH_LONG).show();

    }
    public void lclick(View view){

        //System.out.println("ha şuna buraya bak: " + deneme4);
        getData();

    }
    public void rclick(View view){
        Intent intent = new Intent(getApplicationContext(),Register.class);
        startActivity(intent);
    }

   void getData(){


       String email = editText.getText().toString();
       String password = editText1.getText().toString();
        new AsyncTask<String,String,String>(){


            @Override
            protected String doInBackground(String... strings) {

               try{
                    String url = "https://tux.csicxt.com/index.php";


                    String response = Jsoup.connect(url).ignoreContentType(true).data("op","login","un",email,"pw",password, "shash", "1602414").post().text();

                    user = new JSONObject(response);


                    Log.e("x","Response From Server : ");
                    Log.e("x", user.toString());


                   user2 = new JSONObject(user.getString("user"));
                   Log.e("x", "şuna bak " + user2.getInt("id"));

                   Intent intent = new Intent(getApplicationContext(),postlar.class);
                   intent.putExtra("INT_ID", user2.getString("id") );
                   startActivity(intent);

                }

                catch (Exception e){


                    Log.e("x","Fetch Err: " + e);

                    try {
                        deneme4 = user.getString("r");
                        System.out.println("kac: " + deneme4);

                        if (deneme4.equals("0")) {


                            runOnUiThread(new Runnable() {
                                public void run() {

                                    Toast.makeText(MainActivity.this, "Username or Password is invalid", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }


                    }

                    catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }


                }
                return null;
            }

        }.execute();
    }
}