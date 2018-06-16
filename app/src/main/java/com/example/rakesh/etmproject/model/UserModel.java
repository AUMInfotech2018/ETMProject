package com.example.rakesh.etmproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.rakesh.etmproject.model.master.MainActivityMasterModel;
import com.example.rakesh.etmproject.utils.constants.ApplicationConstant;
import com.example.rakesh.etmproject.utils.constants.DbConstants;
import com.example.rakesh.etmproject.utils.constants.NetworkConstants;
import com.example.rakesh.etmproject.utils.db.DatabaseHelper;
import com.example.rakesh.etmproject.utils.network.HttpConnection;


/*
USER        Date            Version             Changes
Rakesh      13-06-2018     Initial Draft       No changes.
*/

public class UserModel implements MainActivityMasterModel {

    private Context context;
    private int noOfPacketsReceived = 1;
    private boolean bContinue;
    private HttpConnection httpCon;
    private String crewMasterNumber;
    private int role = 2;
    private SQLiteDatabase db;
    private static final String TAG = UserModel.class.getSimpleName();

    public UserModel(Context context) {
        this.context = context;
    }

    /*
    MethodId : init
    Input: Nothing
    Output: Nothing
    scope: MainActivityPresenter.class
    Description: To initialize the UserModel
    Version: 1.0
    */
    @Override
    public void init() {

        /*Instance of HttpTaskCompleteListener*/
        httpCon = HttpConnection.getInstance(context, this);
        httpCon.httpGet(NetworkConstants.CLIENT_HTTP_CONNECTION_REQUEST_FULL, 5000, httpCon.setRetriesToDefault());

    }

    /*
    MethodId : getData
    Input: Nothing
    Output: Nothing
    scope:
    Description:
    Version: 1.0
    */
    @Override
    public void getData() {
    }

    /*
    MethodId : onSuccess
    Input: String Object
    Output: Nothing
    scope: UserModel, HttpTaskCompleteListener
    Description: to recieve string object data after getting responce from HttpTaskCompleteListener class
    Version: 1.0
    */
    @Override
    public void onSuccess(Object success) {
        String sResult = (String) success;
        /*Splitting string based on [$]*/
        sResult = sResult.replaceAll("[-+.^,]", "");
        String sInterateResult[] = sResult.split("[$]");
        /*Checking for Succesful response ACK:000*/
        switch (sInterateResult[0]) {
            case "ACK:000":
                if (sInterateResult[0].equalsIgnoreCase("ACK:000")) {
                    /*number of packets recieved*/
                    noOfPacketsReceived++;
                    /*update Crew number from response, initial value is 0*/
                    crewMasterNumber = sInterateResult[3];
                    /*check whether to continue or skip the loop Y=Continue, N=Skip*/

                    for (int i = 4; i < sInterateResult.length; i += 4) {
                        Log.d("dataPackets", sInterateResult[i]);
                        Log.d("dataPackets", sInterateResult[i + 1]);
                        Log.d("dataPackets", sInterateResult[i + 2]);
                        Log.d("dname", sInterateResult[i + 3]);
                        Log.d("drole", String.valueOf(role));
                        insertUserDetailsToDb(Integer.parseInt(sInterateResult[i]), sInterateResult[i + 1], sInterateResult[i + 2], sInterateResult[i + 3], role);
                    }
                    if (sInterateResult[2].equalsIgnoreCase("Y")) {
                        httpCon.httpGet(NetworkConstants.CLIENT_HTTP_CONNECTION_REQUEST
                                + NetworkConstants.CLIENT_REQUEST_PARAMS_KEY_1
                                + NetworkConstants.CLIENT_REQUEST_PARAMS_VALUE_1 + "&"
                                + NetworkConstants.CLIENT_REQUEST_PARAMS_KEY_2
                                + NetworkConstants.CLIENT_REQUEST_PARAMS_VALUE_2 + "$"
                                + role + "$" + noOfPacketsReceived + "$" + crewMasterNumber, 5000, httpCon.setRetriesToDefault());
                        Log.d("role", String.valueOf(role));

                    } else {
                        final int temp = 4;
                        role++;
                        if (role <= temp) {
                            noOfPacketsReceived = 1;
                            httpCon.httpGet(NetworkConstants.CLIENT_HTTP_CONNECTION_REQUEST
                                    + NetworkConstants.CLIENT_REQUEST_PARAMS_KEY_1
                                    + NetworkConstants.CLIENT_REQUEST_PARAMS_VALUE_1 + "&"
                                    + NetworkConstants.CLIENT_REQUEST_PARAMS_KEY_2
                                    + NetworkConstants.CLIENT_REQUEST_PARAMS_VALUE_2 + "$"
                                    + role + "$" + noOfPacketsReceived + "$" + crewMasterNumber, 5000, httpCon.setRetriesToDefault());
                        }
                    }
                }
                break;
            case "ACK:001":
                Toast.makeText(context, sInterateResult[1], Toast.LENGTH_SHORT).show();
                break;

            case "ACK:002":
                Toast.makeText(context, sInterateResult[1], Toast.LENGTH_SHORT).show();
                break;

            case "ACK:003":
                Toast.makeText(context, sInterateResult[1], Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /*
    MethodId : insertUserDetailsToDb
    Input: int,string,string,string,int
    Output: Nothing
    scope: UserModel
    Description: to insert data into DB when recieved from server
    Version: 1.0
    */
    private void insertUserDetailsToDb(int usedId, String loginId, String usrPassword, String usrName, int role) {
        db = ApplicationConstant.db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.COLUMN_USER_ID, usedId);
        contentValues.put(DbConstants.COLUMN_USER_LOGIN_ID, loginId);
        contentValues.put(DbConstants.COLUMN_USER_PASSWORD, usrPassword);
        contentValues.put(DbConstants.COLUMN_USER_NAME, usrName);
        contentValues.put(DbConstants.COLUMN_USER_ROLE, role);
        /*SQLite Inserting query*/
        long lSucces = DatabaseHelper.insert(DbConstants.TABLE_NAME, contentValues, db);
        /*Checking if data is inserted or not into the DB*/
        if (lSucces != DbConstants.DB_INSERT_FAILED) {
            Log.d(TAG, "data inserted successfully");
        } else {
            Log.d(TAG, "data insertion failed");
        }
    }


    /*
    MethodId : onFailure
    Input: String Object
    Output: Nothing
    scope: UserModel, HttpTaskCompleteListener
    Description: to display error message when we didnt recieve responce from HttpTaskCompleteListener class
    Version: 1.0
    */
    @Override
    public void onFailure(Object failure) {
        DatabaseHelper.deleteAll(DbConstants.TABLE_NAME, db);
        init();
    }
}
