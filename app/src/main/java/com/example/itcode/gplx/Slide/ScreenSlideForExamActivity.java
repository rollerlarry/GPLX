package com.example.itcode.gplx.Slide;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.itcode.gplx.Activity.ExamForGroupActivity;
import com.example.itcode.gplx.Activity.ExamResultActivity;
import com.example.itcode.gplx.Activity.HomeActivity;
import com.example.itcode.gplx.Adapter.CheckAnswerAdapter;
import com.example.itcode.gplx.Controller.QuestionControler;
import com.example.itcode.gplx.DAO.DataModel;
import com.example.itcode.gplx.DTO.Question;
import com.example.itcode.gplx.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class ScreenSlideForExamActivity extends FragmentActivity implements View.OnClickListener {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 10;
    public static final String QUESTION_ARRAY_LIST = "questionArrayList";

    private TextView tvTimer, tvFinish, tvBack;
    private ImageView imageView;
    private CounterClass counterClassTimer;


    public static int typeExam;
    private int checkFinish = 0;
    public static int checkDoAgain = 0;
    private LinearLayout bottomSheetLayout;
    private BottomSheetBehavior bottomSheetBehavior;
    private int checkBottomSheet = 0;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    public static ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    //CSDL
    private QuestionControler questionControler;
    private  ArrayList<Question> questionArrayList;
    public static ArrayList<Question> questionArrayListOld = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        questionArrayList = new ArrayList<>();

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());

        //Get exam type
        Intent intent = getIntent();
        typeExam = intent.getIntExtra("typeExam", 1);

        //Start counter
        counterClassTimer = new CounterClass(180*1000, 1000);
        counterClassTimer.start();

        //Bottom sheet
        bottomSheetLayout = findViewById(R.id.bottomSheet);


        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        //hidden bottom sheet
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        imageView = bottomSheetLayout.findViewById(R.id.imvBottomSheet);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                changStatusImageBottomSheet();
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
            }
        });


        //Get questionList
        if (checkDoAgain == 0){
            questionArrayList.clear();
            questionControler = new QuestionControler(this);
            questionArrayList = questionControler.getQuestionList(typeExam);
        }else {
            questionArrayList.addAll(questionArrayListOld);
        }

        //Call find view and event
        findView();
        setEvent();

        //Show check answer
        CheckAnswerAdapter checkAnswerAdapter = new CheckAnswerAdapter(questionArrayList, this);
        GridView gridView = findViewById(R.id.grvQuestion);
        gridView.setAdapter(checkAnswerAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                mPager.setCurrentItem(position);
            }
        });


    }

    public void changStatusImageBottomSheet(){
        checkBottomSheet ++;
        if (checkBottomSheet%2!=0){
            imageView.setImageResource(R.drawable.ic_arrowdown);
        } else{
            imageView.setImageResource(R.drawable.arrowup);
        }
    }



    public void findView(){
        tvTimer = findViewById(R.id.tvTimer);
        tvFinish = findViewById(R.id.tvFinish);
        tvBack = findViewById(R.id.tvBack);
    }

    public void setEvent(){
        tvFinish.setOnClickListener(this);
        tvBack.setOnClickListener(this);
    }

    public ArrayList<Question> getData(){
        return questionArrayList;
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0 && checkFinish == 0) {
            dialogExit();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void dialogExit(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_close);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn đang thi lỡ. Bạn có muốn thoát không ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                counterClassTimer.cancel();
                if (typeExam == 3){
                    startActivity(new Intent(builder.getContext(), HomeActivity.class));
                } else{
                    startActivity(new Intent(builder.getContext(), ExamForGroupActivity.class));
                }
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvFinish:
                finishExam();
                break;
            case R.id.tvBack:
                startActivity(new Intent(this, ExamResultActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            default:
                break;
        }
    }

    public void finishExam(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_list_noti);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn nộp bài không ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishStatus();
            }
        });
        builder.setNegativeButton("Tiếp tục làm bài", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }

    public void notiTimeUp(){
        finishStatus();
    }

    public void finishStatus(){
        counterClassTimer.cancel();
        checkFinish = 1;
        tvFinish.setVisibility(View.GONE);
        tvBack.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, ExamResultActivity.class);


        for (int i = 0; i < questionArrayList.size(); i++) {
            if (questionArrayList.get(i).getUserAnswer() == null){
                questionArrayList.get(i).setUserAnswer("");
            }
        }

        intent.putExtra(QUESTION_ARRAY_LIST, questionArrayList);


        DataModel.QuestionArrayList = questionArrayList;
        DataModel.TypeExam = typeExam;

        intent.putExtra("typeExam", typeExam);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSildePageForExamFragment.screenSilePageFragment(position, checkFinish);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }

    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvTimer.setText(countTime); //SetText cho textview hiện thị thời gian.
            System.out.println(millisUntilFinished);
            if (millisUntilFinished < 2000){
                onFinish();
                notiTimeUp();
            }
        }

        @Override
        public void onFinish() {
            tvTimer.setText("00:00");  //SetText cho textview hiện thị thời gian.
        }
    }
}
