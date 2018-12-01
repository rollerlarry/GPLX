package com.example.itcode.gplx.Controller;

import android.content.Context;

import com.example.itcode.gplx.DAO.TipDAO;
import com.example.itcode.gplx.DTO.Tip;

import java.util.ArrayList;

public class TipControler {
    private Context context;
    public TipControler(Context context){
        this.context = context;
    }
    public ArrayList<Tip> getTipList(int typeExam){
        TipDAO tipDAO = new TipDAO(context);
        return tipDAO.getTipList(typeExam);
    }
}
