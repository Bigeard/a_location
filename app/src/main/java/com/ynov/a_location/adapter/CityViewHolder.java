package com.ynov.a_location.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.ynov.a_location.databinding.RowLayoutCityBinding;

public class CityViewHolder extends RecyclerView.ViewHolder {
    RowLayoutCityBinding binding;
    public CityViewHolder(RowLayoutCityBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
