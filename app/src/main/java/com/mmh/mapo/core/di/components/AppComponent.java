package com.mmh.mapo.core.di.components;

import com.mmh.mapo.core.android.App;
import com.mmh.mapo.core.bus.Bus;
import com.mmh.mapo.core.di.modules.ApiModule;
import com.mmh.mapo.core.di.modules.AppModule;
import com.mmh.mapo.core.di.modules.DataModule;
import com.mmh.mapo.core.di.modules.MappersModule;
import com.mmh.mapo.core.di.modules.RepositoriesModule;
import com.mmh.mapo.core.di.modules.ThreadExecutorsModule;
import com.mmh.mapo.core.di.modules.UseCaseModule;
import com.mmh.mapo.ui.di.HomeComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vladimir on 25.07.16.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        DataModule.class,
        ThreadExecutorsModule.class,
        ApiModule.class,
        UseCaseModule.class,
        RepositoriesModule.class,
        MappersModule.class
})
public interface AppComponent {

    HomeComponent plus(HomeComponent.Module module);

    App getApp();
    Bus getBus();


}
