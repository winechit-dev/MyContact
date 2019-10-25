package com.example.mynote.Utilites;

import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class CheckEditTexts {

    private int count =0 ;
    private List<String> text;
    private boolean checked;

    public boolean check (TextInputEditText[] editTexts){
        text = new ArrayList<>();


        for (int i=0 ; i<editTexts.length; i++){

            text.add(editTexts[i].getText().toString());
        }

        for (int i=0 ; i<text.size(); i++){

            if (!TextUtils.isEmpty(text.get(i))){
                count++;
            }else {
                editTexts[i].setError("Its required to fill");
                count =0;
            }
        }

        if (count == editTexts.length){
            text.clear();
            count = 0;
            checked = true;
        } else { return checked =false;}


        return checked;
    }

}
