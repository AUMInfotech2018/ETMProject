package com.example.rakesh.etmproject.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rakesh.etmproject.R;
import com.example.rakesh.etmproject.contract.MainActivityContractInterface;
import com.example.rakesh.etmproject.presenter.MainActivityPresenter;


/*
USER        Date            Version             Changes
Rakesh      13-06-2018     Initial Draft       No changes.
*/

public class MainActivity extends AppCompatActivity implements MainActivityContractInterface.View {

    private MainActivityContractInterface.Presenter presenter;
    private TextView textDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this, this);
    }


    /*
    MethodId : init
    Input: Nothing
    Output: Nothing
    scope: MainActivity.class
    Description: To initialize the views components like Text,Button
    Version: 1.0
    */
    @Override
    public void initViews() {
        textDisplay = findViewById(R.id.txt_activity_main_display_result);
        final Button initialize = findViewById(R.id.btn_activity_main_initialize);
        initialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickIntialize();
            }
        });
    }


    /*
    MethodId : setResultData
    Input: string
    Output: Nothing
    scope: MainActivity.class
    Description: To display result from presenter classs
    Version: 1.0
    */
    @Override
    public void setResultData(String result) {
        textDisplay.setText(result);
    }
}
