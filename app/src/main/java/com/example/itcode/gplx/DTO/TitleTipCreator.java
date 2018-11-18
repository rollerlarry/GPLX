package com.example.itcode.gplx.DTO;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class TitleTipCreator {
    static TitleTipCreator _titCreator;
    List<TitleTipParent> _titParents;

    public TitleTipCreator(Context context){
        _titParents = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TitleTipParent titleTipParent = new TitleTipParent(String.format("Tip #%d", i));
            _titParents.add(titleTipParent);
        }
    }
    public static TitleTipCreator get(Context context){
        if (_titCreator == null){
            _titCreator = new TitleTipCreator(context);
        }
        return _titCreator;
    }

    public List<TitleTipParent> getAll() {
        return _titParents;
    }
}
