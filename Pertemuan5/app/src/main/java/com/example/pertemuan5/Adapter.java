package com.example.pertemuan5;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Adapter extends FragmentStatePagerAdapter {


    public Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment =  new Fragment1();
                break;
            case 1:
                fragment = new Fragment2();
                break;

            default:
                throw new IllegalStateException();
        }

        return fragment;
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;

        switch(position) {
            case 0:
                title = "Fragment 1";
                break;

            case 1:
                title = "Fragment 2";
                break;

            default:
                throw new IllegalStateException();

        }

        return title;
    }
}
