package com.example.itcode.gplx.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.itcode.gplx.R;
import com.example.itcode.gplx.Slide.ScreenSlideForExamActivity;
import com.example.itcode.gplx.Slide.ScreenSlideLearnTheoryActivity;

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
                notiExam("Bạn có muốn ôn tập lý thuyết Word không ?", 0);
                break;
            case R.id.cvExcelLearn:
                notiExam("Bạn có muốn ôn tập lý thuyết Excel không ?", 1);
                break;
            case R.id.cvPowerPointLearn:
                notiExam("Bạn có muốn ôn tập lý thuyết PowerPoint không ?", 2);
                break;
        }
    }

    public void notiExam(String mes, final int typeExam){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.bell);
        builder.setTitle("Thông báo");
        builder.setMessage(mes);
        intent = new Intent(this, ScreenSlideLearnTheoryActivity.class);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                intent.putExtra("typeExam", typeExam);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
