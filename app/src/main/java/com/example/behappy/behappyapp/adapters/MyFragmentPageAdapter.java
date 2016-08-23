package com.example.behappy.behappyapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.behappy.behappyapp.fragments.AboutFragment;
import com.example.behappy.behappyapp.fragments.FavoriteFragment;
import com.example.behappy.behappyapp.fragments.NewsFeedFragment;

/**
 * Created by Matheus on 28/06/2016.
 */
public class MyFragmentPageAdapter extends FragmentPagerAdapter{

    //Using FragmentPagerAdapter for the prototype, for dynamic data loaded in the fragments use FragmentStatePagerAdapter and implement its methods
    private String[] mTabTitles;
    public MyFragmentPageAdapter(FragmentManager fm, String[] mTabTitles) {
        super(fm);
        this.mTabTitles = mTabTitles;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new NewsFeedFragment();

            case 1:
                return new FavoriteFragment();

            case 2:
                return new AboutFragment();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return this.mTabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return this.mTabTitles[position];
    }
}
