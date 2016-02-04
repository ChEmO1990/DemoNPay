package com.naranya.demonpay.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.naranya.demonpay.ui.fragments.CatalogFragment;
import com.naranya.demonpay.ui.fragments.ServiceFragment;

/**
 * Created by anselmo on 2/3/16.
 */

public class CustomPagerAdapter extends FragmentPagerAdapter {
    Context mContext;

    public CustomPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        // Create fragment object
        Fragment fragment = null;

        switch ( position ) {
            case 0:
                fragment = new ServiceFragment();
                break;

            case 1:
                fragment = new CatalogFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";

        switch ( position ) {
            case 0: title = "Servicio Suscripciones"; break;
            case 1: title = "Servicio Catalogos"; break;
        }

        return title;
    }
}
