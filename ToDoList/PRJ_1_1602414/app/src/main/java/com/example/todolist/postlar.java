package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.LinkedList;

public class postlar extends AppCompatActivity {

    String id;
    ListView listView;
    ArrayList<String> userNotesTitle;
    ArrayList<String> userNotesContent;
    JSONArray dS = new JSONArray();
    JSONObject user = new JSONObject();
    public static LinkedList<deletingObject> deletingObjects = new LinkedList<>();


    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Insert custom code here
            Adapter customAdapter = new Adapter(getApplicationContext(), userNotesTitle, userNotesContent);
            listView.setAdapter(customAdapter);
            customAdapter.notifyDataSetChanged();
            // Repeat every 2 seconds
            handler.postDelayed(runnable, 500);
            download();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.personal_information,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.change_password_item) {

            Intent newIntent = new Intent(postlar.this,personal_info.class);
            newIntent.putExtra("INT_ID", id );
            startActivity(newIntent);

        }

        else if(item.getItemId() == R.id.logout_item){

            Intent newIntent = new Intent(postlar.this,MainActivity.class);
            id = null;
            startActivity(newIntent);
            finish();


        }
        else if(item.getItemId() == R.id.add_item){

            Intent newIntent = new Intent(postlar.this,AddActivity.class);
            newIntent.putExtra("INT_ID", id );
            startActivity(newIntent);

        }
        else if(item.getItemId() == R.id.delete_item){

            Intent newIntent = new Intent(postlar.this,DeleteActivity.class);
            newIntent.putExtra("INT_ID", id );
            startActivity(newIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlar);

        listView = findViewById(R.id.listView);

        userNotesTitle = new ArrayList<>();
        userNotesContent = new ArrayList<>();

        download();

        handler.post(runnable);

        //adapter.notifyDataSetChanged();

    }

    public void download() {


        Intent intent = getIntent();
        id = intent.getStringExtra("INT_ID");



        new AsyncTask<String, String, String>()
        {

            protected String doInBackground(String... strings)
            {

                try{
                    String url = "https://tux.csicxt.com/index.php";

                    String response = Jsoup.connect(url).ignoreContentType(true).data("op","list_posts","id", id ,"shash","1602414").post().text();
                    Log.e("x","Response From Server : ");
                    Log.e("x", String.valueOf(response));
                    dS = new JSONArray(response);
                    int i = 0;
                    i += userNotesTitle.size();
                    for( ;i< dS.length(); i++){
                        user = dS.getJSONObject(i);
                        userNotesTitle.add(user.getString("title"));
                        userNotesContent.add(user.getString("txt"));

                        if((!user.getString("title").isEmpty()) || (!user.getString("id").isEmpty())) {

                            deletingObject deletingObject2 = new deletingObject(user.getString("title"),user.getString("id"));

                            if(deletingObject2 != null){
                                deletingObjects.add(deletingObject2);
                            }
                        }





                    }

                }

                catch (Exception e){

                    Log.e("x","Fetch Err: " + e);

                }

                Log.e("x", userNotesTitle.toString());
                Log.e("x", userNotesContent.toString());

                return null;
            }
        }.execute();


        //System.out.println("title için " + deletingObjects.isEmpty());
    }

}