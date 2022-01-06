package com.hamed.myfirstgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable{

    private Thread thread;
    private boolean isPlaying;
    private BackGround backGround1, backGround2;
    private int screenX, screenY;
    private Paint paint;
    private Flight flight;
    public static float screenRatioX, screenRatioY;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        backGround1 = new BackGround(screenX, screenY, getResources());
        backGround2 = new BackGround(screenX, screenY, getResources());

        flight = new Flight(screenY, getResources());

        backGround2.x = screenX;

        paint = new Paint();
    }

    @Override
    public void run() {
        while (isPlaying){

            update();
            draw();
            sleep();
        }
    }

    public void update(){
        backGround1.x -= 10 * screenRatioX;
        backGround2.x -= 10 * screenRatioX;

        if (backGround1.x + backGround1.background.getWidth() < 0){
            backGround1.x = screenX;
        }

        if (backGround2.x + backGround2.background.getWidth() < 0){
            backGround2.x = screenX;
        }

        if (flight.isGoingUp)
            flight.y -= 30 * screenRatioY;
        else flight.y += 30 * screenRatioY;

        if (flight.y < 0)
            flight.y = 0;

        if (flight.y >= screenY - flight.height)
            flight.y = screenY - flight.height;
    }

    public void draw(){
        if (getHolder().getSurface().isValid()){

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(backGround1.background, backGround1.x, backGround1.y, paint);
            canvas.drawBitmap(backGround2.background, backGround2.x, backGround2.y, paint);

            canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void sleep(){
        //60fs
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume(){

        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }

    public void pause(){

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < screenX / 2){
                    flight.isGoingUp = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                flight.isGoingUp = false;
                break;
        }

        return true;
    }

}
