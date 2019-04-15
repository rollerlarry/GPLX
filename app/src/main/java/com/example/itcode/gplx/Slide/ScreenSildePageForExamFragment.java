package com.example.itcode.gplx.Slide;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itcode.gplx.DTO.Question;
import com.example.itcode.gplx.R;

import java.util.ArrayList;

public class ScreenSildePageForExamFragment extends Fragment implements View.OnClickListener{

    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECK_FINISH = "check_finish";

    private ArrayList<Question> questionArrayList;
    private int pageNumberCurrent;
    private int checkFinish;

    private TextView tvQuestion, tvNumPrev, tvNumCurrent, tvNumNext;
    private RadioGroup radioGroup;
    private RadioButton radA, radB, radC, radD;
    private ImageView imvQuestion;
    private ImageView imvType;

    public ScreenSildePageForExamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
        findView(rootView);

        int type = questionArrayList.get(pageNumberCurrent).getQuestionTypeID();
        if (type == 0){
            imvType.setImageDrawable(getContext().getDrawable(R.drawable.word));
        }else if (type == 1){
            imvType.setImageDrawable(getContext().getDrawable(R.drawable.excel));
        }else{
            imvType.setImageDrawable(getContext().getDrawable(R.drawable.powerpoint));
        }

        tvNumNext.setOnClickListener(this);
        tvNumPrev.setOnClickListener(this);
        return rootView;
    }

    public void findView(ViewGroup rootView){
        tvQuestion = rootView.findViewById(R.id.tvQuestion);
        radioGroup = rootView.findViewById(R.id.radGroup);
        radA = rootView.findViewById(R.id.radA);
        radB = rootView.findViewById(R.id.radB);
        radC = rootView.findViewById(R.id.radC);
        radD = rootView.findViewById(R.id.radD);
        tvNumPrev = rootView.findViewById(R.id.tvNumPrev);
        tvNumCurrent = rootView.findViewById(R.id.tvNumCurrent);
        tvNumNext = rootView.findViewById(R.id.tvNumNext);

        imvQuestion = rootView.findViewById(R.id.imvQuestion);
        imvType = rootView.findViewById(R.id.imvType);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionArrayList = new ArrayList<>();
        ScreenSlideForExamActivity screenSlideForExamActivity = (ScreenSlideForExamActivity) getActivity();
        questionArrayList = screenSlideForExamActivity.getData();
        pageNumberCurrent = getArguments().getInt(ARG_PAGE);
        checkFinish = getArguments().getInt(ARG_CHECK_FINISH);


    }

    public Question getItem(int position){
        return questionArrayList.get(position);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvQuestion.setText(questionArrayList.get(pageNumberCurrent).getTextQuestion()+"");
        radA.setText(getItem(pageNumberCurrent).getAnswerA());
        radB.setText(getItem(pageNumberCurrent).getAnswerB());
        radC.setText(getItem(pageNumberCurrent).getAnswerC());
        radD.setText(getItem(pageNumberCurrent).getAnswerD());

        pagerNumberDisplay();

        //Image question
        if (getItem(pageNumberCurrent).getImageQuestion() != null){
            imvQuestion.setImageResource(getResources().getIdentifier(getItem(pageNumberCurrent).getImageQuestion()+"","drawable", "com.example.itcode.gplx"));
        }

        //If finish
        if (checkFinish != 0){
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            radD.setClickable(false);

            getCheckAnswer(getItem(pageNumberCurrent).getAnswerTrue().toString(), questionArrayList.get(pageNumberCurrent).getUserAnswer());
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                questionArrayList.get(pageNumberCurrent).choiceID = checkedID;
                questionArrayList.get(pageNumberCurrent).setUserAnswer(getChoiceFromRadioButton(checkedID));
                //ScreenSlideForExamActivity.mPager.setCurrentItem(pageNumberCurrent + 1);
            }
        });
    }

    public void pagerNumberDisplay(){
        if (pageNumberCurrent == 0){ tvNumPrev.setText(""); }else{ tvNumPrev.setText("Câu : " + (pageNumberCurrent)); }
        tvNumCurrent.setText("Câu : " + (pageNumberCurrent +1));
        if (pageNumberCurrent == questionArrayList.size()-1){ tvNumNext.setText(""); }else{ tvNumNext.setText("Câu : " + (pageNumberCurrent + 2)); }
    }

    public static ScreenSildePageForExamFragment screenSilePageFragment(int pageNumber, int checkFinish){
        ScreenSildePageForExamFragment screenSildePageForExamFragment = new ScreenSildePageForExamFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE, pageNumber);
        bundle.putInt(ARG_CHECK_FINISH, checkFinish);
        screenSildePageForExamFragment.setArguments(bundle);
        return screenSildePageForExamFragment;
    }

    //Get value checked radiobutton -> A/B/C/D
    private String getChoiceFromRadioButton(int id){
        if (id == R.id.radA){
            return "A";
        } else if (id == R.id.radB){
            return "B";
        } else if (id == R.id.radC){
            return "C";
        } else if (id == R.id.radD){
            return "D";
        } else {
            return "123";
        }
    }

    //Check answer true
    private void getCheckAnswer(String trueAnswer, String userAnswer){
        if (trueAnswer.equals("A")){
            if (userAnswer != null && userAnswer.equals("A")){
                radA.setBackgroundColor(Color.GREEN);
            }else{
                radA.setBackgroundColor(Color.RED);
            }
        } else if (trueAnswer.equals("B")){
            if (userAnswer != null &&  userAnswer.equals("B")){
                radB.setBackgroundColor(Color.GREEN);
            }else{
                radB.setBackgroundColor(Color.RED);
            }
        } else if (trueAnswer.equals("C")){
            if (userAnswer != null && userAnswer.equals("C")){
                radC.setBackgroundColor(Color.GREEN);
            }else{
                radC.setBackgroundColor(Color.RED);
            }
        } else if (trueAnswer.equals("D")){
            if (userAnswer != null && userAnswer.equals("D")){
                radD.setBackgroundColor(Color.GREEN);
            }else{
                radD.setBackgroundColor(Color.RED);
            }
        }else ;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvNumNext:
                ScreenSlideForExamActivity.mPager.setCurrentItem(pageNumberCurrent + 1);
                break;
            case R.id.tvNumPrev:
                ScreenSlideForExamActivity.mPager.setCurrentItem(pageNumberCurrent - 1);
                break;
        }
    }
}
