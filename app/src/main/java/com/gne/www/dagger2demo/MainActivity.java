package com.gne.www.dagger2demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.gne.www.dagger2demo.dagger.DaggerRetrofitComponent;
import com.gne.www.dagger2demo.dagger.RetrofitComponent;
import com.gne.www.dagger2demo.retrofit.ApiInterface;
import com.gne.www.dagger2demo.retrofit.model.ResponseData;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Inject
    Call<ArrayList<ResponseData>> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        RetrofitComponent retrofitComponent = DaggerRetrofitComponent.create();
        retrofitComponent.inject(MainActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    void getData() {

        call.enqueue(new Callback<ArrayList<ResponseData>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseData>> call, Response<ArrayList<ResponseData>> response) {
                ArrayList<ResponseData> responseDataArrayList = response.body();
                if (responseDataArrayList != null) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(new AdapterRecycler(responseDataArrayList, MainActivity.this));
                } else {
                    showErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseData>> call, Throwable t) {
                showErrorMessage();
            }
        });
    }

    void showErrorMessage() {

        Snackbar.make(findViewById(android.R.id.content), "Something went wrong :(", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getData();
                    }
                }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (call != null)
            call.cancel();
    }
}
