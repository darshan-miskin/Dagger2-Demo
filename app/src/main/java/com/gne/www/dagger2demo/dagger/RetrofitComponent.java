package com.gne.www.dagger2demo.dagger;

import com.gne.www.dagger2demo.MainActivity;
import com.gne.www.dagger2demo.retrofit.ApiInterface;

import dagger.Component;

@Component(modules = ApiInterfaceModule.class)
public interface RetrofitComponent {

    void inject(MainActivity mainActivity);
}
