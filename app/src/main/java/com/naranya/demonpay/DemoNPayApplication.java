package com.naranya.demonpay;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.naranya.demonpay.db.DataBaseHelper;
import com.naranya.demonpay.db.Querys;
import com.naranya.demonpay.utils.Constants;

import java.io.IOException;

/**
 * Created by Naranya on 31/01/2016.
 */
public class DemoNPayApplication extends Application {

    SQLiteDatabase sampleDB = null;


    @Override
    public void onCreate() {
        super.onCreate();
        sampleDB = this.openOrCreateDatabase(Constants.DB_NAME_DATABASE, MODE_PRIVATE, null);
        sampleDB.execSQL("CREATE TABLE IF NOT EXISTS tbl_subscriptions" + " (id_subscription VARCHAR, id_service VARCHAR);");
    }
}
