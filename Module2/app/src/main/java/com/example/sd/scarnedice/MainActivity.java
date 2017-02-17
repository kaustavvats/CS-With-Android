package com.example.sd.scarnedice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int finalUserScore;
    private int currentUserScore;
    private int finalCompScore;
    private int currentCompScore;
    private int currentRoll;
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rand = new Random();
        Button hold = (Button)findViewById(R.id.HoldButton);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalUserScore += currentUserScore;
                currentUserScore = 0;
                currentCompScore = handleComputerSide();
                Toast.makeText(getApplicationContext(), "Computer scored: " + Integer.toString(currentCompScore), Toast.LENGTH_SHORT).show();
                finalCompScore += currentCompScore;
                if(finalCompScore >= 100) {
                    Toast.makeText(getApplicationContext(), "YOU LOST!!!", Toast.LENGTH_LONG).show();
                    currentUserScore = 0;
                    currentCompScore = 0;
                    finalUserScore = 0;
                    finalCompScore = 0;
                }
                changeTextView();
            }
        });

        Button roll = (Button)findViewById(R.id.RollButton);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView diceView = (ImageView)findViewById(R.id.diceView);
                currentRoll = rand.nextInt(6)+1;
                if(currentRoll == 1) {
                    diceView.setImageResource(R.drawable.dice1);
                    currentUserScore = 0;
                    currentCompScore = handleComputerSide();
                    Toast.makeText(getApplicationContext(), "Computer scored: " + Integer.toString(currentCompScore), Toast.LENGTH_SHORT).show();
                    finalCompScore += currentCompScore;
                    if(finalCompScore >= 100) {
                        Toast.makeText(getApplicationContext(), "YOU LOST!!!", Toast.LENGTH_LONG).show();
                        currentUserScore = 0;
                        currentCompScore = 0;
                        finalUserScore = 0;
                        finalCompScore = 0;
                        changeTextView();
                        return;
                    }
                } else {
                    currentUserScore += currentRoll;
                    if(currentRoll == 2) {
                        diceView.setImageResource(R.drawable.dice2);
                    } else if(currentRoll == 3) {
                        diceView.setImageResource(R.drawable.dice3);
                    } else if(currentRoll == 4) {
                        diceView.setImageResource(R.drawable.dice4);
                    } else if(currentRoll == 5) {
                        diceView.setImageResource(R.drawable.dice5);
                    } else if(currentRoll == 6) {
                        diceView.setImageResource(R.drawable.dice6);
                    }
                }
                changeTextView(currentRoll);
                if(finalUserScore + currentUserScore > 100) {
                    Toast.makeText(getApplicationContext(), "YOU WON!!!", Toast.LENGTH_LONG).show();
                    currentUserScore = 0;
                    currentCompScore = 0;
                    finalUserScore = 0;
                    finalCompScore = 0;
                    changeTextView();
                }
            }
        });

        Button reset = (Button)findViewById(R.id.ResetButton);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Reset", Toast.LENGTH_SHORT).show();
                currentUserScore = 0;
                currentCompScore = 0;
                finalUserScore = 0;
                finalCompScore = 0;
                changeTextView();
            }
        });
    }

    public void changeTextView() {
        TextView textView = (TextView)findViewById(R.id.scoreTextView);
        textView.setText("Final User Score: " + finalUserScore + " Current User Score: " + currentUserScore + " Current Dice Roll: " + 0 + " Computer Score: " + finalCompScore);
    }

    public void changeTextView(int currentDiceRoll) {
        TextView textView = (TextView)findViewById(R.id.scoreTextView);
        textView.setText("Final User Score: " + finalUserScore + " Current User Score: " + currentUserScore + " Current Dice Roll: " + currentDiceRoll + " Computer Score: " + finalCompScore);
    }

    public int handleComputerSide() {
        int result = 0;
        int current = rand.nextInt(5) + 1;
        while(current != 1 && result < 20) {
            result += current;
            current = rand.nextInt(5) + 1;
        }
        if(current == 1) {
            result = 0;
        }
        return result;
    }
}
