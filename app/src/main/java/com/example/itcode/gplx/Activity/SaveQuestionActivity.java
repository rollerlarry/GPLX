package com.example.itcode.gplx.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.itcode.gplx.Adapter.SaveQuestionAdapter;
import com.example.itcode.gplx.Controller.QuestionControler;
import com.example.itcode.gplx.DTO.Question;
import com.example.itcode.gplx.R;

import java.util.ArrayList;

public class SaveQuestionActivity extends AppCompatActivity {
    private ListView lvSaveQuestion;
    private ArrayList<Question> questionArrayList;
    private SaveQuestionAdapter saveQuestionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_question);
        lvSaveQuestion = findViewById(R.id.lvSaveQuestion);
        QuestionControler questionControler = new QuestionControler(this);
        questionArrayList = questionControler.getSaveQuestionList();
        saveQuestionAdapter = new SaveQuestionAdapter(this, R.layout.item_save_question, questionArrayList);
        lvSaveQuestion.setAdapter(saveQuestionAdapter);
        registerForContextMenu(lvSaveQuestion);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        System.out.println("!23333");
        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
