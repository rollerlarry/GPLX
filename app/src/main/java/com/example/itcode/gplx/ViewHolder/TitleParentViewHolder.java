package com.example.itcode.gplx.ViewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.itcode.gplx.R;

public class TitleParentViewHolder extends ParentViewHolder{

    public TextView textView;
    public ImageButton imageButton;

    public TitleParentViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.parentTitle);
        imageButton = itemView.findViewById(R.id.expanArrow);
    }
}
