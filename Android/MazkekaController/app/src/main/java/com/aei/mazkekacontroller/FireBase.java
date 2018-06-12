package com.aei.mazkekacontroller;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireBase {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private String name;
    private Listener listener;

    public FireBase(Listener listener){
        this.listener = listener;
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Clients");
    }

    private void setListeners(){
        databaseReference.child("Temp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    listener.tempUpdate(dataSnapshot.getValue(Double.class));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("SIT").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    listener.sitUpdate(dataSnapshot.getValue(String.class));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("W").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    listener.onDistChange(dataSnapshot.getValue(boolean.class));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setName(String name){
        this.name = name;
        check();
    }

    private void check(){
        databaseReference = database.getReference().child("Clients").child(name);
        databaseReference.child("ON").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.getValue(boolean.class)) {
                        Log.e("FireBase", "ON");
                        listener.onPowerChange(true);
                        setListeners();
                    } else {
                        listener.onPowerChange(false);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                if(databaseError.getCode() != DatabaseError.UNAVAILABLE)
                    return;
                listener.onPowerChange(false);
            }
        });
    }

    public void setDistiling(boolean t){
        databaseReference.child("CMD").setValue((t)?"S":"SP");
    }
    public void sendExit(){
        databaseReference.child("CMD").setValue("E");
    }

    interface Listener{
        void onPowerChange(boolean sit);
        void tempUpdate(double temp);
        void sitUpdate(String sit);
        void onDistChange(boolean sit);
    }
}
