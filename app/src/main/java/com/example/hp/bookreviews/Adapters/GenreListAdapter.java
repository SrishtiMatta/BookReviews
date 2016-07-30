package com.example.hp.bookreviews.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hp.bookreviews.Models.GenreListItemClass;
import com.example.hp.bookreviews.R;

import java.util.ArrayList;


public class GenreListAdapter extends ArrayAdapter<GenreListItemClass> {
    Context context;
    ArrayList<GenreListItemClass> bookList;

    public GenreListAdapter(Context context, ArrayList<GenreListItemClass> objects) {
        super(context, 0, objects);
        this.context = context;
        this.bookList = objects;
    }

    static class ViewHolder {
        TextView aName;
        TextView bName;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.genre_list_item_layout, null);
            ViewHolder vh = new ViewHolder();
            vh.aName = (TextView) convertView.findViewById(R.id.authorNameTextView);
            vh.bName = (TextView) convertView.findViewById(R.id.bookNameTextView);
            convertView.setTag(vh);

        }
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.aName.setText("By: "+bookList.get(position).aName);
        vh.bName.setText(bookList.get(position).bName);
        return convertView;

    }
}
