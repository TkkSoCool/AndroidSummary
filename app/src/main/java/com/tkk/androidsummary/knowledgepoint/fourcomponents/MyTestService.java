package com.tkk.androidsummary.knowledgepoint.fourcomponents;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

/**
 * Created  on 2017/11/8
 * @author 唐开阔
 * @describe 四大主键-Service
**/
public class MyTestService extends IntentService {
    MediaPlayer mediaPlayer;
    String TAG = "MyTestService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyTestService(String name) {
        super(name);
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, ">>>onBind---" );
        return  new MyBinder();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Log.d(TAG, ">>>unbindService---" );
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, ">>>onCreate---" );
        try {
            AssetFileDescriptor fd = getAssets().openFd("a.mp3");
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, ">>>onStartCommand---");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, ">>>onDestroy---" );
    }
    class  MyBinder extends Binder{

    }
}
