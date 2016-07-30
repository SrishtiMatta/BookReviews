package com.example.hp.bookreviews.Adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hp.bookreviews.Models.CriticReview;
import com.example.hp.bookreviews.R;

import java.util.ArrayList;

public class ReviewListAdapter extends ArrayAdapter<CriticReview> {
    Context context;
    ArrayList<CriticReview> reviews;
    public ReviewListAdapter(Context context, ArrayList<CriticReview> objects) {
        super(context, 0, objects);
        this.context = context;
        this.reviews = objects;
    }
    static  class ViewHolder{
        TextView snippet;
        TextView link;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView = View.inflate(context, R.layout.review_list_item_layout,null);
            ViewHolder vh = new ViewHolder();
            vh.snippet= (TextView)convertView.findViewById(R.id.snippetTextView);
            vh.link = (TextView)convertView.findViewById(R.id.linkTextView);
            convertView.setTag(vh);

        }
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.link.setText("Read More...");
        vh.snippet.setText(reviews.get(position).snippet);

        vh.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = reviews.get(position).reviewLink;
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                i.addCategory(Intent.CATEGORY_BROWSABLE);
                if(i.resolveActivity(context.getPackageManager())!=null)
                    context.startActivity(i);
                else
                    Log.i("Errr", "No Browser");
            }
        });

        return convertView;

    }

}

