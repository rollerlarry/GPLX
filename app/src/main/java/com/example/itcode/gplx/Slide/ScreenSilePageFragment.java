package com.example.itcode.gplx.Slide;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.itcode.gplx.DTO.Question;
import com.example.itcode.gplx.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSilePageFragment extends Fragment {

    private ArrayList<Question> questionArrayList;
    public static final String ARG_PAGE = "page";
    private int pageNumberCurrent;
    private TextView tvQuestion, tvNumPrev, tvNumCurrent, tvNumNext;
    private RadioGroup radioGroup;
    private RadioButton radA, radB, radC, radD;


    public ScreenSilePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_sile_page, container, false);

        tvQuestion = rootView.findViewById(R.id.tvQuestion);
        radioGroup = rootView.findViewById(R.id.radGroup);
        radA = rootView.findViewById(R.id.radA);
        radB = rootView.findViewById(R.id.radB);
        radC = rootView.findViewById(R.id.radC);
        radD = rootView.findViewById(R.id.radD);
        tvNumPrev = rootView.findViewById(R.id.tvNumPrev);
        tvNumCurrent = rootView.findViewById(R.id.tvNumCurrent);
        tvNumNext = rootView.findViewById(R.id.tvNumNext);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        questionArrayList = new ArrayList<>();
        ScreenSlideActivity screenSlideActivity = (ScreenSlideActivity) getActivity();
        questionArrayList = screenSlideActivity.getData();

        pageNumberCurrent = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvQuestion.setText(questionArrayList.get(pageNumberCurrent).getTextQuestion()+"");
        radA.setText(questionArrayList.get(pageNumberCurrent).getAnswerA());
        radB.setText(questionArrayList.get(pageNumberCurrent).getAnswerB());
        radC.setText(questionArrayList.get(pageNumberCurrent).getAnswerC());
        radD.setText(questionArrayList.get(pageNumberCurrent).getAnswerD());
        if (pageNumberCurrent == 0){
            tvNumPrev.setText("");
        }else{
            tvNumPrev.setText("Câu : " + (pageNumberCurrent));
        }
        tvNumCurrent.setText("Câu : " + (pageNumberCurrent +1));
        tvNumNext.setText("Câu : " + (pageNumberCurrent + 2));

    }

    public static ScreenSilePageFragment screenSilePageFragment(int pageNumber){
        ScreenSilePageFragment screenSilePageFragment = new ScreenSilePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE, pageNumber);
        screenSilePageFragment.setArguments(bundle);
        return screenSilePageFragment;
    }
}
