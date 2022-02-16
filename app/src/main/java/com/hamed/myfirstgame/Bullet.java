package com.hamed.myfirstgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.hamed.myfirstgame.GameView.screenRatioX;
import static com.hamed.myfirstgame.GameView.screenRatioY;

public class Bullet {
    int x, y, width, height;
    Bitmap bullet;

    Bullet(Resources res){
        bullet = BitmapFactory.decodeResource(res, R.drawable.bullet);
        width = bullet.getWidth();
        height = bullet.getHeight();

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        //bullet = Bitmap.createScaledBitmap(bullet, width, height, false);
    }
}
