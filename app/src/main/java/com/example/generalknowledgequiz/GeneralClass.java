package com.example.generalknowledgequiz;

import android.app.Application;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;


public class GeneralClass extends Application {

    boolean media_eletronicloop_state;
    MediaPlayer media_eletronicloop;


    @Override
    public void onCreate() {
        super.onCreate();
        setMedia_eletronicloop_state(false);
        media_eletronicloop = new MediaPlayer();
    }


    public boolean getMedia_eletronicloop_state() {
        return media_eletronicloop_state;
    }

    public void setMedia_eletronicloop_state(boolean media_eletronicloop_state) {
        this.media_eletronicloop_state = media_eletronicloop_state;
    }

    protected void musicPlay() {
        media_eletronicloop.reset();
        media_eletronicloop.setLooping(true);
        try {
            media_eletronicloop.setDataSource(getApplicationContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.songs));
            media_eletronicloop.prepare();
            media_eletronicloop.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void musicStop() {
        media_eletronicloop.stop();
    }


}
