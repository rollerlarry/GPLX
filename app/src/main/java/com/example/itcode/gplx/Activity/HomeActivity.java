package com.example.itcode.gplx.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.itcode.gplx.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView cvRandomExam, cvExamForGroup, cvLearnTheory, cvLearnPractice, cvExamTips, cvSaveQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cvRandomExam = findViewById(R.id.cvRandomExam);
        cvExamForGroup = findViewById(R.id.cvExamForGroup);
        cvLearnTheory = findViewById(R.id.cvLearnTheory);
        cvLearnPractice = findViewById(R.id.cvLearnPractice);
        cvExamTips = findViewById(R.id.cvExamTips);
        cvSaveQuestion = findViewById(R.id.cvSaveQuestion);

        cvRandomExam.setOnClickListener(this);
        cvExamForGroup.setOnClickListener(this);
        cvLearnTheory.setOnClickListener(this);
        cvLearnPractice.setOnClickListener(this);
        cvExamTips.setOnClickListener(this);
        cvSaveQuestion.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
            case R.id.cvRandomExam: intent = new Intent(this,RandomExamActivity.class);startActivity(intent); break;
            case R.id.cvExamForGroup: intent = new Intent(this,ExamForGroupActivity.class);startActivity(intent); break;
            case R.id.cvLearnTheory: intent = new Intent(this,LearnTheoryActivity.class);startActivity(intent); break;
            case R.id.cvLearnPractice: intent = new Intent(this,LearnPracticeActivity.class);startActivity(intent); break;
            case R.id.cvExamTips: intent = new Intent(this,ExamTipsActivity.class);startActivity(intent); break;
            case R.id.cvSaveQuestion: intent = new Intent(this,SaveQuestionActivity.class);startActivity(intent); break;
        }
    }
}
