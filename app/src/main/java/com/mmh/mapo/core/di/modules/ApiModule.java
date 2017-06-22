package com.mmh.mapo.core.di.modules;

import com.google.gson.Gson;
import com.mmh.mapo.core.android.AuthRetrofit;
import com.mmh.mapo.domain.mappers.ErrorMapper;
import com.mmh.mapo.domain.repository.PreferenceRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApiModule {
    private String baseUrl;


    public ApiModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    @Named("SimpleOkHttp")
    OkHttpClient provideSimpleOkHttpClient(){
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .followRedirects(false)
                .followSslRedirects(false)
                .addInterceptor(interceptor)
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    @Named("AuthOkHttp")
    OkHttpClient provideAuthOkHttpClient(PreferenceRepository preferenceRepository){
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .followRedirects(false)
                .followSslRedirects(false)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(chain -> {
                    Request request = chain.request();
//                    String token = preferenceRepository.getAccessToken();
                    String token = "";
//                    if (StringUtils.isNullEmpty(token)){
//                        throw new IOException("401 Not auth");
//                    }

                    request = request.newBuilder()
                                .addHeader("Authorization", token)
                            .build();

                    return chain.proceed(request);
                })
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    Gson provideGson(){
        Gson gson = new Gson();
        return gson;
    }

    @Provides
    @Singleton
    ErrorMapper provideErrorMapper(Gson gson){
        return new ErrorMapper(gson);
    }


    @Singleton
    @Provides
    Retrofit provideSimpleRetrofit(@Named("SimpleOkHttp") OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    @Singleton
    @Provides
    AuthRetrofit provideAuthRetrofit(@Named("AuthOkHttp") OkHttpClient okHttpClient, Gson gson){
        return new AuthRetrofit( new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build());

    }
}
