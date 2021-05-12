package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    Context context;
    ArrayList<String> noteTitle;
    ArrayList<String> noteContent;
    LayoutInflater inflater;

    public Adapter(Context applicationContext, ArrayList noteTitle, ArrayList noteContent) {
        this.context = context;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return noteTitle.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.adap_den, null);
        TextView userNameText = (TextView) view.findViewById(R.id.custom_view_note_title);
        TextView commentText = (TextView) view.findViewById(R.id.custom_view_note_content);
        userNameText.setText(noteTitle.get(i));
        commentText.setText(noteContent.get(i));
        return view;
    }
}
