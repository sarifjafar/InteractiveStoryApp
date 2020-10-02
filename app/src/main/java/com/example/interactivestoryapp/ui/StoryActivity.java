package com.example.interactivestoryapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.interactivestoryapp.R;
import com.example.interactivestoryapp.model.Page;
import com.example.interactivestoryapp.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {
    private Story story;
    private TextView text;
    private ImageView image;
    private Button choice1;
    private Button choice2;
    private int nextPageChoice1;
    private int getNextPageChoice2;
    private String name;


    private Stack<Integer> pageStack = new Stack<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        //pageNumber = intent.getIntExtra(getString(R.string.pageNumber),0);
        name = intent.getStringExtra(getString(R.string.name));

        text = (TextView) findViewById(R.id.textView);
        image = (ImageView)findViewById(R.id.missionImageView);
        choice1 = (Button)findViewById(R.id.choice1Button);
        choice2 = (Button)findViewById(R.id.choice2Button);

        story = new Story();
        loadPage(0);

//        choice1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(StoryActivity.this,StoryActivity.class);
//                intent.putExtra(getString(R.string.pageNumber),nextPageChoice1);
//                startActivity(intent);
//            }
//        });
//
//        choice2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(StoryActivity.this,StoryActivity.class);
//                intent.putExtra(getString(R.string.pageNumber),getNextPageChoice2);
//                startActivity(intent);
//            }
//        });

    }

//    private void loadPage(int i) {
//        Page page = story.getPage(i);
//        if (page.isFinalPage()) {
//            Toast.makeText(StoryActivity.this,"You Have Reached The Final Chapter",Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(StoryActivity.this, MainActivity.class);
//            //intent.putExtra(getString(R.string.pageNumber), 0);
//            startActivity(intent);
//        } else {
//            Drawable drawable = ContextCompat.getDrawable(this, page.getImageId());
//            image.setImageDrawable(drawable);
//            String pageText = getString(page.getTextId());
//            pageText = String.format(pageText, name);
//            text.setText(pageText);
//            //Log.d("jeff", "loadPage: "+getString(page.getChoice1().getTextId()));
//            choice1.setText(getString(page.getChoice1().getTextId()));
//            nextPageChoice1 = page.getChoice1().getNextPageId();
//            choice2.setText(getString(page.getChoice2().getTextId()));
//            getNextPageChoice2 = page.getChoice2().getNextPageId();
//        }
//    }

    private void loadPage(int pageNumber) {
        pageStack.push(pageNumber);
        final Page page = story.getPage(pageNumber);
        Drawable drawable = ContextCompat.getDrawable(this, page.getImageId());
        image.setImageDrawable(drawable);
        String pageText = getString(page.getTextId());
        pageText = String.format(pageText, name);
        text.setText(pageText);
        if (page.isFinalPage()) {
            choice1.setVisibility(View.INVISIBLE);
            choice2.setText(R.string.play_again_button_text);
            choice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            loadButton(page);
        }
    }

    private void loadButton(final Page page) {
        choice1.setVisibility(View.VISIBLE);
        choice1.setText(getString(page.getChoice1().getTextId()));
        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice1().getNextPageId();
                loadPage(nextPage);
            }
        });
        choice2.setText(getString(page.getChoice2().getTextId()));
        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice2().getNextPageId();
                loadPage(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        pageStack.pop();
        if (pageStack.size() == 0){
            super.onBackPressed();
        }else{
            loadPage(pageStack.pop());
        }
    }
}
