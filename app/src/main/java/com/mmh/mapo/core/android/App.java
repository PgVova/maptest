package com.mmh.mapo.core.android;

import android.app.Application;
import android.content.Context;


import com.mmh.mapo.R;
import com.mmh.mapo.core.di.components.AppComponent;
import com.mmh.mapo.core.di.components.DaggerAppComponent;
import com.mmh.mapo.core.di.modules.ApiModule;
import com.mmh.mapo.core.di.modules.AppModule;
import com.mmh.mapo.core.di.modules.DataModule;
import com.mmh.mapo.core.di.modules.MappersModule;
import com.mmh.mapo.core.di.modules.ThreadExecutorsModule;




public class App extends Application{

    private AppComponent appComponent;

    public static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        setupAppComponent();
        setupPicasso();
    }

    private void setupAppComponent(){
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(getString(R.string.baseUrl)))
                .threadExecutorsModule(new ThreadExecutorsModule())
                .dataModule(new DataModule())
                .mappersModule(new MappersModule())
                .build();
    }

    private void setupPicasso(){

    }
}
