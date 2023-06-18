package com.example.codewizard.ui.bookDetails.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.codewizard.R;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.ui.bookDetails.SharedViewModel;

public class DetailsFragment extends Fragment {
    private Libro libro;
    private TextView textViewDetalles;
    private SharedViewModel sharedViewModel;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_details, container, false);
        textViewDetalles = view.findViewById(R.id.Detalles);
        sharedViewModel.getData().observe(getViewLifecycleOwner(), libro -> {
            // Actualiza la interfaz de usuario con la información del libro
            textViewDetalles.setText(libro.getDetails());
        });
        return view;
    }

}