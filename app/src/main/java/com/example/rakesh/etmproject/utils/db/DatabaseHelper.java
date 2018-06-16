package com.example.rakesh.etmproject.utils.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;


/*
USER        Date            Version             Changes
Rakesh      13-06-2018      Initial Draft       No changes.
Rakesh      15-06-2018      1.0                 deteteAll,batchTableCreate
*/

/**
 * The type Database helper.
 */
public class DatabaseHelper {


    private static String DB_TABLE_CREATION_ERROR = "there was an error creating TABLE in";
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static ArrayList<String> alBatch = new ArrayList<>();

    /**
     * Insert a row into DB.
     *
     * @param tableName the table name
     * @param values    the values
     * @param db        the db
     * @return the long
     */
/*
     Method id: insert
     Input: string,contentvalues,sqlitedatabase
     Output: long
     Scope: project
     Description: to insert a record to the DB
     Version: 1.0
    */
    public static long insert(String tableName, ContentValues values, @NonNull SQLiteDatabase db) {
        // insert row
        long lId = db.insert(tableName, null, values);
        // return newly inserted row id
        return lId;
    }


    /**
     * Update a row into DB.
     *
     * @param tableName the table name
     * @param columnId  the column id
     * @param values    the values
     * @param id        the id
     * @param db        the db
     * @return the int
     */
/*
    Method id: update
    Input: string,string,contentvalues,int,sqlitedatabase
    Output: int
    Scope: project
    Description: to update a record to the DB
    Version: 1.0
   */
    public static int update(String tableName, String columnId, ContentValues values, int id, @NonNull SQLiteDatabase db) {
        // updating row
        int iId = db.update(tableName, values, columnId + " = ?", new String[]{String.valueOf(id)});
        // return updated row id
        return iId;
    }


    /**
     * Delete record from DB.
     *
     * @param tableName the table name
     * @param id        the id
     * @param db        the db
     * @return the int
     */
/*
    Method id: deleteRecord
    Input: string,int,sqlitedatabase
    Output: Nothing
    Scope: project
    Description: to delete a record to the DB
    Version: 1.0
   */
    public static int deleteRecord(String tableName, int id, @NonNull SQLiteDatabase db) {
        // delete row
        int iResult = db.delete(tableName, "id = ?", new String[]{String.valueOf(id)});
        /*return success or failure*/
        return iResult;
    }


    /**
     * Delete all records from DB.
     *
     * @param tableName the table name
     * @param db        the db
     */
    public static void deleteAll(String tableName, @NonNull SQLiteDatabase db) {
        // delete row
        db.execSQL("DELETE FROM " + tableName);
    }


    /**
     * Create table in DB.
     *
     * @param query the qry
     * @param db    the db
     * @return the boolean
     * @throws SQLException the sql exception
     */
    /*
    Method id: batchCreate
    Input: string,sqlitedatabase
    Output: boolean
    Scope: project
    Description: to Create a Table in the DB
    Version: 1.0
   */
    public static boolean createTable(String query, @NonNull SQLiteDatabase db) {
        try {
            // create new sqlite table
            db.execSQL(query);
            // return true for success
            return true;
        } catch (SQLException e) {
            Log.e(DB_TABLE_CREATION_ERROR," batchTableCreate() : "+e.getMessage());
            // return false for failure
            return false;
        }
    }


    public static boolean batchTableCreate(ArrayList<String> createStatements, @NonNull SQLiteDatabase db) {
        try {
            /*Create tables for list of all queries*/
            for (String eachQry : createStatements) {
                // create new sqlite table
                db.execSQL(eachQry);
            }
            // return true for success
            return true;
        } catch (SQLException e) {
            Log.e(DB_TABLE_CREATION_ERROR," batchTableCreate() : "+e.getMessage());
            // return false for failure
            return false;
        }
    }

}
