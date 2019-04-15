package com.example.itcode.gplx.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.itcode.gplx.Activity.ExamForGroupActivity;
import com.example.itcode.gplx.Activity.ExamTipsActivity;
import com.example.itcode.gplx.Activity.LearnPracticeActivity;
import com.example.itcode.gplx.Activity.LearnTheoryActivity;
import com.example.itcode.gplx.Activity.SaveQuestionActivity;
import com.example.itcode.gplx.R;
import com.example.itcode.gplx.Slide.ScreenSlideForExamActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private CardView cvRandomExam, cvExamForGroup, cvLearnTheory, cvExamTips, cvSaveQuestion;
    private Animation animationCardViewRight, animationCardViewLeft;
    private Intent intent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        //Find View
        findView(view);

        //SetEvent
        setEvent();

        //Load Animation
        loadAnimation();

        //Set Animation
        setAnimationLeft();
        setAnimationRight();

        return view;
    }

    public void findView(View view){
        cvRandomExam = view.findViewById(R.id.cvRandomExam);
        cvExamForGroup = view.findViewById(R.id.cvExamForGroup);
        cvLearnTheory = view.findViewById(R.id.cvLearnTheory);
        cvExamTips = view.findViewById(R.id.cvExamTips);
        cvSaveQuestion = view.findViewById(R.id.cvSaveQuestion);
    }

    public void setEvent(){
        cvRandomExam.setOnClickListener(this);
        cvExamForGroup.setOnClickListener(this);
        cvLearnTheory.setOnClickListener(this);
        cvExamTips.setOnClickListener(this);
        cvSaveQuestion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
            case R.id.cvRandomExam:
                notiExam("Bạn đã sẵn sàng để thi ngẫu nhiên chưa ?", 3);
                break;
            case R.id.cvExamForGroup:
                intent = new Intent(getActivity(),ExamForGroupActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.cvLearnTheory:
                intent = new Intent(getActivity(),LearnTheoryActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.cvExamTips:
                intent = new Intent(getActivity(),ExamTipsActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.cvSaveQuestion:
                intent = new Intent(getActivity(),SaveQuestionActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
    }

    public void loadAnimation(){
        animationCardViewRight  = AnimationUtils.loadAnimation(getActivity(), R.anim.card_view_home_right);
        animationCardViewLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.card_view_home_left);
    }

    public void setAnimationRight(){
        cvSaveQuestion.setAnimation(animationCardViewRight);
        cvExamForGroup.setAnimation(animationCardViewRight);
    }

    public void setAnimationLeft(){
        cvRandomExam.setAnimation(animationCardViewLeft);
        cvLearnTheory.setAnimation(animationCardViewLeft);
        cvExamTips.setAnimation(animationCardViewLeft);
    }
    public void notiExam(String mes, final int typeExam){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.bell);
        builder.setTitle("Thông báo");
        builder.setMessage(mes);
        intent = new Intent(getActivity(), ScreenSlideForExamActivity.class);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                intent.putExtra("typeExam", typeExam);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

}
