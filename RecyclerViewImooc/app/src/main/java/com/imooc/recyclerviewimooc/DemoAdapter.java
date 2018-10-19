package com.imooc.recyclerviewimooc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<DataModel> mList = new ArrayList<>();


    public DemoAdapter (Context context) {

        mLayoutInflater = LayoutInflater.from(context);

    }

    // TODO: 让外部能够操作这个List
    public void addList(List<DataModel> list) {
        mList.addAll(list);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        switch (viewType) {
            case DataModel.TYPE_ONE:
                return new TypeOneViewHolder(mLayoutInflater.inflate(R.layout.item_type_one, viewGroup, false));
            case DataModel.TYPE_TWO:
                return new TypeOneViewHolder(mLayoutInflater.inflate(R.layout.item_type_two, viewGroup, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        ((TypeAbstractViewHolder)viewHolder).bindHolder(mList.get(position));


    }

    // TODO: 很重要的一个方法，记得要重写！！！
    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
