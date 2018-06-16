package com.example.rakesh.etmproject.utils.constants;

/*
USER        Date            Version             Changes
Rakesh      13-06-2018      Initial Draft       No changes.
*/

public class DbConstants {

    /*Table Name*/
    public static final String TABLE_NAME = "user";
    /*Column Name*/
    public static final String COLUMN_USER_ID = "n_usr_id";
    /*Column Name*/
    public static final String COLUMN_USER_LOGIN_ID = "c_usr_lgn_id";
    /*Column Name*/
    public static final String COLUMN_USER_PASSWORD= "c_usr_passwd";
    /*Column Name*/
    public static final String COLUMN_USER_NAME= "c_usr_name";
    /*Column Name*/
    public static final String COLUMN_USER_ROLE= "n_usr_role_id";

    /*failed DB row delete */
    public static final int DB_DELETE_FAILED = 0;
    /*Insert to db failed*/
    public static final int DB_INSERT_FAILED = -1;
    /*createNewTable table to db failed*/
    public static final boolean DB_CRTEATION_FAILED = false;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_USER_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_USER_LOGIN_ID + " VARCHAR (8) ,"
                    + COLUMN_USER_PASSWORD + " VARCHAR (6),"
                    + COLUMN_USER_NAME + " VARCHAR (30),"
                    + COLUMN_USER_ROLE + " INTEGER"
                    + ")";


}
