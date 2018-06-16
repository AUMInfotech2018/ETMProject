package com.example.rakesh.etmproject.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.rakesh.etmproject.utils.constants.DbConstants;


/*
USER        Date            Version             Changes
Rakesh      13-06-2018      Initial Draft       No changes.
*/

/**
 * The type Database.
 */
public class Database extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    /*creating singleton instance*/
    private static Database dbInstance;


    /**
     * Get instance database.
     *
     * @param context      the context
     * @param databaseName the database name
     * @return the database
     */
    public static Database getInstance(Context context,String databaseName){
        if(dbInstance ==null){
            dbInstance = new Database(context.getApplicationContext(),databaseName);
        }
        return dbInstance;
    }

    /**
     * constructor for Database class.
     *
     * @param context      the context
     * @param databaseName the database name
     */
    private Database(Context context, String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(DbConstants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +DbConstants.TABLE_NAME);
        onCreate(db);
    }

}
