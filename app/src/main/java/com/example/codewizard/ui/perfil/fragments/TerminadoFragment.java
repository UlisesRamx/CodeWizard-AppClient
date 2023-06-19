package com.example.codewizard.ui.perfil.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.services.AuthorService;
import com.example.codewizard.api.services.BookService;
import com.example.codewizard.singleton.CurrentUser;
import com.example.codewizard.ui.mainmenu.BookUserAdapter;
import com.example.codewizard.ui.perfil.SharedViewModelProfile;

import java.util.ArrayList;

public class TerminadoFragment extends Fragment {
    private ArrayList<Libro> listaLibros;
    private SharedViewModelProfile sharedViewModelProfile;
    private BookUserAdapter bookUserAdapter;
    View view;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModelProfile = new ViewModelProvider(requireActivity()).get(SharedViewModelProfile.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_terminado, container, false);
        sharedViewModelProfile.getData().observe(getViewLifecycleOwner(), libro -> {
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleviewLibraryTerminado);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        bookUserAdapter = new BookUserAdapter(getContext());
        recyclerView.setAdapter(bookUserAdapter);
        bookUserAdapter.notifyDataSetChanged();
        dataInitialize();
    }

    private void dataInitialize(){
        ApiResponse apiResponseBooks = BookService.findLibrary(3,CurrentUser.getInstance().getIdUsuario());
        if (apiResponseBooks.getLibros().size() > 0){
            ApiResponse apiResponseBooksAuthors = new ApiResponse();
            for (Libro libro : apiResponseBooks.getLibros()) {
                apiResponseBooksAuthors = AuthorService.findAutorsBook(libro.getIdLibro());
                libro.setAutores(apiResponseBooksAuthors.getAutores());
            }
            bookUserAdapter.setItems(apiResponseBooks.getLibros());
            System.out.println("Jala LIBROS: " + apiResponseBooks.getLibros().size());
        }else{
            System.out.println("LIBROS Error **: " + apiResponseBooks.getLibros().size());
        }
    }

}