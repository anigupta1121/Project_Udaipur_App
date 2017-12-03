package com.project_udaipur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import me.wangyuwei.particleview.ParticleView;

public class SplashActivity extends AppCompatActivity {

    ParticleView mParticleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);}
        catch (Exception e){
            e.printStackTrace();
        }
        setContentView(R.layout.activity_splash);

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("token", "onCreate: "+refreshedToken);

        mParticleView=(ParticleView)findViewById(R.id.particle);
        mParticleView.startAnim();
        mParticleView.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {

                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
