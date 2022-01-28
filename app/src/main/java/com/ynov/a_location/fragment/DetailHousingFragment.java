package com.ynov.a_location.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;
import com.ynov.a_location.R;
import com.ynov.a_location.bo.Housing;
import com.ynov.a_location.databinding.FragmentDetailHousingBinding;

public class DetailHousingFragment extends Fragment {
    FragmentDetailHousingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()),
                R.layout.fragment_detail_housing,
                container,
                false
        );
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Housing housing = DetailHousingFragmentArgs.fromBundle(getArguments()).getHousing();
        binding.setHousing(housing);
        View image = view.findViewById(R.id.imageViewHousing);
        Picasso.get().load("https://flutter-learning.mooo.com" + housing.getIllustrations().getUrl()).resize(120, 60).into(binding.imageViewHousing);
    }
}