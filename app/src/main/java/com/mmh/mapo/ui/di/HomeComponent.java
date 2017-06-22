package com.mmh.mapo.ui.di;

import com.mmh.mapo.core.di.scope.PerActivity;
import com.mmh.mapo.ui.screens.home.HomeActivity;
import com.mmh.mapo.ui.screens.home.HomePresenter;
import com.mmh.mapo.ui.screens.home.HomePresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 21.06.17.
 */
@PerActivity
@Subcomponent(modules = HomeComponent.Module.class)
public interface HomeComponent {

    void inject(HomeActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        HomePresenter provideHomePresenter(){
            return new HomePresenterImpl();
        }
    }
}
