package com.example.itcode.gplx.Slide;


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

import com.example.itcode.gplx.Controller.QuestionControler;
import com.example.itcode.gplx.DTO.Question;
import com.example.itcode.gplx.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlideForLearnTheoryFragment extends Fragment implements View.OnClickListener {
    private ArrayList<Question> questionArrayList;
    public static final String ARG_PAGE = "page";
    private int pageNumberCurrent;

    private TextView tvQuestion, tvAnswerTrue, tvNumPrev, tvNumCurrent, tvNumNext;
    private ImageView imvQuestion, imvSaveQuestion;

    public ScreenSlideForLearnTheoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_for_learn_theory, container, false);
        tvQuestion = rootView.findViewById(R.id.tvQuestion);
        tvAnswerTrue = rootView.findViewById(R.id.tvAnswerTrue);
        tvNumPrev = rootView.findViewById(R.id.tvNumPrev);
        tvNumCurrent = rootView.findViewById(R.id.tvNumCurrent);
        tvNumNext = rootView.findViewById(R.id.tvNumNext);
        imvQuestion = rootView.findViewById(R.id.imvQuestion);
        imvSaveQuestion = rootView.findViewById(R.id.imvSaveQuestion);

        tvNumNext.setOnClickListener(this);
        tvNumPrev.setOnClickListener(this);
        imvSaveQuestion.setOnClickListener(this);

        QuestionControler questionControler = new QuestionControler(getActivity());
        int checkStateQuestionSave = questionControler.getStateQuestionSave(questionArrayList.get(pageNumberCurrent).getQuestionID());
        if (checkStateQuestionSave == 0){
            imvSaveQuestion.setImageDrawable(getResources().getDrawable(R.drawable.starnot));
        } else {
            imvSaveQuestion.setImageDrawable(getResources().getDrawable(R.drawable.star));
        }
        return rootView;
    }

    public static ScreenSlideForLearnTheoryFragment create(int pageNumber){
        ScreenSlideForLearnTheoryFragment screenSlideForLearnTheoryFragment = new ScreenSlideForLearnTheoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE, pageNumber);
        screenSlideForLearnTheoryFragment.setArguments(bundle);
        return screenSlideForLearnTheoryFragment;
    }

    public Question getItem(int position){
        return questionArrayList.get(position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionArrayList = new ArrayList<>();
        ScreenSlideForLearnTheoryActivity screenSlideForLearnTheoryActivity = (ScreenSlideForLearnTheoryActivity) getActivity();
        questionArrayList = screenSlideForLearnTheoryActivity.getData();
        pageNumberCurrent = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvQuestion.setText(questionArrayList.get(pageNumberCurrent).getTextQuestion());
        pagerNumberDisplay();

        //Image question
        if (getItem(pageNumberCurrent).getImageQuestion() != null){
            imvQuestion.setImageResource(getResources().getIdentifier(getItem(pageNumberCurrent).getImageQuestion()+"","drawable", "com.example.itcode.gplx"));
        }
        tvAnswerTrue.setText("ĐÁP ÁN : " + getCheckAnswer(questionArrayList.get(pageNumberCurrent).getAnswerTrue()));
    }

    //Check answer true
    private String getCheckAnswer(String trueAnswer){
        if (trueAnswer.equals("A")){
            return questionArrayList.get(pageNumberCurrent).getAnswerA();
        } else if (trueAnswer.equals("B")){
            return questionArrayList.get(pageNumberCurrent).getAnswerB();
        } else if (trueAnswer.equals("C")){
            return questionArrayList.get(pageNumberCurrent).getAnswerC();
        } else if (trueAnswer.equals("D")){
            return questionArrayList.get(pageNumberCurrent).getAnswerD();
        }else ;
        return null;
    }

    public void pagerNumberDisplay(){
        if (pageNumberCurrent == 0){ tvNumPrev.setText(""); }else{ tvNumPrev.setText("Câu : " + (pageNumberCurrent)); }
        tvNumCurrent.setText("Câu : " + (pageNumberCurrent +1));
        if (pageNumberCurrent == questionArrayList.size()-1){ tvNumNext.setText(""); }else{ tvNumNext.setText("Câu : " + (pageNumberCurrent + 2)); }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvNumNext:
                ScreenSlideForLearnTheoryActivity.mPager.setCurrentItem(pageNumberCurrent + 1);
                break;
            case R.id.tvNumPrev:
                ScreenSlideForLearnTheoryActivity.mPager.setCurrentItem(pageNumberCurrent - 1);
                break;
            case R.id.imvSaveQuestion:
                QuestionControler questionControler = new QuestionControler(getActivity());
                int checkStateQuestionSave = questionControler.getStateQuestionSave(questionArrayList.get(pageNumberCurrent).getQuestionID());
                String idQuestion = String.valueOf(questionArrayList.get(pageNumberCurrent).getQuestionID());
                if (checkStateQuestionSave == 0){
                    questionControler.setStateQuestionSave(1, idQuestion);
                    imvSaveQuestion.setImageDrawable(getResources().getDrawable(R.drawable.star));
                    Toast.makeText(getActivity(), "Lưu câu hỏi thành công", Toast.LENGTH_SHORT).show();
                } else {
                    questionControler.setStateQuestionSave(0, idQuestion);
                    imvSaveQuestion.setImageDrawable(getResources().getDrawable(R.drawable.starnot));
                    Toast.makeText(getActivity(), "Bỏ lưu câu hỏi thành công", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

}
