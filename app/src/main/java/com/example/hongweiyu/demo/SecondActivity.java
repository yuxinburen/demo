package com.example.hongweiyu.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import de.greenrobot.event.EventBus;

public class SecondActivity extends AppCompatActivity {

    Button btnSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnSecond = ((Button) findViewById(R.id.btn_second));
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new User(""));
            }
        });
    }

    float startX, startY;

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                startX = event.getX();
//                startY = event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float endX = event.getX();
//                float endY = event.getY();
//                float disX = endX - startX;
//                float disY = endY - startY;
//                Toast.makeText(MainActivity.this, "===", Toast.LENGTH_SHORT).show();
//                if( Math.abs(disX) > 0 || Math.abs(disY) > 0){
//                    long postion = listview.getSelectedItemId();
//                    Toast.makeText(MainActivity.this, postion+"", Toast.LENGTH_SHORT).show();
//                }
//                startX = event.getX();
//                startY = event.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
//        return true;
//    }

}
