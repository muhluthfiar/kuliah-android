package com.example.myviewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Integer> _result = new MutableLiveData<>();
    public LiveData<Integer> result = _result;

    void calculate(String width, String height, String length) {

        int result = Integer.parseInt(width) * Integer.parseInt(height) * Integer.parseInt(length);
        _result.setValue(result);

    }

}
