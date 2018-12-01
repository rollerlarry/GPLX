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

public class PowerPointTipsFragment extends Fragment {
    ListView lvTip;
    ArrayList<Tip> lsTip;
    TipsAdapter tipAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_powerpoint_tips, container, false);
        lvTip = view.findViewById(R.id.lv_powerpointtips);
        TipControler tipControler = new TipControler(getActivity());
        lsTip = tipControler.getTipList(2);
        tipAdapter = new TipsAdapter(getContext(), R.layout.item_tips, lsTip, 2);
        lvTip.setAdapter(tipAdapter);
        return view;
    }
}
