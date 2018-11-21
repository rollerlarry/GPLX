package com.example.itcode.gplx.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.itcode.gplx.R;
import com.example.itcode.gplx.Slide.ScreenSlideActivity;

public class ExamForGroupActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView cvWordTopic,cvExcelTopic, cvPowerPointTopic;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_group);

        cvWordTopic = findViewById(R.id.cvWordTopic);
        cvExcelTopic = findViewById(R.id.cvExcelTopic);
        cvPowerPointTopic = findViewById(R.id.cvPowerPointTopic);
        cvWordTopic.setOnClickListener(this);
        cvExcelTopic.setOnClickListener(this);
        cvPowerPointTopic.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.cvWordTopic:
                notiExam("Bạn đã sẵn sàng để thi Word chưa ?", 0);
                break;
            case R.id.cvExcelTopic:
                notiExam("Bạn đã sẵn sàng để thi Excel chưa ?", 1);
                break;
            case R.id.cvPowerPointTopic:
                notiExam("Bạn đã sẵn sàng để thi PowerPoint chưa ?", 2);
                break;
        }
    }

    public void notiExam(String mes, final int typeExam){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.bell);
        builder.setTitle("Thông báo");
        builder.setMessage(mes);
        intent = new Intent(this, ScreenSlideActivity.class);
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
