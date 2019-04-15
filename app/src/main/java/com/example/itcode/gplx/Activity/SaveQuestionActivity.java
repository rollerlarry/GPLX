package com.example.itcode.gplx.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.itcode.gplx.Adapter.SaveQuestionAdapter;
import com.example.itcode.gplx.Controller.QuestionControler;
import com.example.itcode.gplx.DTO.Question;
import com.example.itcode.gplx.R;
import com.example.itcode.gplx.Slide.ScreenSlideForExamActivity;

import java.util.ArrayList;

public class SaveQuestionActivity extends AppCompatActivity {
    private ListView lvSaveQuestion;
    private static ArrayList<Question> questionArrayList;
    private SaveQuestionAdapter saveQuestionAdapter;
    private AdapterView.AdapterContextMenuInfo menuItem;
    private QuestionControler questionControler;
    private EditText edtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_question);
        lvSaveQuestion = findViewById(R.id.lvSaveQuestion);
        edtSearch = findViewById(R.id.edtSearch);
        //Lay danh sach cau hoi luu tu trong CSDL
        questionControler = new QuestionControler(this);
        questionArrayList = questionControler.getSaveQuestionList();

        saveQuestionAdapter = new SaveQuestionAdapter(this, R.layout.item_save_question, questionArrayList);
        lvSaveQuestion.setAdapter(saveQuestionAdapter);

        registerForContextMenu(lvSaveQuestion);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchQuestion(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void searchQuestion(CharSequence s) {
        questionArrayList.clear();
        ArrayList<Question> arrTempQuestion = new ArrayList<>();
        arrTempQuestion = questionControler.getSaveQuestionList();
        for (int i = 0; i < arrTempQuestion.size(); i++) {
            if (arrTempQuestion.get(i).getTextQuestion().contains(s)){
                questionArrayList.add(arrTempQuestion.get(i));
            }
        }
        lvSaveQuestion.setAdapter(saveQuestionAdapter);
        saveQuestionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        menuItem = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }




}
