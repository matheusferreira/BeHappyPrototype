package com.example.behappy.behappyapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.behappy.behappyapp.R;
import com.example.behappy.behappyapp.WebActivity;
import com.example.behappy.behappyapp.classes.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matheus on 30/06/2016.
 */
public class SimpleNewsListAdapter extends ArrayAdapter<News>{

    private List<News> newsFeed = new ArrayList<News>();
    LayoutInflater mInflater;
    Context mContext;
    String mURL;

    public SimpleNewsListAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        this.newsFeed = objects;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = mInflater.inflate(R.layout.listview_news_item, parent, false);
        }
        final News currentNews = newsFeed.get(position);
        //return super.getView(position, convertView, parent);

        TextView titleTxt = (TextView) itemView.findViewById(R.id.news_item_title);
        titleTxt.setText(currentNews.getTitle());

        TextView ratingTxt = (TextView) itemView.findViewById(R.id.news_item_rating);
        ratingTxt.setText("" + currentNews.getRating());

        TextView sourceTxt = (TextView) itemView.findViewById(R.id.news_item_source);
        sourceTxt.setText(currentNews.getSource());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, WebActivity.class);
                i.putExtra("mURL", String.valueOf(currentNews.getUrl()));
                Log.d("*** OUTBOUND INTENT: ", "" + i.getExtras().get("mURL") + "  SHOULD BE: " + currentNews.getUrl());
                mContext.startActivity(i);

            }
        });

        return itemView;

    }


}
