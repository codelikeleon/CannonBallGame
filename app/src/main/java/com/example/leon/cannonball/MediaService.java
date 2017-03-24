package com.example.leon.cannonball;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * A class containing a Media Service that will play background music in the app. A service is used
 * so that music can be played in the background of multiple activities.
 */
public class MediaService extends Service {

    private static final String TAG = null;
    MediaPlayer player;

    @Override
    public IBinder onBind( Intent arg0 ) {
        return null;
    }

    /**
     * This method is executed upon creation of the media service. It creates a new Media Player
     * and loads the music stored in the resources folder. Looping is set to true which will cause
     * the track to play again once it has finished.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create( this, R.raw.background_music );
        player.setLooping( true ); // Set looping
        player.setVolume( 100,100 );
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    public int onStartCommand( Intent intent, int flags, int startId ) {
        player.start();
        return START_STICKY;
    }
}
