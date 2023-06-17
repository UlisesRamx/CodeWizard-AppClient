package com.example.codewizard.ui.bookDetails;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.codewizard.api.model.Libro;
import com.example.codewizard.ui.bookDetails.fragmets.DetailsFragment;
import com.example.codewizard.ui.bookDetails.fragmets.ReviewsFragment;
import com.example.codewizard.ui.bookDetails.fragmets.SipnosisFragment;

public class ViewBookDetails extends FragmentStateAdapter {
    Libro libro;

    public ViewBookDetails(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                SipnosisFragment sipnosisFragment = new SipnosisFragment();
                //sipnosisFragment.setLibro(libro);
                return sipnosisFragment;
            case 1:
                DetailsFragment detailsFragment = new DetailsFragment();
                detailsFragment.setLibro(libro);
                return detailsFragment;
            case 2:
                ReviewsFragment reviewsFragment = new ReviewsFragment();
                reviewsFragment.setLibro(libro);
                return reviewsFragment;
            default:
                return new SipnosisFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void setLibro(Libro libro){this.libro = libro;}
}
