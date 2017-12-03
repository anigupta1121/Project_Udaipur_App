package com.project_udaipur;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import devlight.io.library.ArcProgressStackView;

public class MainActivity extends AppCompatActivity {
    private int[] bgColors={R.color.colorAccent,R.color.colorAccent,R.color.colorAccent,R.color.colorPrimaryDark};
    private int[] mStartColors={R.color.colorAccent,R.color.colorAccent,R.color.colorAccent,R.color.colorPrimaryDark};
    private BroadcastReceiver receiver;
    ArcProgressStackView arcProgressStackView;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog =new ProgressDialog(this);
        dialog.setMessage("Loading Details of the crime");
        startService(new Intent(MainActivity.this,MyService.class));
        IntentFilter filter=new IntentFilter("data");
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("token", "onCreate: "+refreshedToken);

        dialog.show();
         arcProgressStackView = (ArcProgressStackView) findViewById(R.id.apsv);
        arcProgressStackView.setClickable(false);
        arcProgressStackView.animateProgress();
        receiver =new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


                try {

                    dialog.dismiss();

                    String gender =intent.getStringExtra("gender");
                    String age = intent.getStringExtra("age");
                    Float fear = Float.parseFloat(intent.getStringExtra("neutral"))-0.2f;
                    if(fear<0){
                        fear=fear*-1;
                    }
                    Float happy =Float.parseFloat(intent.getStringExtra("happiness"));
                    Float sad =Float.parseFloat(intent.getStringExtra("sadness"));
                    ArrayList<ArcProgressStackView.Model> models = new ArrayList<>();
                    models.add(new ArcProgressStackView.Model("Fear", 100*fear, bgColors[0], mStartColors[0]));
                    models.add(new ArcProgressStackView.Model("Sadness", 100*sad, bgColors[0], mStartColors[0]));
                    models.add(new ArcProgressStackView.Model("Happy", 100*happy, bgColors[0], mStartColors[0]));
                    arcProgressStackView.animateProgress();
                    arcProgressStackView.setModels(models);

                    Toast.makeText(MainActivity.this, "Gender:"+gender+" Age:"+age, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        };

        registerReceiver(receiver,filter);


    }

    @Override
    protected void onDestroy() {

        if(receiver!=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
        super.onDestroy();
    }

}
//dDarSG5je8A:APA91bFCu8EIa9TaTiiEUSmMvkA--tOlxzCUf3PSqCv2q2fMvimvmRb8Z3LcybYw4dVGCUGMR-aB7rj80ah718kC-6wB6C0LDz4ymGhwGtQPpEuLbpNovyagJKMMq4rIAYzutwB-EefS