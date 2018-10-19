package com.imooc.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;



// TODO: third-party apps 的实现能力完全取决于你的ContentProvider中的方法有多少，
// 他们在解析时受限于你的限制
public class MyContentProvider extends ContentProvider {

    // 创建之后需要实现这些方法，其实就是Database的方法


    // ContentProvider 创建后被调用
    @Override
    public boolean onCreate() {
        return false;
    }


    // 根据Uri查询出selection指定的条件所匹配的所有记录，
    // 并且可以指定查询哪些列，以什么方式(order)排序   s1->sortOrder
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }


    // 返回当前Uri MIME类型，如果该Uri对应的数据可能包括多条记录，
    // 那么 MIME 类型字符串以 vnd.android.dir/开头
    // 如果对应的数据只有一条， 那么 MIME 类型字符串以 vnd.android.cursor.item/开头
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }


    // 根据Uri插入ContentValues对应的数据
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }


    // 根据Uri删除selection指定的条件所匹配的所有记录
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }


    // 根据Uri修改selection 指定的条件所匹配的所有记录
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
