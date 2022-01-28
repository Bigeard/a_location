package com.ynov.a_location.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ynov.a_location.databinding.RowLayoutHousingBinding;

class HousingHolder extends RecyclerView.ViewHolder {
    RowLayoutHousingBinding binding;

    public HousingHolder(@NonNull RowLayoutHousingBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
