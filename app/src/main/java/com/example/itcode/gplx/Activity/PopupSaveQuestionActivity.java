package com.example.itcode.gplx.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itcode.gplx.Adapter.SaveQuestionAdapter;
import com.example.itcode.gplx.Controller.QuestionControler;
import com.example.itcode.gplx.DAO.DataModel;
import com.example.itcode.gplx.DTO.Question;
import com.example.itcode.gplx.R;

public class PopupSaveQuestionActivity extends Activity {
    private TextView tvQuestion;
    private RadioGroup radioGroup;
    private RadioButton radA, radB, radC, radD;
    private Question question;
    private ImageView imvType;
    private Button btnCancle, btnDelete;
    private int check = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_save_question);

        tvQuestion = findViewById(R.id.tvQuestion);
        radioGroup = findViewById(R.id.radGroup);
        radA = findViewById(R.id.radA);
        radB = findViewById(R.id.radB);
        radC = findViewById(R.id.radC);
        radD = findViewById(R.id.radD);
        imvType = findViewById(R.id.imvType);
        btnCancle = findViewById(R.id.btnCancle);
        btnDelete = findViewById(R.id.btnDelete);
        question = (Question) getIntent().getSerializableExtra("SAVE_QUESTION");

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertMess("Bạn có muốn xóa câu hỏi lưu này không ?");

            }
        });




        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        tvQuestion.setText(question.getTextQuestion());
        radA.setText(question.getAnswerA());
        radB.setText(question.getAnswerB());
        radC.setText(question.getAnswerC());
        radD.setText(question.getAnswerD());

        if (question.getQuestionTypeID() == 0){
            imvType.setImageDrawable(getDrawable(R.drawable.word));
        }else if (question.getQuestionTypeID() == 1){
            imvType.setImageDrawable(getDrawable(R.drawable.excel));
        }else{
            imvType.setImageDrawable(getDrawable(R.drawable.powerpoint));
        }

        getCheckAnswer(question.getAnswerTrue());
    }

    //Check answer true
    private void getCheckAnswer(String trueAnswer){
        if (trueAnswer.equals("A")){
            radA.setChecked(true);
            radA.setBackgroundColor(Color.GREEN);
        } else if (trueAnswer.equals("B")){
            radB.setChecked(true);
            radB.setBackgroundColor(Color.GREEN);
        } else if (trueAnswer.equals("C")){
            radC.setChecked(true);
            radC.setBackgroundColor(Color.GREEN);
        } else if (trueAnswer.equals("D")){
            radD.setChecked(true);
            radD.setBackgroundColor(Color.GREEN);
        }else ;
    }

    public void alertMess(String mess){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.bell);
        builder.setTitle("Thông báo");
        builder.setMessage(mess);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Update cac hoi luu -> 0 -> xoa khoi ds luu
                QuestionControler questionControler = new QuestionControler(getApplicationContext());
                questionControler.deleteSaveQuestion(String.valueOf(question.getQuestionID()));

                Finish();
                DataModel.SaveQuestionAdapter.refresh();
                Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    public void Finish(){
        this.finish();
    }
}
