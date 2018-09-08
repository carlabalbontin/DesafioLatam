package com.desafiolatam.desafioface.views.splash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.desafiolatam.desafioface.R;
import com.desafiolatam.desafioface.background.RecentUsersService;
import com.desafiolatam.desafioface.views.MainActivity;
import com.desafiolatam.desafioface.views.login.LoginActivity;


public class SplashActivity extends AppCompatActivity implements LoginCallback {

    private IntentFilter intentFilter;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        View view = findViewById(R.id.root);
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);



        intentFilter = new IntentFilter();
        intentFilter.addAction(RecentUsersService.USERS_FINISHED);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.d("SPLASH ACTION", action);
                // Al usar el método equals, el primer parámetro debe existir, por eso ponemos la contstante
                // Si el primer parámetro es null, se cae la aplicación
                if(RecentUsersService.USERS_FINISHED.equals(action)){
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
            }
        };

        new LoginValidation(this).init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void signed() {
        RecentUsersService.startActionRecentUsers(this);
    }

    @Override
    public void signUp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 1200);
    }
}
