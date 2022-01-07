package com.hamed.myfirstgame;

import static com.hamed.myfirstgame.GameView.screenRatioX;
import static com.hamed.myfirstgame.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Flight {

    public boolean isGoingUp = false;
    int x, y, width, height, wingCounter = 0;
    Bitmap flight1;

    Flight(int screenY, Resources res){
        flight1 = BitmapFactory.decodeResource(res, R.drawable);

        width = flight1.getWidth();
        height = flight1.getHeight();

        width /= 16;
        height /= 16;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        //flight1 = createScaledBitmap(flight1, width, height, false);

        y = screenY / 2;
        x = (int) (64*screenRatioX);

    }

    Bitmap getFlight(){

        if (wingCounter == 0){
            wingCounter++;
            return flight1;
        }

        wingCounter--;
        return flight1;
    }
}
