package com.gne.www.dagger2demo.dagger;

import android.util.Log;

import com.gne.www.dagger2demo.BuildConfig;
import com.gne.www.dagger2demo.retrofit.ApiInterface;
import com.gne.www.dagger2demo.retrofit.model.ResponseData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class ApiModule {

    @Provides
    ApiInterface getData(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson){
        String url="https://raw.githubusercontent.com/";
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    @Provides
    Gson provideGson(){
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Provides
    OkHttpClient provideOkHttpClient(){
       return new OkHttpClient.Builder()
                .callTimeout(10*1000, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request=chain.request();
                        request.newBuilder().addHeader("headerName","headerValue").build();//add common headers

                        Response response=chain.proceed(request);

                        String resp=response.body().string();
                        if(BuildConfig.DEBUG){
                            Log.d(ApiModule.class.getSimpleName(), "intercept: "+resp);//check or log response
                        }

                        return response.newBuilder()
                                .body(ResponseBody.create(resp, response.body().contentType())).build();
                    }
                }).build();
    }
}
