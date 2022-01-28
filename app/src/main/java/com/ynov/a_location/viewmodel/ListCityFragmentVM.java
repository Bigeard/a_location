package com.ynov.a_location.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ynov.a_location.bo.City;

import java.util.ArrayList;

/**
 * Created by quentin for a_location on 13/01/2022.
 */
public class ListCityFragmentVM extends ViewModel {
    MutableLiveData<ArrayList<City>> arrayListCity;

    public MutableLiveData<ArrayList<City>> getArrayListCity(){
        if(arrayListCity == null){
            this.arrayListCity = new MutableLiveData<>(new ArrayList<>());
        }
        return this.arrayListCity;
    }
}
