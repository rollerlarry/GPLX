package com.example.itcode.gplx.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itcode.gplx.DAO.DataModel;
import com.example.itcode.gplx.DTO.Question;
import com.example.itcode.gplx.R;
import com.example.itcode.gplx.Slide.ScreenSlideForExamActivity;

import java.util.ArrayList;

public class ExamResultActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<Question> questionArrayList;
    private int numNoAnswer = 0;
    private int numTrueAnswer = 0;
    private int numFalseAnswer = 0;
    private Button tbtnTrueAnswer, tbtnFalseAnswer, tbtnNoAnswer, btnBack, btnDoAgain, btnBackTopic, btnBackHome;
    private TextView tvTrueAnswer, tvTotalAnswer, tvResult;
    private LinearLayout bgResult;

    public static int typeExam = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_result);
        questionArrayList = new ArrayList<>();

        //Get question list from screen slide
        Intent intent = getIntent();
        try {
            questionArrayList = (ArrayList<Question>) intent.getExtras().getSerializable(ScreenSlideForExamActivity.QUESTION_ARRAY_LIST);
            typeExam = intent.getExtras().getInt("typeExam");
        }catch (Exception ex){
            questionArrayList = DataModel.QuestionArrayList;
            typeExam = DataModel.TypeExam;
        }

        findView();
        setEvent();
        checkResult();
        resultInfo();

        if (typeExam == 3){
            btnBackTopic.setVisibility(View.GONE);
            btnBackHome.setVisibility(View.VISIBLE);
        }
    }

    public void findView(){
        tbtnTrueAnswer = findViewById(R.id.tbtnTrueAnswer);
        tbtnFalseAnswer = findViewById(R.id.tbtnFalseAnswer);
        tbtnNoAnswer = findViewById(R.id.tbtnNoAnswer);

        tvTrueAnswer = findViewById(R.id.tvTrueAnswer);
        tvTotalAnswer = findViewById(R.id.tvTotalAnswer);
        tvResult = findViewById(R.id.tvResult);

        bgResult = findViewById(R.id.bgResult);
        btnBack = findViewById(R.id.btnBack);
        btnDoAgain = findViewById(R.id.btnDoAgain);
        btnBackTopic = findViewById(R.id.btnBackTopic);
        btnBackHome = findViewById(R.id.btnBackHome);
    }

    public void setEvent(){
        btnBack.setOnClickListener(this);
        btnDoAgain.setOnClickListener(this);
        btnBackTopic.setOnClickListener(this);
        btnBackHome.setOnClickListener(this);

        tbtnNoAnswer.setOnClickListener(this);
        tbtnFalseAnswer.setOnClickListener(this);
        tbtnTrueAnswer.setOnClickListener(this);
    }

    //Check result
    public void checkResult(){
        for (int i = 0; i < questionArrayList.size(); i++) {
            if (questionArrayList.get(i).getUserAnswer() == ""){
                numNoAnswer++;
            }else if (questionArrayList.get(i).getAnswerTrue().equals(questionArrayList.get(i).getUserAnswer()) == true){
                numTrueAnswer++;
            }else if (questionArrayList.get(i).getAnswerTrue().equals(questionArrayList.get(i).getUserAnswer()) == false){
                numFalseAnswer++;
            }
        }
    }

    public void resultInfo(){
        tbtnTrueAnswer.setText(numTrueAnswer+"");
        tbtnFalseAnswer.setText(numFalseAnswer+"");
        tbtnNoAnswer.setText(numNoAnswer+"");

        tvTrueAnswer.setText(numTrueAnswer+"");
        tvTotalAnswer.setText(questionArrayList.size()+"");
        if (numTrueAnswer >= (questionArrayList.size()/2)){
            tvResult.setText("ĐẠT");
        }else {
            tvResult.setText("KHÔNG ĐẠT");
            bgResult.setBackground(getResources().getDrawable(R.drawable.circle_bg_custom_false));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                //Fix error of slide can not change status when finish
                for (int i = 0; i < 4; i++) {
                    ScreenSlideForExamActivity.mPager.setCurrentItem(i);
                }
                //Start show from question 1
                ScreenSlideForExamActivity.mPager.setCurrentItem(0);
                //Call finish to back history exam
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.btnDoAgain:
                resetQuestionArrayList();
                ScreenSlideForExamActivity.questionArrayListOld.clear();
                ScreenSlideForExamActivity.questionArrayListOld.addAll(questionArrayList);

                ScreenSlideForExamActivity.checkDoAgain = 1;
                Intent intent = new Intent(this, ScreenSlideForExamActivity.class);
                intent.putExtra("typeExam", typeExam);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                break;
            case R.id.btnBackTopic:
                startActivity(new Intent(this, ExamForGroupActivity.class));
                ScreenSlideForExamActivity.checkDoAgain = 0;
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.btnBackHome:
                startActivity(new Intent(this, HomeActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.tbtnFalseAnswer:
                Toast.makeText(this, "Câu trả lời sai", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tbtnNoAnswer:
                Toast.makeText(this, "Câu trả lời chưa chọn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tbtnTrueAnswer:
                Toast.makeText(this, "Câu trả lời đúng", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //Reset to do again
    public void resetQuestionArrayList(){
        for (int i = 0; i < questionArrayList.size(); i++) {
            questionArrayList.get(i).setUserAnswer("");
        }
    }

    @Override
    public void onBackPressed() {

    }
}
