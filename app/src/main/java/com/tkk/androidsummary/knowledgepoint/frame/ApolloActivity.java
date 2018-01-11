package com.tkk.androidsummary.knowledgepoint.frame;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.lsxiao.apollo.core.Apollo;
import com.lsxiao.apollo.core.annotations.Receive;
import com.lsxiao.apollo.core.contract.ApolloBinder;
import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.androidsummary.knowledgepoint.frame.EventBus.MessageEvent;
import com.tkk.androidsummary.knowledgepoint.frame.EventBus.TestFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created  on 2018/1/3
 * @author 唐开阔
 * 主体流程：
 * 1：通过双重检查锁定模式获取EventBus单例对象
 * 2：注册register
    a):
 */
@KnowledgeInfo(catalog = KnowledgeInfo.FRAME, desc = "Apollo")
@BindLayout(R.layout.activity_event_bus)
/**
 *
 */
public class ApolloActivity extends BaseActivity {
    private ApolloBinder mBinder;
    ArrayList<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @Override
    protected void initView() {
        Apollo.init(AndroidSchedulers.mainThread(), this);

        mBinder = Apollo.bind(this);
        fragments.add(TestFragment.get("fragment1"));
        fragments.add(TestFragment.get("fragment2"));
        fragments.add(TestFragment.get("fragment3"));
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }
    @Receive("event")
    public void tkkshow(String message){
        Toast.makeText(ApolloActivity.this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBinder != null){
            mBinder.unbind();
        }
    }


}
