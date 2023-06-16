package com.example.codewizard.ui.bookDetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;

import com.example.codewizard.R;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.ui.bookDetails.fragmets.SharedViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

public class BookDetails extends AppCompatActivity  {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewBookDetails viewBookDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Intent intent = getIntent();
        String libroJson = intent.getStringExtra("libro");
        Gson gson = new Gson();
        Libro libroRecibido = gson.fromJson(libroJson, Libro.class);

        SharedViewModel sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        sharedViewModel.setData(libroRecibido);




System.out.println(libroRecibido.getTitulo());

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);

        viewBookDetails = new ViewBookDetails(this);
        viewPager2.setAdapter(viewBookDetails);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

    }
}
