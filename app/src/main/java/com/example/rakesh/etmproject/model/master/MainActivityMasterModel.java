package com.example.rakesh.etmproject.model.master;

import com.example.rakesh.etmproject.contract.MainActivityContractInterface;


/*
USER        Date            Version             Changes
Rakesh      13-06-2018     Initial Draft       No changes.
*/


public interface MainActivityMasterModel extends MainActivityContractInterface.Model,HttpTaskCompleteListener {

    void init();
    int id =1;
}
