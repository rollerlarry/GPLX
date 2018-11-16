package com.example.itcode.gplx.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.itcode.gplx.R;

public class TitleViewChildHolder extends ChildViewHolder{

    public TextView option1, option2;
    public TitleViewChildHolder(View itemView) {
        super(itemView);
        option1 = itemView.findViewById(R.id.option1);
        option2 = itemView.findViewById(R.id.option2);
    }
}
