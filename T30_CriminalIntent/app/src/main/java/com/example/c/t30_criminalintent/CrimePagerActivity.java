package com.example.c.t30_criminalintent;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by c on 2015-08-23.
 */
public class CrimePagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.myViewPager);
        setContentView(mViewPager);

        mCrimes = CrimeLab.get().getCrimes();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime c = mCrimes.get(position);
                return CrimeFragment.newInstance(c.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
    }
}
