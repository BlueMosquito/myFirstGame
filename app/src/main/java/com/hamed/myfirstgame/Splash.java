package com.hamed.myfirstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {

    private static int ANIM_TIME = 5000;
    Animation topAnim, bottomAnim;
    ImageView img1, img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);
        img1 = findViewById(R.id.imageView);
        img2 = findViewById(R.id.imageView2);

        img1.setAnimation(topAnim);
        img2.setAnimation(bottomAnim);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Splash.this, PlayActivity.class);
            startActivity(intent);
            finish();
        }, ANIM_TIME);

    }
}