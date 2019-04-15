package com.example.itcode.gplx.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itcode.gplx.Activity.PopupSaveQuestionActivity;
import com.example.itcode.gplx.Controller.QuestionControler;
import com.example.itcode.gplx.DAO.DataModel;
import com.example.itcode.gplx.DTO.Question;
import com.example.itcode.gplx.R;

import java.util.ArrayList;

public class SaveQuestionAdapter extends ArrayAdapter<Question>{
    private Context context;
    private int resource;
    private static ArrayList<Question> questionArrayList;
    private Question question;
    private static int _position;

    public SaveQuestionAdapter(Context context, int resource, ArrayList<Question> questionArrayList){
        super(context, resource, questionArrayList);
        this.context = context;
        this.resource = resource;
        this.questionArrayList = questionArrayList;
        DataModel.SaveQuestionAdapter = this;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_save_question, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.imvType = convertView.findViewById(R.id.imvType);
            viewHolder.tvQuestion = convertView.findViewById(R.id.tvQuestion);
            viewHolder.tvType = convertView.findViewById(R.id.tvType);
            viewHolder.cardView = convertView.findViewById(R.id.cardView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //Lay vi tri cau trong cai mang
        question = questionArrayList.get(position);





        //Kiem tra loai cau hoi
        if (question.getQuestionTypeID() == 0){
            viewHolder.imvType.setImageDrawable(getContext().getDrawable(R.drawable.word));
            viewHolder.tvType.setText("Word");
        } else if (question.getQuestionTypeID() == 1){
            viewHolder.imvType.setImageDrawable(getContext().getDrawable(R.drawable.excel));
            viewHolder.tvType.setText("Excel");
        } else {
            viewHolder.imvType.setImageDrawable(getContext().getDrawable(R.drawable.powerpoint));
            viewHolder.tvType.setText("PowerPoint");
        }



        viewHolder.tvQuestion.setText(question.getTextQuestion());

        //Su kien xoa cau hoi luu
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getContext(), PopupSaveQuestionActivity.class);
            intent.putExtra("SAVE_QUESTION", questionArrayList.get(position));
            getContext().startActivity(intent);

            _position = position;
            notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private static class ViewHolder{
        private ImageView imvType;
        private TextView tvType, tvQuestion, tvAnswerTrue;
        private CardView cardView;
    }

    public boolean refresh(){
        questionArrayList.remove(questionArrayList.get(_position));
        notifyDataSetChanged();
        return true;
    }

    //get answer true
    private String getCheckAnswer(String trueAnswer){
        if (trueAnswer.equals("A")){
            return question.getAnswerA();
        } else if (trueAnswer.equals("B")){
            return question.getAnswerB();
        } else if (trueAnswer.equals("C")){
            return question.getAnswerC();
        } else if (trueAnswer.equals("D")){
            return question.getAnswerD();
        }else ;
        return null;
    }
}
