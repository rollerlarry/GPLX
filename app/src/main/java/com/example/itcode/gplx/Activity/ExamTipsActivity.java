package com.example.itcode.gplx.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.itcode.gplx.Fragment.PracticeTipsFragment;
import com.example.itcode.gplx.Fragment.TheoryTipsFragment;
import com.example.itcode.gplx.R;

public class ExamTipsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPracticeTips;
    private Button btnTheoryTips;
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
        fragment = new TheoryTipsFragment();
        replaceFragment();
    }

    private void findView() {
        btnPracticeTips = findViewById(R.id.btnPracticeTips);
        btnTheoryTips = findViewById(R.id.btnTheoryTips);

    }

    private void setEvent() {
        btnPracticeTips.setOnClickListener(this);
        btnTheoryTips.setOnClickListener(this);
    }


    private void replaceFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContent, fragment);
        fragmentTransaction.commit();
    }

    private void changeStatus(Button btnSelected, Button btnNotSelected){
        btnSelected.setBackground(getDrawable(R.drawable.round_button_custom_selected));
        btnNotSelected.setBackground(getDrawable(R.drawable.round_button_custom_nonselected));
        btnSelected.setTextColor(getColor(R.color.black));

        btnNotSelected.setBackground(getDrawable(R.drawable.round_button_custom_nonselected));
        btnNotSelected.setTextColor(getColor(R.color.white));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTheoryTips:
                fragment = new TheoryTipsFragment();
                changeStatus(btnTheoryTips, btnPracticeTips);
                replaceFragment();
                break;
            case R.id.btnPracticeTips:
                fragment = new PracticeTipsFragment();
                changeStatus(btnPracticeTips, btnTheoryTips);
                replaceFragment();
                break;
        }
    }
}
