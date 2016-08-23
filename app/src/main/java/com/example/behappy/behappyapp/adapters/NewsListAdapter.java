package com.example.behappy.behappyapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.behappy.behappyapp.R;
import com.example.behappy.behappyapp.classes.News;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Matheus on 30/06/2016.
 */
public class NewsListAdapter extends ArrayAdapter<News>{

    private List<News> newsFeed = new ArrayList<News>();
    LayoutInflater mInflater;
    Context mContext;

    public NewsListAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        this.newsFeed = objects;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = mInflater.inflate(R.layout.listview_news, parent, false);
        }
        News currentNews = newsFeed.get(position);
        //return super.getView(position, convertView, parent);

        ImageView imageView = (ImageView)itemView.findViewById(R.id.news_image);
        //new DownloadImageTask(imageView).execute(currentNews.getImgPath()); //tá bugando a UI... investigar
        imageView.setImageResource(currentNews.getImgResourceID());


        TextView titleTxt = (TextView) itemView.findViewById(R.id.news_list_title);
        titleTxt.setText(currentNews.getTitle());

        TextView subtitleTxt = (TextView) itemView.findViewById(R.id.news_list_subtitle);
        subtitleTxt.setText(currentNews.getSubtitle());

        TextView ratingTxt = (TextView) itemView.findViewById(R.id.news_list_rating);
        ratingTxt.setText("" + currentNews.getRating());

        TextView fontTxt = (TextView) itemView.findViewById(R.id.news_list_source);
        fontTxt.setText(currentNews.getFont());

        TextView dateTxt = (TextView) itemView.findViewById(R.id.news_list_date);
        dateTxt.setText(currentNews.getData());

        return itemView;

    }


}
