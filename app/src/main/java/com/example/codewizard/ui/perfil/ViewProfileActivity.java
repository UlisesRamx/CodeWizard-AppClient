package com.example.codewizard.ui.perfil;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.codewizard.ui.perfil.fragments.LeyendoFragment;
import com.example.codewizard.ui.perfil.fragments.PorLeerFragment;
import com.example.codewizard.ui.perfil.fragments.TerminadoFragment;

public class ViewProfileActivity extends FragmentStateAdapter {

    public ViewProfileActivity(FragmentActivity fragmentManager) {
        super(fragmentManager);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new PorLeerFragment();
            case 1:
                return new LeyendoFragment();
            case 2:
                return new TerminadoFragment();
            default:
                return new LeyendoFragment();

        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
