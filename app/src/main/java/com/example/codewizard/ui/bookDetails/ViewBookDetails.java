package com.example.codewizard.ui.bookDetails;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.codewizard.api.model.Libro;
import com.example.codewizard.ui.bookDetails.fragments.DetailsFragment;
import com.example.codewizard.ui.bookDetails.fragments.ReviewsFragment;
import com.example.codewizard.ui.bookDetails.fragments.SipnosisFragment;

    public class ViewBookDetails extends FragmentStateAdapter {
    public static SharedViewModel sharedViewModel;

    public ViewBookDetails(FragmentActivity fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new SipnosisFragment();
            case 1:
                return new DetailsFragment();
            case 2:
                return new ReviewsFragment();
            default:
                return new SipnosisFragment();

        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
