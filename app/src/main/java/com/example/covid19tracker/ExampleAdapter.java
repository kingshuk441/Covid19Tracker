package com.example.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19tracker.ExampleItem;
import com.example.covid19tracker.R;

import java.util.ArrayList;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    //6.2
    private ArrayList<ExampleItem> mExampleList;

    //1
    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        //4
        public TextView state,conf,act,rec,decr;

        //2
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            //4
            state=itemView.findViewById(R.id.stateTv);
            conf=itemView.findViewById(R.id.confirmedTv);
            act=itemView.findViewById(R.id.activeTv);
            rec=itemView.findViewById(R.id.recoveredTv);
            decr=itemView.findViewById(R.id.deceasedTv);

        }
    }

    //6.1
    public ExampleAdapter(ArrayList<ExampleItem> exampleList){
        mExampleList = exampleList;
    }


    //3
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //5
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(view);
        return exampleViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        //7
        ExampleItem currentItem = mExampleList.get(i);
        exampleViewHolder.act.setText(currentItem.getmActive());
        exampleViewHolder.state.setText(currentItem.getmState());
        exampleViewHolder.decr.setText(currentItem.getmDec());
        exampleViewHolder.rec.setText(currentItem.getmRec());
        exampleViewHolder.conf.setText(currentItem.getMconf());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }




}
