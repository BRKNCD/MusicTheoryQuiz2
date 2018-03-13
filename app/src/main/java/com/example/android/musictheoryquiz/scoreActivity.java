package com.example.android.musictheoryquiz;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

public class scoreActivity extends AppCompatActivity {

    // DECLARE VIEWS
    TextView score;
    Button newGame;
    SmileRating smileRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);

        initializeAll();
        smileRating.setIndicator(true);
        int prevPoint = 0;

        // THIS WILL GET INTENT AND THE INT POINTSCOUNTER PASSED FROM PREVIOUS ACTIVITY
        Intent mIntent = getIntent();
        int pointsCounter = mIntent.getIntExtra("pointsCounter", 0);

        animateTextView(prevPoint, pointsCounter, score);
        score.setText("" + pointsCounter);
        animateSmile(pointsCounter);

        newGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(scoreActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    // INITIALIZE ALL VIEWS
    public void initializeAll() {
        score = findViewById(R.id.score);
        newGame = findViewById(R.id.new_game);
        smileRating = findViewById(R.id.smile_rating);
    }

    //INCREMENTAL POINTS ANIMATION
    public void animateTextView(int initialValue, int finalValue, final TextView textview) {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(initialValue, finalValue);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textview.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator.start();
    }

    public void animateSmile(int point) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        if (point <= 0) {
            smileRating.post(new Runnable() {
                @Override
                public void run() {
                    smileRating.setSelectedSmile(BaseRating.TERRIBLE, true);
                }
            });
            Toast toast = Toast.makeText(context, "Your score is: " + point + " " + R.string.terrible, duration);
            toast.show();
        } else {
            if (point > 0 && point <= 20) {
                smileRating.post(new Runnable() {
                    @Override
                    public void run() {
                        smileRating.setSelectedSmile(BaseRating.BAD, true);
                    }
                });
                Toast toast = Toast.makeText(context, "Your score is: " + point + " " + R.string.bad, duration);
                toast.show();
            } else {
                if (point > 20 && point <= 60) {
                    smileRating.post(new Runnable() {
                        @Override
                        public void run() {
                            smileRating.setSelectedSmile(BaseRating.OKAY, true);
                        }
                    });
                    Toast toast = Toast.makeText(context, "Your score is: " + point + " " + R.string.okay, duration);
                    toast.show();
                } else {
                    if (point > 60 && point <= 80) {
                        smileRating.post(new Runnable() {
                            @Override
                            public void run() {
                                smileRating.setSelectedSmile(BaseRating.GOOD, true);
                            }
                        });
                        Toast toast = Toast.makeText(context, "Your score is: " + point + " " + R.string.good, duration);
                        toast.show();
                    } else {
                        if (point > 80) {
                            smileRating.post(new Runnable() {
                                @Override
                                public void run() {
                                    smileRating.setSelectedSmile(BaseRating.GREAT, true);
                                }
                            });
                            Toast toast = Toast.makeText(context, "Your score is: " + point + " " + R.string.great, duration);
                            toast.show();
                        }
                    }
                }
            }
        }
    }
}
