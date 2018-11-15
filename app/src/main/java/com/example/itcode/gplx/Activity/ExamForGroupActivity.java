package com.example.itcode.gplx.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

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
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.bell);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn đã sẵn sàng để thi chưa ?");
        intent = new Intent(this, ScreenSlideActivity.class);
        switch (v.getId()){
            case R.id.cvWordTopic:
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intent.putExtra("typeExam", 0);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                break;
            case R.id.cvExcelTopic:
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intent.putExtra("typeExam", 1);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                break;
            case R.id.cvPowerPointTopic:
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intent.putExtra("typeExam", 2);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
