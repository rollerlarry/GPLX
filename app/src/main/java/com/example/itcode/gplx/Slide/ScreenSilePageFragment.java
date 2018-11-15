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

public class ScreenSilePageFragment extends Fragment {

    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECK_FINISH = "check_finish";

    private ArrayList<Question> questionArrayList;
    private int pageNumberCurrent;
    private int checkFinish;

    private TextView tvQuestion, tvNumPrev, tvNumCurrent, tvNumNext;
    private RadioGroup radioGroup;
    private RadioButton radA, radB, radC, radD;
    private ImageView imvQuestion;

    public ScreenSilePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_sile_page, container, false);
        findView(rootView);
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
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionArrayList = new ArrayList<>();
        ScreenSlideActivity screenSlideActivity = (ScreenSlideActivity) getActivity();
        questionArrayList = screenSlideActivity.getData();
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

            getCheckAnswer(getItem(pageNumberCurrent).getAnswerTrue().toString());
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                questionArrayList.get(pageNumberCurrent).choiceID = checkedID;
                questionArrayList.get(pageNumberCurrent).setUserAnswer(getChoiceFromRadioButton(checkedID));
                //Toast.makeText(getActivity(), "" + checkedID, Toast.LENGTH_SHORT).show();
//                if (ScreenSlideActivity.mPager.getCurrentItem() == 4){
//
//                }else{
                    //ScreenSlideActivity.mPager.setCurrentItem(ScreenSlideActivity.mPager.getCurrentItem() + 1);
//                }
                //System.out.println(ScreenSlideActivity.mPager);
            }
        });



    }

    public void pagerNumberDisplay(){
        if (pageNumberCurrent == 0){
            tvNumPrev.setText("");
        }else{
            tvNumPrev.setText("Câu : " + (pageNumberCurrent));
        }
        tvNumCurrent.setText("Câu : " + (pageNumberCurrent +1));


        if (pageNumberCurrent == 4){
            tvNumNext.setText("");
        }else{
            tvNumNext.setText("Câu : " + (pageNumberCurrent + 2));
        }

        System.out.println(pageNumberCurrent);
    }

    public static ScreenSilePageFragment screenSilePageFragment(int pageNumber, int checkFinish){
        ScreenSilePageFragment screenSilePageFragment = new ScreenSilePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE, pageNumber);
        bundle.putInt(ARG_CHECK_FINISH, checkFinish);
        screenSilePageFragment.setArguments(bundle);
        return screenSilePageFragment;
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
    private void getCheckAnswer(String answer){
        if (answer.equals("A")){
            radA.setBackgroundColor(Color.RED);
        } else if (answer.equals("B")){
            radB.setBackgroundColor(Color.RED);
        } else if (answer.equals("C")){
            radC.setBackgroundColor(Color.RED);
        } else if (answer.equals("D")){
            radD.setBackgroundColor(Color.RED);
        }else ;
    }
}
