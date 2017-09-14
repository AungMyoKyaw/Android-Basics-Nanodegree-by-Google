package com.aungmyokyaw.www.scorekeeperapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //my vars
    int p1Score = 0;
    int p2Score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void draw(View view){
        p1Score += 1;
        p2Score += 1;
        displayp1(p1Score);
        displayp2(p2Score);
    }

    public void victoryP1(View view){
        p1Score += 1;
        displayp1(p1Score);
    }

    public void victoryP2(View view){
        p2Score += 1;
        displayp2(p2Score);
    }

    public void lossP1(View view){
        p1Score = 0;
        displayp1(p1Score);
    }

    public void lossP2(View view){
        p2Score = 0;
        displayp2(p2Score);
    }

    public void reset(View view){
        p1Score = 0;
        p2Score = 0;
        displayp1(p1Score);
        displayp2(p2Score);
    }

    private void displayp1(int score){
        TextView p1Score = (TextView) findViewById(R.id.p1Score);
        p1Score.setText(""+score);
    }

    private void displayp2(int score){
        TextView p1Score = (TextView) findViewById(R.id.p2Score);
        p1Score.setText(""+score);
    }
}
