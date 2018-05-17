package com.example.kor3a.matchingcardgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Highscore extends AppCompatActivity {

    private TextView highscores;
    private String name1, name2, name3, name4, name5;
    private int score1, score2, score3, score4, score5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        highscores = findViewById(R.id.highscores);

        SharedPreferences sp = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        //Get the passed in score
        int highscore = sp.getInt("Score", 0);
        //Get the passed in Name
        String name = sp.getString("NAME", null);
        score1 = sp.getInt("Score1", 0);
        score2 = sp.getInt("Score2", 0);
        score3 = sp.getInt("Score3", 0);
        score4 = sp.getInt("Score4", 0);
        score5 = sp.getInt("Score5", 0);

        name1 = sp.getString("Name1", null);
        name2 = sp.getString("Name2", null);
        name3 = sp.getString("Name3", null);
        name4 = sp.getString("Name4", null);
        name5 = sp.getString("Name5", null);

        if(highscore > score5){
            score5 = highscore;
            name5 = name;
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("Score5", score5);
            editor.putString("Name5", name5);
            editor.commit();
        }
        if(highscore > score4){
            int temp = score4;
            score4 = highscore;
            score5 = temp;
            String tempName = name4;
            name4 = name;
            name5 = tempName;
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("Score5", score5);
            editor.putInt("Score4", score4);
            editor.putString("Name5", name5);
            editor.putString("Name4", name4);
            editor.commit();
        }
        if(highscore > score3){
            int temp = score3;
            score3 = highscore;
            score4 = temp;
            String tempName = name3;
            name3 = name;
            name4 = tempName;
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("Score4", score4);
            editor.putInt("Score3", score3);
            editor.putString("Name4", name4);
            editor.putString("Name3", name3);
            editor.commit();
        }
        if(highscore > score2){
            int temp = score2;
            score2 = highscore;
            score3 = temp;
            String tempName = name2;
            name2 = name;
            name3 = tempName;
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("Score3", score3);
            editor.putInt("Score2", score2);
            editor.putString("Name3", name3);
            editor.putString("Name2", name2);
            editor.commit();
        }
        if(highscore > score1){
            int temp = score1;
            score1 = highscore;
            score2 = temp;
            String tempName = name1;
            name1 = name;
            name2 = tempName;
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("Score2", score2);
            editor.putInt("Score1", score1);
            editor.putString("Name2", name2);
            editor.putString("Name1", name1);
            editor.commit();
        }

        highscores.setText("1. " + name1 + "..." + score1 + "\n" +
                            "2. " + name2 + "..." + score2 + "\n" +
                "3. " + name3 + "..." + score3 + "\n" +
                "4. " + name4 + "..." + score4 + "\n" +
                "5. " + name5 + "..." + score5);
    }
}
