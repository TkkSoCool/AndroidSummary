package com.tkk.androidsummary.knowledgepoint.ipc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import com.tkk.androidsummary.IMyAidlInterface;
import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created  on 2017/11/8
 * @author 唐开阔
 * @describe AIDL跨进程通信
 */
@BindLayout(R.layout.activity_aidl)
@KnowledgeInfo(catalog = KnowledgeInfo.IPC, desc = "AIDL通信")
public class AidlActivity extends BaseActivity {
    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void initView() {

    }
    @OnClick(R.id.btm_bind)
    public void onBtmBindClicked() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.tkk.aidlservice","com.tkk.aidlservice.MyService"));
        bindService(intent, new ServiceConnection()
        {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service)
            {

                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name)
            {

            }
        }, BIND_AUTO_CREATE);
    }

    @OnClick(R.id.btm_getData)
    public void onBtmGetDataClicked() {
        if (iMyAidlInterface == null){

        }else {
            try {
                Toast.makeText(AidlActivity.this, iMyAidlInterface.getPerson().getName(), Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
