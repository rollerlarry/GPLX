package com.example.itcode.gplx.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itcode.gplx.DTO.Tip;
import com.example.itcode.gplx.R;
import com.riyagayasen.easyaccordion.AccordionView;

import java.util.ArrayList;
import java.util.List;

public class TipsAdapter extends ArrayAdapter<Tip> {
    private Context context;
    private int resource;
    private ArrayList<Tip> lsTip;
    private int type;
    public TipsAdapter(Context context, int resource, ArrayList<Tip> objects, int type) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.lsTip = objects;
        this.type = type;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tips, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.txtTips = convertView.findViewById(R.id.txt_tips);
            viewHolder.imgTips = convertView.findViewById(R.id.img_tips);
            viewHolder.acdvTips = convertView.findViewById(R.id.acdv_tips);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Tip tip = lsTip.get(position);

        viewHolder.acdvTips.setHeadingString(tip.getTitleTip());
        viewHolder.txtTips.setText(tip.getContentTip());

        Bitmap bitmap = BitmapFactory.decodeByteArray(tip.getImgTip(),0,tip.getImgTip().length);
        viewHolder.imgTips.setImageBitmap(bitmap);


//        viewHolder.acdvTips.setBodyBackGroundColor(0xFFE7EAED);
//        if(type == 0)
//            viewHolder.acdvTips.setHeadingBackGroundColor(0xFF2B579A);
//        else if(type == 1)
//            viewHolder.acdvTips.setHeadingBackGroundColor(0xFF217346);
//        else
//            viewHolder.acdvTips.setHeadingBackGroundColor(0xFFD24726);
//        return convertView;

        viewHolder.acdvTips.setBodyBackGroundColor(0xFFFFFFFF);
        if(type == 0)
            viewHolder.acdvTips.setHeadingBackGroundColor(0xFF83A2ED);
        else if(type == 1)
            viewHolder.acdvTips.setHeadingBackGroundColor(0xFF50B58B);
        else
            viewHolder.acdvTips.setHeadingBackGroundColor(0xFFE48C73);
        return convertView;
    }

    private static class ViewHolder{
        AccordionView acdvTips;
        TextView txtTips;
        ImageView imgTips;
    }
}
