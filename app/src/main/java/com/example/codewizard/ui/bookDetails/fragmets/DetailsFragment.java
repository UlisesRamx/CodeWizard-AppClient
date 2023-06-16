package com.example.codewizard.ui.bookDetails.fragmets;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codewizard.R;
import com.example.codewizard.api.model.Libro;

public class DetailsFragment extends Fragment {
    private Libro libro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    public void setLibro(Libro libro){this.libro =  libro;}

}