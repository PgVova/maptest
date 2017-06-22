package com.mmh.mapo.core.android;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.mmh.mapo.R;
import com.mmh.mapo.core.bus.EventBus;
import com.mmh.mapo.core.bus.IgnoreEvent;
import com.mmh.mapo.core.mvp.Presenter;
import com.mmh.mapo.core.mvp.ViewMvpFragment;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;


/**
 * Created by vladimir on 29.05.16.
 */
public class BaseActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getApp(this).getAppComponent().getBus().register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppForegroundStateManager.getInstance().onActivityVisible(this);
    }

    @Override
    protected void onStop() {
        AppForegroundStateManager.getInstance().onActivityNotVisible(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getApp(this).getAppComponent().getBus().unRegister(this);
    }

    protected <F extends ViewMvpFragment> F replaceFragment(Presenter presenter, F fragment, MvpFragmentTransactionCallback fragmentTransactionCallback){
        Fragment oldFragment = getSupportFragmentManager().findFragmentByTag(fragment.getClass().getName());
        if (oldFragment == null){
            oldFragment = fragment;
            FragmentTransaction fragmentTransaction = replaceFragment(fragment, fragment.getClass().getName());
            fragmentTransactionCallback.afterReplace(fragmentTransaction);
            fragmentTransaction.commit();
        }
        ((ViewMvpFragment)oldFragment).attachPresenter(presenter);
        return (F)oldFragment;
    }

    protected <F extends ViewMvpFragment> F replaceFragment(Presenter presenter, F fragment){
        return replaceFragment(presenter, fragment, fragmentTransaction -> {});
    }

    protected <T> Optional<T> findFragment(Class<T> tClass){
        return new Optional<>((T) getSupportFragmentManager().findFragmentByTag(tClass.getName()));
    }

    protected FragmentTransaction replaceFragment(Fragment fragment, String tag){
        return getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, tag);
    }

    @Subscribe
    @EventBus
    public void onEvent(IgnoreEvent ignore){

    }

    public boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public interface MvpFragmentTransactionCallback {
        void afterReplace(FragmentTransaction fragmentTransaction);
    }

    public void setupToolbar(Toolbar toolbar, String primaryText, android.view.View.OnClickListener onLeftClickListener,android.view.View.OnClickListener onRightClickListener){
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView text = ButterKnife.findById(toolbar, R.id.toolbar_text);
        ImageView rightIcon = ButterKnife.findById(toolbar, R.id.toolbar_settings);
        ImageView leftIcon = ButterKnife.findById(toolbar, R.id.toolbar_settings);
        text.setText(primaryText);
        leftIcon.setOnClickListener(onLeftClickListener);
        rightIcon.setOnClickListener(onRightClickListener);


    }
}
