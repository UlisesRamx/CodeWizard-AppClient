package com.example.codewizard.ui.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.codewizard.api.model.Libro;

import java.util.List;

public class SharedViewModelProfile extends ViewModel {
    private MutableLiveData<List<List<Libro>>> data = new MutableLiveData<>();

    public void setData(List<List<Libro>> newData) {
        data.setValue(newData);
    }

    public LiveData<List<List<Libro>>> getData() {
        return data;
    }
}
