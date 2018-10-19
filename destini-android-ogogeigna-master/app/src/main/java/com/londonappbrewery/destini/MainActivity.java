package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    private TextView mStoryTextView;
    private Button mTopButton;
    private Button mButtomButton;
    int mStoryIndex;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Step 4 - Wire up the 3 views from the layout to the member variables:
         mStoryIndex = 1;

        mStoryTextView= findViewById(R.id.storyTextView);
        mTopButton = findViewById(R.id.buttonTop);
        mButtomButton = findViewById(R.id.buttonBottom);


        // TODO: step5 - set OnClickListener on each button
        mTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mStoryIndex == 1 || mStoryIndex == 2) {
                    mStoryTextView.setText(R.string.T3_Story);
                    mTopButton.setText(R.string.T3_Ans1);
                    mButtomButton.setText(R.string.T3_Ans2);
                    mStoryIndex = 3;

                } else {
                    mStoryTextView.setText(R.string.T6_End);
                    mTopButton.setVisibility(View.GONE);
                    mButtomButton.setVisibility(View.GONE);

                }

            }
        });




        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        mButtomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mStoryIndex == 1) {
                    mStoryTextView.setText(R.string.T2_Story);
                    mTopButton.setText(R.string.T2_Ans1);
                    mButtomButton.setText(R.string.T2_Ans2);
                    mStoryIndex = 2;

                } else if (mStoryIndex == 2) {
                    mStoryTextView.setText(R.string.T4_End);
                    mTopButton.setVisibility(View.GONE);
                    mButtomButton.setVisibility(View.GONE);

                } else {
                    mStoryTextView.setText(R.string.T5_End);
                    mTopButton.setVisibility(View.GONE);
                    mButtomButton.setVisibility(View.GONE);
                }

            }
        });
    }

}