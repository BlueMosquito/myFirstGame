package com.hamed.myfirstgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BackGround {

    int x = 0, y = 0;
    Bitmap background;

    BackGround (int screenX, int screenY, Resources res){

        background = BitmapFactory.decodeResource(res, R.drawable.game2d);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
    }
}
