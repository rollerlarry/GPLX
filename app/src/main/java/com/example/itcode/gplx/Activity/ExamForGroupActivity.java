package com.example.itcode.gplx.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.itcode.gplx.R;
import com.example.itcode.gplx.Slide.ScreenSlideActivity;

public class ExamForGroupActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView cvWordTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_group);

        cvWordTopic = findViewById(R.id.cvWordTopic);
        cvWordTopic.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cvWordTopic:
                Intent intent = new Intent(this, ScreenSlideActivity.class);
                startActivity(intent);
        }
    }
}
