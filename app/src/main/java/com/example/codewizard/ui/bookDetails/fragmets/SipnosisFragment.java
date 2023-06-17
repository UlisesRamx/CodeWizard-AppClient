package com.example.codewizard.ui.bookDetails.fragmets;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.codewizard.R;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.ui.bookDetails.BookDetails;

public class SipnosisFragment extends Fragment {

    private Libro libro;
    private TextView sipnosisTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sipnosis, container, false);
        sipnosisTextView = view.findViewById(R.id.txtSipnosis);
        sipnosisTextView.setText("YOLO");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sipnosis, container, false);
    }

    public void setLibro(Libro libro){this.libro =  libro;}


}