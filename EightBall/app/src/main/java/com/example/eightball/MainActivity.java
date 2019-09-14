package com.example.eightball;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AccelerometerListener{

    int num = 0;
    AnswerGenerator jn = new AnswerGenerator();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView answers = (TextView) findViewById(R.id.answer);
        answers.setText(" ");


    }

    @Override
    public void onAccelerationChanged(float x, float y, float z) {

    }

    @Override
    public void onShake(float force) {
        final TextView answers = (TextView) findViewById(R.id.answer);
        final TextView timers = (TextView) findViewById(R.id.timer);
        final String[] myArr = getResources().getStringArray(R.array.answers);
        if(num == 0) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }
            answers.setText(jn.getAnswers(myArr));
            new CountDownTimer(3000, 1000) {
                public void onTick(long millisUntilFinished) {
                    num++;

                }

                public void onFinish() {
                    num = 0;
                    timers.setText("Ask me a question!");
                    answers.setText(" ");
                }
            }.start();
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getBaseContext(), "onResume Accelerometer Started",
                Toast.LENGTH_SHORT).show();

        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isSupported(this)) {

            //Start Accelerometer Listening
            AccelerometerManager.startListening(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isListening()) {

            //Start Accelerometer Listening
            AccelerometerManager.stopListening();

            Toast.makeText(getBaseContext(), "onStop Accelerometer Stoped",
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Sensor", "Service  distroy");

        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isListening()) {

            //Start Accelerometer Listening
            AccelerometerManager.stopListening();

            Toast.makeText(getBaseContext(), "onDestroy Accelerometer Stoped",
                    Toast.LENGTH_SHORT).show();
        }

    }






        /*final TextView answers = (TextView) findViewById(R.id.answer);
        final TextView timers = (TextView) findViewById(R.id.timer);
        final String[] myArr = getResources().getStringArray(R.array.answers);
        if(num == 0) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }
            answers.setText(jn.getAnswers(myArr));
            new CountDownTimer(3000, 1000) {
                public void onTick(long millisUntilFinished) {
                    num++;

                }

                public void onFinish() {
                    num = 0;
                    timers.setText("Ask me a question!");
                    answers.setText(" ");
                }
            }.start();
        } */


}
