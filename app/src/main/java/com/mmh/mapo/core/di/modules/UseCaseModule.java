package com.mmh.mapo.core.di.modules;


import dagger.Module;


/**
 * Created by vladimir on 02.06.16.
 */
@Module(includes = {
        RepositoriesModule.class,
        ThreadExecutorsModule.class,
        ApiModule.class
})
public class UseCaseModule {



}
