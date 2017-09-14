package com.aungmyokyaw.www.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private int score = 0;
    private boolean submitted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submit(View view){
        scoreCalc();
        if(!submitted){
            displayToast("Your score is "+score);
            submitted = true;
        }
    }

    public void reset(View view){
        score = 0;
        clearCheck();
        displayToast("Successfully Reset!");
    }

    private void displayToast(String message){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast =  Toast.makeText(context,message,duration);
        toast.show();
    }

    public void calcCheck(View view){
        CheckBox check1 = (CheckBox) findViewById(R.id.qC1);
        CheckBox check2 = (CheckBox) findViewById(R.id.qC2);
        if(check1.isChecked() && check2.isChecked()){
            score += 1;
        }
    }

    private void scoreCalc(){
        if(submitted){
            score = 0;
            displayToast("Please Reset !");
        } else {
            calcQradio1();
            calcQradio2();
            calcQ3();
        }
    }

    private void calcQradio1(){
        RadioGroup qRadio1 = (RadioGroup) findViewById(R.id.qR1);
        int checkedId = qRadio1.getCheckedRadioButtonId();
        if(checkedId != -1){
            RadioButton button = (RadioButton) findViewById(checkedId);
            String text = (String) button.getText();
            if(text.equals("true")){
                score += 1;
            }
        }
    }

    private void calcQradio2(){
        RadioGroup qRadio2 = (RadioGroup) findViewById(R.id.qR2);
        int checkedId = qRadio2.getCheckedRadioButtonId();
        if(checkedId != -1){
            RadioButton button = (RadioButton) findViewById(checkedId);
            String text = (String) button.getText();
            if(text.equals("toString()")){
                score += 1;
            }
        }
    }

    private void calcQ3(){
        EditText textBox = (EditText) findViewById(R.id.q3);
        String text = textBox.getText().toString();
        if(text.equals("push()")){
            score += 1;
        }
    }

    private void clearCheck(){
        RadioGroup qRadio1 = (RadioGroup) findViewById(R.id.qR1);
        qRadio1.clearCheck();
        RadioGroup qRadio2 = (RadioGroup) findViewById(R.id.qR2);
        qRadio2.clearCheck();
        CheckBox check1 = (CheckBox) findViewById(R.id.qC1);
        check1.setChecked(false);
        CheckBox check2 = (CheckBox) findViewById(R.id.qC2);
        check2.setChecked(false);
        EditText textBox = (EditText) findViewById(R.id.q3);
        textBox.setText("");
        submitted = false;
    }
}
