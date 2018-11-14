package com.example.itcode.gplx.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.itcode.gplx.Fragment.ContactFragment;
import com.example.itcode.gplx.Fragment.HomeFragment;
import com.example.itcode.gplx.Fragment.InfoFragment;
import com.example.itcode.gplx.Helper.DBHelper;
import com.example.itcode.gplx.R;

import java.io.IOException;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnHome, btnInfo, btnContact;
    private FragmentManager fragmentManager;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHome = findViewById(R.id.btnHome);
        btnInfo = findViewById(R.id.btnInfo);
        btnContact = findViewById(R.id.btnContact);

        btnHome.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
        btnContact.setOnClickListener(this);


        fragmentManager = getSupportFragmentManager();
        fragment = new HomeFragment();
        replaceFragment();

        DBHelper dbHelper = new DBHelper(this);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnHome :
                fragment = new HomeFragment();
                changeStatus(btnHome, btnInfo, btnContact);
                replaceFragment();
                break;
            case R.id.btnInfo :
                fragment = new InfoFragment();
                changeStatus(btnInfo, btnHome, btnContact);
                replaceFragment();
                break;
            case R.id.btnContact :
                fragment = new ContactFragment();
                changeStatus(btnContact, btnHome, btnInfo);
                replaceFragment();
                break;
        }
    }

    public void replaceFragment(){
        //Replace Fragment
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
}
