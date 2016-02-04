package com.naranya.demonpay;

import android.app.Application;

import com.naranya.demonpay.db.DataBaseHelper;

import java.io.IOException;

/**
 * Created by Naranya on 31/01/2016.
 */
public class DemoNPayApplication extends Application {
    private DataBaseHelper db_helper;

    @Override
    public void onCreate() {
        super.onCreate();

        db_helper = new DataBaseHelper(this);

        try {
            db_helper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
