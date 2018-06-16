package com.example.rakesh.etmproject.contract;

/*
USER        Date            Version             Changes
Rakesh      13-06-2018     Initial Draft       No changes.
*/


public interface MainActivityContractInterface {

    interface View{
        void initViews();
        void setResultData(String result);
    }

    interface Presenter{
        void onClickIntialize();
    }

    interface Model{
        void getData();
    }
}
