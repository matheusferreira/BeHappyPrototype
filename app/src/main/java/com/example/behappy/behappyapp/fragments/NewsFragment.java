package com.example.behappy.behappyapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.behappy.behappyapp.NewsNavigationActivity;
import com.example.behappy.behappyapp.R;
import com.example.behappy.behappyapp.classes.News;

/**
 * Created by Matheus on 30/06/2016.
 */


public class NewsFragment extends Fragment {

    private News news;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        news = ((NewsNavigationActivity)getActivity()).getNews();


        return inflater.inflate(R.layout.fragment_news_detail, container, false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView titleTxt = (TextView) view.findViewById(R.id.news_title);
        titleTxt.setText(news.getTitle());

        TextView author = (TextView) view.findViewById(R.id.news_author);
        author.setText(news.getAuthor());

        TextView ratingTxt = (TextView) view.findViewById(R.id.news_rating_txt);
        ratingTxt.setText("Rate: " + news.getRating());

        TextView date = (TextView) view.findViewById(R.id.news_date);
        date.setText(news.getData());

        TextView source = (TextView) view.findViewById(R.id.news_source);
        source.setText(news.getFont());

        TextView body = (TextView) view.findViewById(R.id.news_body_txt);
        body.setText(news.getBody());

        //TODO: button manipulation...
        ImageButton upvoteBtn = (ImageButton) view.findViewById(R.id.news_upvote_btn);
        ImageButton downvoteBtn = (ImageButton)view.findViewById(R.id.news_downvote_btn);
    }


}
