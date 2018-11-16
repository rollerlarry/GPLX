package com.example.itcode.gplx.Test;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class TitleCreator {
    static TitleCreator _titCreator;
    List<TitleParent> _titParents;

    public TitleCreator(Context context){
        _titParents = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TitleParent titleParent = new TitleParent(String.format("Tip #%d", i));
            _titParents.add(titleParent);
        }
    }
    public static TitleCreator get(Context context){
        if (_titCreator == null){
            _titCreator = new TitleCreator(context);
        }
        return _titCreator;
    }

    public List<TitleParent> getAll() {
        return _titParents;
    }
}
