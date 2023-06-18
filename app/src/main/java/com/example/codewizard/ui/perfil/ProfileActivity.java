package com.example.codewizard.ui.perfil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.api.services.AuthorService;
import com.example.codewizard.api.services.BookService;
import com.example.codewizard.api.services.UserService;
import com.example.codewizard.databinding.ActivityMainMenuBinding;
import com.example.codewizard.databinding.ActivityProfileBinding;
import com.example.codewizard.singleton.CurrentUser;
import com.example.codewizard.ui.bookDetails.SharedViewModel;
import com.example.codewizard.ui.bookDetails.ViewBookDetails;
import com.example.codewizard.ui.broadcast.BroadcastActivity;
import com.example.codewizard.ui.login.LoginActivity;
import com.example.codewizard.ui.mainmenu.MainMenuActivity;
import com.example.codewizard.ui.resenias.ReseniaActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    //private TextView tvUsername = findViewById(R.id.tvUsername);
    private ActivityProfileBinding activityProfileBinding;
    private TextView textViewUserName;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewProfileActivity viewProfileActivity;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //activityProfileBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        //setContentView(activityProfileBinding.getRoot());

        Intent intent = getIntent();
        String usuarioJson = intent.getStringExtra("usuario");
        Gson gson = new Gson();
        Usuario usuarioRecibido = gson.fromJson(usuarioJson, Usuario.class);

        //SharedViewModelProfile sharedViewModelProfile = new ViewModelProvider(this).get(SharedViewModelProfile.class);
        //sharedViewModelProfile.setData(Libros);

        textViewUserName = findViewById(R.id.tvUsername);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.view_pager2);

        textViewUserName.setText(usuarioRecibido.getUsername());
        viewProfileActivity = new ViewProfileActivity(this);
        viewPager2.setAdapter(viewProfileActivity);

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