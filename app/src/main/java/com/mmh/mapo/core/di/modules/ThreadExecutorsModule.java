package com.mmh.mapo.core.di.modules;


import com.mmh.mapo.core.executors.PostExecutionThread;
import com.mmh.mapo.core.executors.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ThreadExecutorsModule {

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecuter(){
        return new ThreadExecutor.DefaultWorker();
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread (){
        return new PostExecutionThread.DefaultWorker();
    }



}
