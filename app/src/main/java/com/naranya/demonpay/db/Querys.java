package com.naranya.demonpay.db;

import android.content.ContentValues;
import android.content.Context;
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


	public static void addSubscription(Context c, String id_subscription, String id_service) {
		SQLiteDatabase sampleDB = c.openOrCreateDatabase(Constants.DB_NAME_DATABASE, c.MODE_PRIVATE, null);
		sampleDB.execSQL("INSERT INTO tbl_subscriptions VALUES (" + "'" + id_subscription + "'" + "," + "'" + id_service + "'" + ")");
	}

	public static void clearSubscription( Context c, String id_service ) {
		SQLiteDatabase sampleDB = c.openOrCreateDatabase(Constants.DB_NAME_DATABASE, c.MODE_PRIVATE, null);
		String query = "DELETE FROM tbl_subscriptions WHERE id_service = '" + id_service + "'";
		sampleDB.execSQL(query);
	}

	public static String getIDSubscription( Context context, String id_service ) {
		SQLiteDatabase sampleDB = context.openOrCreateDatabase(Constants.DB_NAME_DATABASE, context.MODE_PRIVATE, null);
		String query = "SELECT DISTINCT id_subscription FROM tbl_subscriptions WHERE id_service = " + "'" + id_service + "'";

		String id = "";

		//Create Cursor object to read versions from the table
		Cursor c = sampleDB.rawQuery(query, null);
		//If Cursor is valid
		if (c != null ) {
			//Move cursor to first row
			if  (c.moveToFirst()) {
				do {
					id = c.getString(0);
				}while (c.moveToNext()); //Move to next row
			}
		}

		return id;
	}




	/*
	private static SQLiteDatabase dataBaseHelper = SQLiteDatabase.openDatabase(Constants.DB_PATH_DATABASE + Constants.DB_NAME_DATABASE, null, SQLiteDatabase.OPEN_READWRITE);


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
*/
}