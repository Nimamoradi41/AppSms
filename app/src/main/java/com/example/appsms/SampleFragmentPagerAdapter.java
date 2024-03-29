package com.example.appsms;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Tab1", "Tab2","Tat3" };
    private Context context;
    private Activity activity;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context,Activity Act) {
        super(fm);
        this.context = context;
        this.activity = Act;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
         if (position==0)
         {
             return  new  Frag_Recive();
         }
        if (position==1)
        {
            return  new  Frag_Send(activity);
        }
        return  new Frag_SendedSms(activity);
    }


}
