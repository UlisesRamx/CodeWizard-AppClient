package com.example.codewizard.ui.bookDetails.fragments;

import android.content.Intent;
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
import com.example.codewizard.ui.bookDetails.BookDetails;
import com.example.codewizard.ui.bookDetails.SharedViewModel;
import com.example.codewizard.ui.deleteuser.DeleteUser;
import com.example.codewizard.ui.mainmenu.MainMenuActivity;
import com.example.codewizard.ui.makereview.MakeReviewActivity;
import com.example.codewizard.ui.resenias.ReseniaAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

public class ReviewsFragment extends Fragment {
    private Libro libro;
    private TextView textViewDetalles;
    private SharedViewModel sharedViewModel;
    private ReseniaAdapter reseniaAdapter;
    View view;
    private FloatingActionButton floatingActionButtonReseniar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reviews, container, false);
        floatingActionButtonReseniar = view.findViewById(R.id.floatingActionButtonReseniar);
        floatingActionButtonReseniar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), MakeReviewActivity.class);
                Gson gson = new Gson();
                String libroJson = gson.toJson(libro);
                intent.putExtra("libro", libroJson);
                startActivity(intent);
            }
        });

        sharedViewModel.getData().observe(getViewLifecycleOwner(), libro -> {
            // Actualiza la interfaz de usuario con la información del libro
            loadView(libro.getIdLibro());
            this.libro = libro;
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