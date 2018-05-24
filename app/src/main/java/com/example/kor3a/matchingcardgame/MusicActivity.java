package com.example.kor3a.matchingcardgame;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MusicActivity extends Service{

    private static final String TAG = null;
    MediaPlayer player;


    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.ps4systemmusic);
        player.setLooping(true);
        player.setVolume(100,100);
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory(){

    }

    public IBinder onUnBind(Intent arg0){
        return null;
    }
}
