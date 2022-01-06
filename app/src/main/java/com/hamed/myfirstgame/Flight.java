package com.hamed.myfirstgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Flight {

    int x, y, width, height;
    Bitmap flight1, flight2;

    Flight(int screenY, Resources res){
        flight1 = BitmapFactory.decodeResource(res, R.id);
    }
}
