package com.example.itcode.gplx.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.itcode.gplx.Fragment.ExcelTipsFragment;
import com.example.itcode.gplx.Fragment.PowerPointTipsFragment;
import com.example.itcode.gplx.Fragment.WordTipsFragment;
import com.example.itcode.gplx.R;

public class ExamTipsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnTipsWord;
    private Button btnTipsExcel;
    private Button btnTipsPowerPoint;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_tips);

        findView();

        setEvent();

        fragmentManager = getSupportFragmentManager();
        fragment = new WordTipsFragment();
        replaceFragment();
    }

    private void findView() {
        btnTipsWord = findViewById(R.id.btnTipsWord);
        btnTipsExcel = findViewById(R.id.btnTipsExcel);
        btnTipsPowerPoint = findViewById(R.id.btnTipsPowerPoint);

    }

    private void setEvent() {
        btnTipsWord.setOnClickListener(this);
        btnTipsExcel.setOnClickListener(this);
        btnTipsPowerPoint.setOnClickListener(this);
    }


    private void replaceFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContent, fragment);
        fragmentTransaction.commit();
    }

    public void changeStatus(Button btnSelected, Button btnNotSelected1, Button btnNotSelected2){
        //Properties button selected
        btnSelected.setBackground(getDrawable(R.drawable.round_button_custom_selected));
        btnSelected.setTextColor(getColor(R.color.black));

        //Properties button not selected
        btnNotSelected1.setBackground(getDrawable(R.drawable.round_button_custom_nonselected));
        btnNotSelected2.setBackground(getDrawable(R.drawable.round_button_custom_nonselected));
        btnNotSelected1.setTextColor(getColor(R.color.white));
        btnNotSelected2.setTextColor(getColor(R.color.white));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTipsWord :
                fragment = new WordTipsFragment();
                changeStatus(btnTipsWord, btnTipsExcel, btnTipsPowerPoint);
                replaceFragment();
                break;
            case R.id.btnTipsExcel :
                fragment = new ExcelTipsFragment();
                changeStatus(btnTipsExcel, btnTipsWord, btnTipsPowerPoint);
                replaceFragment();
                break;
            case R.id.btnTipsPowerPoint :
                fragment = new PowerPointTipsFragment();
                changeStatus(btnTipsPowerPoint, btnTipsWord, btnTipsExcel);
                replaceFragment();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
