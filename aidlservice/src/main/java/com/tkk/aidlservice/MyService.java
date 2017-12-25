package com.tkk.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.tkk.androidsummary.IMyAidlInterface;
import com.tkk.androidsummary.knowledgepoint.ipc.Person;

/**
 * Created  on 2017/11/8
 *
 * @author 唐开阔
 * @describe aidl跨进程服务端
 */

public class MyService extends Service
{

    public MyService()
    {

    }

    @Override
    public IBinder onBind(Intent intent)
    {

        return new MyBinder();
    }

    class MyBinder extends IMyAidlInterface.Stub
    {

        @Override
        public String getName() throws RemoteException
        {
            return "test";
        }

        @Override
        public Person getPerson() throws RemoteException {
            return new Person(18,"TKK",22);
        }


    }
}

