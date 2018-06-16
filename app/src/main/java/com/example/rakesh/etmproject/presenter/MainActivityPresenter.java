package com.example.rakesh.etmproject.presenter;

import android.content.Context;

import com.example.rakesh.etmproject.contract.MainActivityContractInterface;
import com.example.rakesh.etmproject.model.UserModel;
import com.example.rakesh.etmproject.model.master.MainActivityMasterModel;
import java.util.ArrayList;


/*
USER        Date            Version             Changes
Rakesh      13-06-2018     Initial Draft       No changes.
*/


public class MainActivityPresenter implements MainActivityContractInterface.Presenter {

    private MainActivityContractInterface.View mView;
    private Context mContext;
    private ArrayList<MainActivityMasterModel> alMasters;


    public MainActivityPresenter(MainActivityContractInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        initPresenter();
    }


    /*
    MethodId : initPresenter
    Input: Nothing
    Output: Nothing
    scope: project
    Description: to initialze View interface and creating an array of Models using MasterMModelInterface
    Version: 1.0
    */
    private void initPresenter() {
        mView.initViews();
        alMasters = new ArrayList<>();
        alMasters.add(new UserModel(mContext));
    }


    /*
    MethodId : onClickIntialize
    Input: Nothing
    Output: Nothing
    scope: project
    Description: to initialze models
    Version: 1.0
    */
    @Override
    public void onClickIntialize() {
        for(MainActivityMasterModel mamObj : alMasters) {
            mamObj.init();
        }
        mView.setResultData("");
    }
}
