package com.development.mobile.andromeda.shimmer;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{
    private StartThread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageView Logo = (ImageView) findViewById(R.id.circle);
        ImageView LogoText = (ImageView) findViewById(R.id.text);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo_rotate);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.alpha);

        LinearLayout main_screen = (LinearLayout) findViewById(R.id.activity_start);
        main_screen.setOnClickListener(this);

        thread = new StartThread(this);
        thread.start();

        LogoText.startAnimation(animation1);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        thread.interrupt();
        this.finish();
    }


    class StartThread extends Thread {
        private StartActivity startActivity;

        StartThread(StartActivity startActivity){
            this.startActivity = startActivity;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);

            } catch (InterruptedException ignored) {}

            Intent intent = new Intent(startActivity, RegisterActivity.class);
            startActivity(intent);
            startActivity.finish();
        }
    }
}
