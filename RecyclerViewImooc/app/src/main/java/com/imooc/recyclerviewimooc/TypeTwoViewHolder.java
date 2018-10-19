package com.imooc.recyclerviewimooc;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TypeTwoViewHolder extends TypeAbstractViewHolder {

    public ImageView avatar;
    public TextView name;
    public TextView content;

    public TypeTwoViewHolder(@NonNull View itemView) {
        super(itemView);

        avatar = itemView.findViewById(R.id.avatar);
        name = itemView.findViewById(R.id.name);
        content = itemView.findViewById(R.id.content);
    }


    // 发现好几个holder都要使用这个类，所以我们为了方便单独创建出一个abstract class
    @Override
    // TODO: 将ViewHolder和外面数据进行绑定
    public void bindHolder(DataModel model) {
        avatar.setBackgroundResource(model.avatarColor);
        name.setText(model.name);
        content.setText(model.content);
    }

}
