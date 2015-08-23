package com.example.c.t30_criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by c on 2015-08-23.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
