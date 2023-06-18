package com.example.codewizard.ui.bookDetails.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.services.BookService;
import com.example.codewizard.singleton.CurrentUser;
import com.example.codewizard.ui.bookDetails.SharedViewModel;

public class SipnosisFragment extends Fragment {
    private Libro libro;
    private TextView textViewSipnosis, textViewTitle;
    private int idLibro;
    private SharedViewModel sharedViewModel;
    private Button buttonBilioteca;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getContext();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sipnosis, container, false);
        Context context = getContext();
        textViewSipnosis = view.findViewById(R.id.sipnosis);
        textViewTitle = view.findViewById(R.id.tilte);

        sharedViewModel.getData().observe(getViewLifecycleOwner(), libro -> {
            // Actualiza la interfaz de usuario con la información del libro
            textViewTitle.setText(libro.getTitulo());
            textViewSipnosis.setText(libro.getSipnosis());
            idLibro = libro.getIdLibro();
            changeTextButton(idLibro);
            System.out.println("4444444"+idLibro);
        });


        buttonBilioteca  = view.findViewById(R.id.buttonBiblioteca);
        System.out.println("55555555"+idLibro);

        buttonBilioteca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu  popupMenu = new PopupMenu(context,v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_pop,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        ApiResponse apiResponseLibraryChange;
                        Libro libro = new Libro();
                        libro.setIdUsuario(CurrentUser.getInstance().getIdUsuario());
                        libro.setIdLibro(idLibro);
                        switch (item.getItemId()){
                            case R.id.menuItem_Leyendo:
                                apiResponseLibraryChange = BookService.deleteBookLibrary(CurrentUser.getInstance().getIdUsuario(),idLibro);
                                //apiResponseLibraryChange.getCode();
                                System.out.println("Leyendo DELETE: "+apiResponseLibraryChange.getCode());
                                libro.setIdEstado(1);
                                apiResponseLibraryChange = BookService.registerBookLibrary(libro);
                                buttonBilioteca.setText("Leyendo");
                                System.out.println("Leyendo REGISTER: "+apiResponseLibraryChange.getCode());
                                break;
                            case R.id.menuItem_PorLeer:
                                System.out.println("Por Leer");
                                apiResponseLibraryChange = BookService.deleteBookLibrary(CurrentUser.getInstance().getIdUsuario(),idLibro);
                                System.out.println("Por Leer DELETE: "+apiResponseLibraryChange.getCode());
                                libro.setIdEstado(2);
                                apiResponseLibraryChange = BookService.registerBookLibrary(libro);
                                buttonBilioteca.setText("Por Leer");
                                System.out.println("Por Leer REGISTER: "+apiResponseLibraryChange.getCode());
                                break;
                            case R.id.menuItem_Leido:
                                System.out.println("Leido");
                                apiResponseLibraryChange = BookService.deleteBookLibrary(CurrentUser.getInstance().getIdUsuario(),idLibro);
                                //apiResponseLibraryChange.getCode();
                                System.out.println("Leido DELETE: "+apiResponseLibraryChange.getCode());
                                libro.setIdEstado(3);
                                apiResponseLibraryChange = BookService.registerBookLibrary(libro);
                                buttonBilioteca.setText("Leido");
                                System.out.println("Leido REGISTER: "+apiResponseLibraryChange.getCode());
                                break;
                            case R.id.menuItem_SinBiblioteca:
                                System.out.println("Sin  Bbilioteca");
                                apiResponseLibraryChange = BookService.deleteBookLibrary(CurrentUser.getInstance().getIdUsuario(),idLibro);
                                //apiResponseLibraryChange.getCode();
                                buttonBilioteca.setText("Sin  Biblioteca");
                                System.out.println("Leido DELETE: "+apiResponseLibraryChange.getCode());
                                break;
                            default:
                                System.out.println("Default");
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        return view;
    }

    private void changeTextButton(int idLibro){
        System.out.println("111111 text user: "+CurrentUser.getInstance().getIdUsuario()+" text idLibro: "+idLibro);

        ApiResponse apiResponseLibrary= BookService.findLibraryByUser(CurrentUser.getInstance().getIdUsuario(),idLibro);
        System.out.println("2222222 text user: "+CurrentUser.getInstance().getIdUsuario()+" text idLibro: "+idLibro);

        System.out.println("333333 text Option: "+apiResponseLibrary.getLibro().getIdEstado());
        int idEstado = 0;
        if(apiResponseLibrary.getLibros().size()>0){
            idEstado = apiResponseLibrary.getLibros().get(0).getIdEstado();
        }
        switch (idEstado){
            case 1:
                buttonBilioteca.setText("Leyendo");
                System.out.println("Button Text Leyendo");
                break;
            case 2:
                buttonBilioteca.setText("Por Leer");
                System.out.println("Button Text Por Leer");
                break;
            case 3:
                buttonBilioteca.setText("Leido");
                System.out.println("Button Text Leido");
                break;
            case 0:
                buttonBilioteca.setText("Sin  Biblioteca");
                System.out.println("Button Text Sin  Bbilioteca");
                break;
            default:
                buttonBilioteca.setText("Sin  Biblioteca");
                System.out.println("Button Text default");
                break;
        }
    }

}