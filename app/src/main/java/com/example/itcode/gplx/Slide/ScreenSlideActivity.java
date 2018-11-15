package com.example.itcode.gplx.Slide;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itcode.gplx.Activity.ExamForGroupActivity;
import com.example.itcode.gplx.Activity.ExamResultActivity;
import com.example.itcode.gplx.Adapter.CheckAnswerAdapter;
import com.example.itcode.gplx.Controller.QuestionControler;
import com.example.itcode.gplx.DTO.Question;
import com.example.itcode.gplx.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class ScreenSlideActivity extends FragmentActivity implements View.OnClickListener {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;
    public static final String QUESTION_ARRAY_LIST = "questionArrayList";

    private ImageView imvBottomSheet;
    private TextView tvTimer, tvFinish, tvExit;
    private CounterClass counterClassTimer;

    private int typeExam;
    private int checkFinish = 0;

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
    private ArrayList<Question> questionArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());

        //Get exam type
        Intent intent = getIntent();
        typeExam = intent.getIntExtra("typeExam", 1);

        counterClassTimer = new CounterClass(60*1000, 1000);
        counterClassTimer.start();
        questionControler = new QuestionControler(this);
        questionArrayList = new ArrayList<>();

        //Get questionList
//        ArrayList<Question> questionArrayListOld = new ArrayList<>();
//        questionArrayListOld = (ArrayList<Question>) getIntent().getSerializableExtra("questionArrayListOld");
//        if (questionArrayListOld.size() > 0){
//            questionArrayList = questionArrayListOld;
//        }else{
//            questionArrayList = questionControler.getQuestionList(typeExam);
//        }

        questionArrayList = questionControler.getQuestionList(typeExam);

        //Call find view and event
        findView();
        setEvent();

    }

    public void findView(){
        imvBottomSheet = findViewById(R.id.imvBottomSheet);
        tvTimer = findViewById(R.id.tvTimer);
        tvFinish = findViewById(R.id.tvFinish);
        tvExit = findViewById(R.id.tvExit);
    }

    public void setEvent(){
        imvBottomSheet.setOnClickListener(this);
        tvFinish.setOnClickListener(this);
        tvExit.setOnClickListener(this);
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
                backActivity();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }

    public void backActivity(){
        Intent intent1 = new Intent(this, ExamForGroupActivity.class);
        startActivity(intent1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imvBottomSheet:
                View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);
                final BottomSheetDialog dialog = new BottomSheetDialog(this);

//                CheckAnswerAdapter checkAnswerAdapter = new CheckAnswerAdapter(questionArrayList, this);
//                GridView gridView = dialog.findViewById(R.id.gvQuestionList);
//                gridView.setAdapter(checkAnswerAdapter);
//                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        dialog.dismiss();
//                    }
//                });
                dialog.setContentView(view);
                dialog.show();
                break;
            case R.id.tvFinish:
                finishExam();
                break;
            case R.id.tvExit:
                Intent intent1 = new Intent(this, ExamForGroupActivity.class);
                startActivity(intent1);
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
        tvExit.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, ExamResultActivity.class);
        intent.putExtra(QUESTION_ARRAY_LIST, questionArrayList);
        startActivity(intent);
        if (mPager.getCurrentItem() >= 4) mPager.setCurrentItem(mPager.getCurrentItem() - 4);
        else if (mPager.getCurrentItem() <= 4) mPager.setCurrentItem(mPager.getCurrentItem() + 4);
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
            return ScreenSilePageFragment.screenSilePageFragment(position, checkFinish);
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
