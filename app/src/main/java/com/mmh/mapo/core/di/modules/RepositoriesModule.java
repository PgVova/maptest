package com.mmh.mapo.core.di.modules;

import com.mmh.mapo.core.android.App;
import com.mmh.mapo.domain.repository.PreferenceRepository;
import com.mmh.mapo.domain.repository.SaveEntityRepository;
import com.mmh.mapo.domain.repository.impl.PreferenceRepositoryImpl;
import com.mmh.mapo.domain.repository.impl.SaveEntityRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;




/**
 * Created by vladimir on 02.06.16.
 */
@Module(includes = {
        DataModule.class,
        MappersModule.class
})
public class RepositoriesModule {

    @Provides
    @Singleton
    PreferenceRepository providePreferenceRepository(App app){
        return new PreferenceRepositoryImpl(app);
    }
    @Provides
    @Singleton
    SaveEntityRepository provideSaveEntityRepo(){
        return new SaveEntityRepositoryImpl();
    }


}
