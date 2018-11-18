package com.example.itcode.gplx.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.itcode.gplx.Adapter.MyAdapter;
import com.example.itcode.gplx.R;
import com.example.itcode.gplx.DTO.TitleTipChild;
import com.example.itcode.gplx.DTO.TitleTipCreator;
import com.example.itcode.gplx.DTO.TitleTipParent;

import java.util.ArrayList;
import java.util.List;

public class TheoryTipsFragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        ((MyAdapter)recyclerView.getAdapter()).onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theory_tips, container, false);



        recyclerView = view.findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        MyAdapter myAdapter = new MyAdapter(getActivity(), initData());
        myAdapter.setParentClickableViewAnimationDefaultDuration();
        myAdapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(myAdapter);
        return view;
    }

    private List<ParentObject> initData() {
        TitleTipCreator titleTipCreator = TitleTipCreator.get(getActivity());
        List<TitleTipParent> titleCreators = titleTipCreator.getAll();
        List<ParentObject> parentObjects = new ArrayList<>();
        for (TitleTipParent titleTipParent : titleCreators){
            List<Object> childList = new ArrayList<>();
            childList.add(new TitleTipChild("", "Anyone who reads Old and Middle English literary texts will be familiar with the mid-brown volumes of the EETS, with the symbol of Alfred's jewel embossed on the front cover. Most of the works attributed to King Alfred or to Aelfric, along with some of those by bishop Wulfstan and much anonymous prose and verse from the pre-Conquest period"));
            titleTipParent.setChildObjectList(childList);
            parentObjects.add(titleTipParent);
        }
        return parentObjects;
    }
}
