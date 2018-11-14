package com.example.roller.tracnghiem.slide;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.roller.tracnghiem.Entity.CauHoi;
import com.example.roller.tracnghiem.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSilePageFragment extends Fragment {

    private ArrayList<CauHoi> cauHoiArrayList;
    public static final String ARG_PAGE = "page";
    private int numberPageCurrent;

    TextView tvQuestion;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;


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

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cauHoiArrayList = new ArrayList<>();
        ScreenSlideActivity screenSlideActivity = (ScreenSlideActivity) getActivity();
        cauHoiArrayList = screenSlideActivity.getData();

        numberPageCurrent = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //tvQuestion.setText(cauHoiArrayList.get(numberPageCurrent).getCauHoiText()+"");
//        radA.setText(cauHoiArrayList.get(numberPageCurrent).getDapAnA());
//        radB.setText(cauHoiArrayList.get(numberPageCurrent).getDapAnB());
//        radC.setText(cauHoiArrayList.get(numberPageCurrent).getDapAnC());
//        radD.setText(cauHoiArrayList.get(numberPageCurrent).getDapAnD());
//        for (CauHoi cauHoi : cauHoiArrayList){
//            System.out.println(cauHoi.getCauHoiText());
//        }
//
//        System.out.println(numberPageCurrent);
//        System.out.println(cauHoiArrayList.size());
    }

    public static ScreenSilePageFragment screenSilePageFragment(int pageNumber){
        ScreenSilePageFragment screenSilePageFragment = new ScreenSilePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE, pageNumber);
        screenSilePageFragment.setArguments(bundle);
        return screenSilePageFragment;
    }
}
