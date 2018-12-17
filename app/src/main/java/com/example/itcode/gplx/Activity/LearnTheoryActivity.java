package com.example.itcode.gplx.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.itcode.gplx.Controller.QuestionControler;
import com.example.itcode.gplx.R;
import com.example.itcode.gplx.Slide.ScreenSlideForLearnTheoryActivity;

public class LearnTheoryActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView cvWordLearn,cvExcelLearn, cvPowerPointLearn;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_theory);
        cvWordLearn = findViewById(R.id.cvWordLearn);
        cvExcelLearn = findViewById(R.id.cvExcelLearn);
        cvPowerPointLearn = findViewById(R.id.cvPowerPointLearn);
        cvWordLearn.setOnClickListener(this);
        cvExcelLearn.setOnClickListener(this);
        cvPowerPointLearn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.cvWordLearn:
                goActivity(0);
                break;
            case R.id.cvExcelLearn:
                goActivity(1);
                break;
            case R.id.cvPowerPointLearn:
                goActivity(2);
                break;
        }
    }

    public void goActivity(final int typeExam){
        intent = new Intent(this, ScreenSlideForLearnTheoryActivity.class);
        intent.putExtra("typeExam", typeExam);
        ScreenSlideForLearnTheoryActivity.NUM_PAGES = questionCountNumber(typeExam);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public int questionCountNumber(int typeExam){
        QuestionControler questionControler = new QuestionControler(this);
        return questionControler.getQuestionCountNumber(typeExam);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
