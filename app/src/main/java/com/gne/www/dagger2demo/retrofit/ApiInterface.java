package com.gne.www.dagger2demo.retrofit;

import com.gne.www.dagger2demo.retrofit.model.ResponseData;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/darshan-miskin/storage/master/db.json")
    Call<ArrayList<ResponseData>> getData();
}
