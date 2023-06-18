package com.example.codewizard.ui.bookDetails.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.services.ReviewService;
import com.example.codewizard.singleton.CurrentUser;
import com.example.codewizard.ui.bookDetails.SharedViewModel;
import com.example.codewizard.ui.resenias.ReseniaAdapter;

public class ReviewsFragment extends Fragment {
    private Libro libro;
    private TextView textViewDetalles;
    private SharedViewModel sharedViewModel;
    private ReseniaAdapter reseniaAdapter;
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
        view = inflater.inflate(R.layout.fragment_reviews, container, false);
        sharedViewModel.getData().observe(getViewLifecycleOwner(), libro -> {
            // Actualiza la interfaz de usuario con la información del libro
            loadView(libro.getIdLibro());
        });
        return view;
    }

    public void setLibro(Libro libro){this.libro =  libro;}

    private void loadView(int idLibro){
        reseniaAdapter = new ReseniaAdapter(this.getContext());
        RecyclerView recyclerView = view.findViewById(R.id.rvReviews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(reseniaAdapter);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse = ReviewService.bookReviews(idLibro);
        reseniaAdapter.setItems(apiResponse.getResenias());
    }
}