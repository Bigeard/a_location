package com.ynov.a_location.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ynov.a_location.bo.Housing;

import java.util.ArrayList;

/**
 * Created by quentin for a_location on 07/01/2022.
 */
public class ListFragmentViewModel extends ViewModel {
    MutableLiveData<ArrayList<Housing>> arrayListHousing;

    public MutableLiveData<ArrayList<Housing>> getArrayListHousing(){
        if(arrayListHousing == null){
            this.arrayListHousing = new MutableLiveData<>(new ArrayList<>());
        }
        return this.arrayListHousing;
    }

}
