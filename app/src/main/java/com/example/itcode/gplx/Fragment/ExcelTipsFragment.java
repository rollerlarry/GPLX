package com.example.itcode.gplx.Fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.itcode.gplx.Adapter.TipsAdapter;
import com.example.itcode.gplx.Controller.TipControler;
import com.example.itcode.gplx.DTO.Tip;
import com.example.itcode.gplx.R;

import java.util.ArrayList;

public class ExcelTipsFragment extends Fragment {
    ListView lvTip;
    ArrayList<Tip> lsTip;
    TipsAdapter tipAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_excel_tips, container, false);
        lvTip = view.findViewById(R.id.lv_exceltips);
        TipControler tipControler = new TipControler(getActivity());
        lsTip = tipControler.getTipList(1);
        tipAdapter = new TipsAdapter(getContext(), R.layout.item_tips, lsTip, 1);
        lvTip.setAdapter(tipAdapter);
        return view;
    }
}
