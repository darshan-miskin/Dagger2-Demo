package com.gne.www.dagger2demo.dagger;

import com.gne.www.dagger2demo.retrofit.ApiInterface;
import com.gne.www.dagger2demo.retrofit.model.ResponseData;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import retrofit2.Call;

@Module(includes = ApiModule.class)
class ApiInterfaceModule {
    @Provides
    Call<ArrayList<ResponseData>> provideCallResponseData(ApiInterface apiInterface){
        return apiInterface.getData();
    }
}
