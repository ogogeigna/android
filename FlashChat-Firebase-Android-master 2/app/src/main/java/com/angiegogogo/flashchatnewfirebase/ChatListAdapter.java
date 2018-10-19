package com.angiegogogo.flashchatnewfirebase;

/*
* We will write the code that acts as a bridge between the chat message data from Firebase and
*                                                      the listView needs to display the messages
* */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

// The BaseAdapter will act as the template that we're going to build our chat list adapter on top of
// Since our chat list adapter is now a child of the BaseAdapter, it inherits a whole bunch of methods and fields from the its class.
// BaseAdapter is the superclass, and ChatListAdapter is the child class
// => ChatListAdapter can do whatever BaseAdapter can do

public class ChatListAdapter extends BaseAdapter {

    // TODO: Create the needed variables
    private Activity mActivity;
    private DatabaseReference mDatabaseReference;
    private String mDisplayName;

    private ArrayList<DataSnapshot> mSnapshotList;
    // A DataSnapshot instance contains data from a Firebase Database location.
    // Any time you read Database data, you receive the data as a DataSnapshot.

    // TODO: Create a Firebase ChildEventListener
    // Classes implementing this interface can be used to receive events about changes in the child locations of a given DatabaseReference ref.
    // Attach the listener to a location using addChildEventListener(ChildEventListener) and the appropriate method will be triggered when changes occur.
    private ChildEventListener mListener = new ChildEventListener() {
        @Override
        // every time a chat message is sent, the onChildAdded() is triggered
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            mSnapshotList.add(dataSnapshot);
            notifyDataSetChanged();

        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };


    // TODO: Create Constructor
    public ChatListAdapter(Activity activity, DatabaseReference ref, String name) {

        mActivity = activity;
        mDisplayName = name;
        mDatabaseReference = ref.child("message");
        mDatabaseReference.addChildEventListener(mListener);

        mSnapshotList = new ArrayList<>();

    }

    // TODO: View holder
    // It will hold the values in a single row
    // this is an inner class
    static class ViewHolder {
        TextView authorName;
        TextView body;

        // we want to style our messages row programmatically at some point so we'll add this for the layout parameters.
        // 高矮胖瘦，居中之类的参数
        LinearLayout.LayoutParams params;
    }


    // ListView asks ViewHolder: "How many items do you have?"
    @Override
    public int getCount() {
        return mSnapshotList.size();
    }

    @Override
    public InstantMessage getItem(int position) {

        DataSnapshot snapshot = mSnapshotList.get(position);

        // TODO: converts the Json from the snapshot into an InstantMessage object
        return snapshot.getValue(InstantMessage.class);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // NOTE: You should check that this view is non-null and of an appropriate type before using.
        // If it is not possible to convert this view to display the correct data, this method can create a new view.
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.chat_msg_row, parent, false);

            // 设置对应关系
            final ViewHolder holder = new ViewHolder();
            holder.authorName = convertView.findViewById(R.id.author);
            holder.body = convertView.findViewById(R.id.message);
            holder.params = (LinearLayout.LayoutParams) holder.authorName.getLayoutParams();

            // setTag() is used to temporarily store the ViewHolder in the convertView => reuse
            convertView.setTag(holder);

        }

        // TODO: Populate the data for the row
        // NOTE: 将上面getItem（） 更改返回类型，不然不符合
        final InstantMessage message = getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();

        boolean isMe = message.getAuthor().equals(mDisplayName);
        setChatRowAppearance(isMe, holder);

        String author = message.getAuthor();
        holder.authorName.setText(author);

        String msg = message.getMessage();
        holder.body.setText(msg);



        return convertView;
    }


    // TODO: Styling
    private void setChatRowAppearance(boolean isMe, ViewHolder holder) {

        if (isMe) {
            holder.params.gravity = Gravity.START;
            holder.authorName.setTextColor(Color.GREEN);
            holder.body.setBackgroundResource(R.drawable.bubble1);
        } else {
            holder.params.gravity = Gravity.END;
            holder.authorName.setTextColor(Color.BLUE);
            holder.body.setBackgroundResource(R.drawable.bubble2);
        }

        holder.authorName.setLayoutParams(holder.params);
        holder.body.setLayoutParams(holder.params);

    }


    // TODO: remove the Firebase of EnventListener => free resources
    // when the app leaves the foreground, stop the adapter from checking for events from the Firebase database
    public void cleanup() {

        mDatabaseReference.removeEventListener(mListener);

    }


}
