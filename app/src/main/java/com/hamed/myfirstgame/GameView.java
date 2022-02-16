package com.hamed.myfirstgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements Runnable{

    private Thread thread;
    private boolean isPlaying, player2IsPlaying;
    private BackGround backGround1, backGround2;
    private int screenX, screenY;
    private Paint paint;
    private Flight flight, flight2;
    private List<Bullet> bullets;
    public static float screenRatioX, screenRatioY;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        backGround1 = new BackGround(screenX, screenY, getResources());
        backGround2 = new BackGround(screenX, screenY, getResources());

        flight = new Flight(this, screenY, getResources());

        bullets = new ArrayList<>();

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

        List<Bullet> removeBullet = new ArrayList<>();
        for (Bullet bullet : bullets){
            if(bullet.x > screenX){
                removeBullet.add(bullet);
            }
            bullet.x += 50 * screenRatioX;
        }

        for (Bullet bullet : removeBullet){
            bullets.remove(bullet);
        }
    }

    public void draw(){
        if (getHolder().getSurface().isValid()){

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(backGround1.background, backGround1.x, backGround1.y, paint);
            canvas.drawBitmap(backGround2.background, backGround2.x, backGround2.y, paint);

            canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint);

            for (Bullet bullet : bullets){
                canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);
            }

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void sleep(){
        try {
            Thread.sleep(33,5);
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
                if (event.getX() > screenX / 2){
                    flight.shooting++;
                }
                break;
        }

        return true;
    }

    public void newBullet() {
        Bullet firstBullet = new Bullet(getResources());
        firstBullet.x = flight.x + flight.width;
        firstBullet.y = flight.y + (flight.height / 2);
        bullets.add(firstBullet);
    }
}
