package com.example.codewizard.ui.bookDetails.fragmets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.codewizard.api.model.Libro;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Libro> data = new MutableLiveData<>();

    public void setData(Libro newData) {
        data.setValue(newData);
    }

    public LiveData<Libro> getData() {
        return data;
    }
}