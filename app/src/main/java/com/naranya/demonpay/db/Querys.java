package com.naranya.demonpay.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.naranya.demonpay.utils.Constants;


/**
 * Provides functions CRUD Database
 * 
 * @author Naranya
 * @version 1.1 date Oct 23, 2014
 */
public class Querys {
	private static SQLiteDatabase dataBaseHelper = SQLiteDatabase.openDatabase(Constants.DB_PATH_DATABASE + Constants.DB_NAME_DATABASE, null, SQLiteDatabase.OPEN_READWRITE);
	
	/**
	 * Add Acquisition
	 * 
	 * @param  - The id acquisition
	 */
	public static void addSubscription(String id_subscription, String id_service) {
		ContentValues newRegister = new ContentValues();
		newRegister.put("id_subscription", id_subscription);
		newRegister.put("id_service", id_service);
		
		//Add
		dataBaseHelper.insert("tbl_subscriptions", null, newRegister);
	}

	public static void clearAllSubscriptions() {
		String query = "DELETE FROM tbl_subscriptions";
		dataBaseHelper.execSQL(query);
	}

	public static void clearSubscription( String id_service ) {
		String query = "DELETE FROM tbl_subscriptions WHERE id_service = '" + id_service + "'";
		dataBaseHelper.execSQL(query);
	}

	public static String getIDSubscription( String id_service ) {
		//Query
		String query = "SELECT DISTINCT id_subscription FROM tbl_subscriptions WHERE id_service = " + "'" + id_service + "'";
		
		//SUBSCRIPTION ID
		String id_subscription = null;
		
		//Cursor
		Cursor cursor = dataBaseHelper.rawQuery(query, null);
		
		if( cursor.getCount() != 0 ) {
			while( cursor.moveToNext() ) {
				id_subscription = cursor.getString(0);
			}

		}
        cursor.close();
		
		return id_subscription;
	}
}