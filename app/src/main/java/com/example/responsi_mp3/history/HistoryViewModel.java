package com.example.responsi_mp3.history;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class HistoryViewModel extends ViewModel {

    MutableLiveData<ArrayList<History>> historys;

    public void init (){

        if (historys !=null){
            return;
        }

        historys = HistoryRepository.getInstance().getHistories();
    }
    public LiveData<ArrayList<History>> getHistorys(){

        return historys;
    }
}
