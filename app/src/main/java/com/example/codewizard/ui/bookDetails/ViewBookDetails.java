package com.example.codewizard.ui.bookDetails;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.codewizard.ui.bookDetails.fragments.DetailsFragment;
import com.example.codewizard.ui.bookDetails.fragments.ReviewsFragment;
import com.example.codewizard.ui.bookDetails.fragments.SipnosisFragment;

    public class ViewBookDetails extends FragmentStateAdapter {

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
