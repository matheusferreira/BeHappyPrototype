package com.example.behappy.behappyapp.adapters;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.behappy.behappyapp.NewsNavigationActivity;
import com.example.behappy.behappyapp.classes.News;
import com.example.behappy.behappyapp.fragments.CommentFragment;
import com.example.behappy.behappyapp.fragments.NewsFeedFragment;
import com.example.behappy.behappyapp.fragments.NewsFragment;
import com.example.behappy.behappyapp.fragments.RelatedFragment;

/**
 * Created by Matheus on 30/06/2016.
 */
public class NewsFragmentPageAdapter extends FragmentPagerAdapter {

    //Using FragmentPagerAdapter for the prototype, for dynamic data loaded in the fragments use FragmentStatePagerAdapter and implement its methods
    private String[] mTabTitles;
    private News news;

    public NewsFragmentPageAdapter(FragmentManager fm, String[] mTabTitles, News news) {
        super(fm);
        this.mTabTitles = mTabTitles;
        this.news = news;

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new NewsFragment();

            case 1:
                return new CommentFragment();

            case 2:
                return new RelatedFragment();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return this.mTabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.mTabTitles[position];
    }
}
