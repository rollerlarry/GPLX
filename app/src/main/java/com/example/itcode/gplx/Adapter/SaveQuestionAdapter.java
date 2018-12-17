package com.example.itcode.gplx.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itcode.gplx.Controller.QuestionControler;
import com.example.itcode.gplx.DTO.Question;
import com.example.itcode.gplx.R;

import java.util.ArrayList;

public class SaveQuestionAdapter extends ArrayAdapter<Question> {
    private Context context;
    private int resource;
    private ArrayList<Question> questionArrayList;
    private Question question;

    public SaveQuestionAdapter(Context context, int resource, ArrayList<Question> questionArrayList){
        super(context, resource, questionArrayList);
        this.context = context;
        this.resource = resource;
        this.questionArrayList = questionArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_save_question, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.imvType = convertView.findViewById(R.id.imvType);
            viewHolder.tvQuestion = convertView.findViewById(R.id.tvQuestion);
            viewHolder.tvType = convertView.findViewById(R.id.tvType);
            viewHolder.tvAnswerTrue = convertView.findViewById(R.id.tvAnswerTrue);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        question = questionArrayList.get(position);

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
        viewHolder.tvAnswerTrue.setText("ĐÁP ÁN : " + getCheckAnswer(question.getAnswerTrue()));

        return convertView;
    }

    private static class ViewHolder{
        private ImageView imvType;
        private TextView tvType, tvQuestion, tvAnswerTrue;
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
