package com.imooc.recyclerviewimooc;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TypeOneViewHolder extends TypeAbstractViewHolder {

    public ImageView avatar;
    public TextView name;

    public TypeOneViewHolder(@NonNull View itemView) {
        super(itemView);

        avatar = itemView.findViewById(R.id.avatar);
        name = itemView.findViewById(R.id.name);
    }

    @Override
    // TODO: 将ViewHolder和外面数据进行绑定
    public void bindHolder(DataModel model) {
        avatar.setBackgroundResource(model.avatarColor);
        name.setText(model.name);
    }

}
