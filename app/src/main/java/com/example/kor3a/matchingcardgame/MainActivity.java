package com.example.kor3a.matchingcardgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private Button startGame;
    private Button highscoreActivity;
    private ToggleButton musicToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGame = findViewById(R.id.startButton);
        highscoreActivity = findViewById(R.id.highscoreButton);
        musicToggle = findViewById(R.id.musictoggle);
        startGame.setText("Start Game");
        highscoreActivity.setText("Highscores");

        //For music on and off
        musicToggle.setTextOff("Music Off");
        musicToggle.setTextOn("Music On");
//        final Intent musicStuff = new Intent(MainMenu.this, BGMService.class);

//        musicToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked){
//                if(isChecked){
//                    //music on
//                    startService(musicStuff);
//                }else{
//                    //music off
//                    stopService(musicStuff);
//                }
//            }
//        });
    }

    public void startGame(View view){
        Intent gameIntent = new Intent(MainActivity.this, GameDifficultyActivity.class);
        startActivity(gameIntent);
    }

    public void showHighscores(View view){
        Intent hsIntent = new Intent(MainActivity.this, Highscore.class);
        startActivity(hsIntent);
    }
}
