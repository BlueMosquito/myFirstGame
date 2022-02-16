package com.hamed.myfirstgame;

import static com.hamed.myfirstgame.GameView.screenRatioX;
import static com.hamed.myfirstgame.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Flight {

    public boolean isGoingUp = false;
    public int shooting = 0;
    int x, y, width, height, wingCounter = 0, shootingCounter = 1;
    Bitmap flight1, bullet, bullet2, bullet3, bullet4, bullet5;
    private GameView gameView;

    Flight(GameView gameView,int screenY, Resources res){

        this.gameView = gameView;
        flight1 = BitmapFactory.decodeResource(res, R.drawable.minion1);

        width = flight1.getWidth();
        height = flight1.getHeight();

        width /= 16;
        height /= 16;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        //flight1 = Bitmap.createScaledBitmap(flight1, width, height, false);

        bullet = BitmapFactory.decodeResource(res, R.drawable.bullet);
        bullet2 = BitmapFactory.decodeResource(res, R.drawable.bullet2);
        bullet3 = BitmapFactory.decodeResource(res, R.drawable.bullet3);
        bullet4 = BitmapFactory.decodeResource(res, R.drawable.bullet4);
        bullet5 = BitmapFactory.decodeResource(res, R.drawable.bullet5);


        bullet = Bitmap.createScaledBitmap(bullet, width, height, false);
        bullet2 = Bitmap.createScaledBitmap(bullet2, width, height, false);
        bullet3 = Bitmap.createScaledBitmap(bullet3, width, height, false);
        bullet4 = Bitmap.createScaledBitmap(bullet4, width, height, false);
        bullet5 = Bitmap.createScaledBitmap(bullet5, width, height, false);

        y = screenY / 2;
        x = (int) (64*screenRatioX);

    }

    Bitmap getFlight(){

        if (shooting != 0){
            if (shootingCounter == 1){
                shootingCounter++;
                return bullet;
            }
            if (shootingCounter == 2){
                shootingCounter++;
                return bullet2;
            }
            if (shootingCounter == 3){
                shootingCounter++;
                return bullet3;
            }
            if (shootingCounter == 4){
                shootingCounter++;
                return bullet4;
            }
            shootingCounter = 1;
            shooting--;
            gameView.newBullet();
            return bullet5;
        }

        if (wingCounter == 0){
            wingCounter++;
            return flight1;
        }

        wingCounter--;
        return flight1;
    }
}
