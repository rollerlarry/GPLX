package com.example.itcode.gplx.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.itcode.gplx.R;
import com.example.itcode.gplx.Test.TitleChild;
import com.example.itcode.gplx.Test.TitleParent;
import com.example.itcode.gplx.ViewHolder.TitleParentViewHolder;
import com.example.itcode.gplx.ViewHolder.TitleViewChildHolder;

import java.util.List;

public class MyAdapter extends ExpandableRecyclerAdapter<TitleParentViewHolder, TitleViewChildHolder>{
    LayoutInflater layoutInflater;

    public MyAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public TitleParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.list_tips, viewGroup, false);
        return new TitleParentViewHolder(view);
    }

    @Override
    public TitleViewChildHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.list_child, viewGroup, false);
        return new TitleViewChildHolder(view);
    }

    @Override
    public void onBindParentViewHolder(TitleParentViewHolder titleParentViewHolder, int i, Object o) {
        TitleParent titleParent = (TitleParent) o;
        titleParentViewHolder.textView.setText(titleParent.getTitle());
    }

    @Override
    public void onBindChildViewHolder(TitleViewChildHolder titleViewChildHolder, int i, Object o) {
        TitleChild titleChild = (TitleChild) o;
        titleViewChildHolder.option1.setText(titleChild.getOption1());
        titleViewChildHolder.option2.setText(titleChild.getOption2());
    }
}
