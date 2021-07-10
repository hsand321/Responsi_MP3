package com.example.responsi_mp3.history;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryRepository {

    static HistoryRepository instance;
    private ArrayList<History> arrayList = new ArrayList<>();
    private MutableLiveData<ArrayList<History>> history = new MutableLiveData<>();

    public static HistoryRepository getInstance(){

        if (instance == null){
            instance = new HistoryRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<History>> getHistories(){

        if (arrayList.size()==0){
            loadHistories();
        }


        history.setValue(arrayList);

        return history;
    }

    private void loadHistories() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child("data");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot :datasnapshot.getChildren()){

                    arrayList.add(snapshot.getValue(History.class));
                }
                history.postValue(arrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
