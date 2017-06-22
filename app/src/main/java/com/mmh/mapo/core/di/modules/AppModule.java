package com.mmh.mapo.core.di.modules;


import com.mmh.mapo.core.android.App;
import com.mmh.mapo.core.bus.Bus;
import com.mmh.mapo.core.executors.PostExecutionThread;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vladimir on 29.05.16.
 */
@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    public App provideAppContext(){
        return app;
    }

    @Singleton
    @Provides
    public Bus provideBus(PostExecutionThread postExecutionThread){
        return new Bus(new EventBus(), postExecutionThread);
    }
}
