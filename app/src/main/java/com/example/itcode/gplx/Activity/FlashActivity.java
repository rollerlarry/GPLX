package com.example.itcode.gplx.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itcode.gplx.Helper.DatabaseHelper;
import com.example.itcode.gplx.R;

public class FlashActivity extends AppCompatActivity {
    private ImageView ivLogo;
    private TextView tvTitle;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        ivLogo = findViewById(R.id.ivLogo);
        tvTitle = findViewById(R.id.tvTitle);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.flash_screen_transition);
        ivLogo.startAnimation(animation);
        tvTitle.startAnimation(animation);
        Intent intentMain = new Intent(this,HomeActivity.class);
        Timer(intentMain);


        createDatabase();

    }

    public void Timer(final Intent intent){
        Thread thread = new Thread(){
            @Override
            public void run() {
            try {
                sleep(5000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                startActivity(intent);
                finish();
            }
            }
        };
        thread.start();
    }

    public void createDatabase(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }
}
