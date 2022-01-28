package com.ynov.a_location.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ynov.a_location.R;
import com.ynov.a_location.bo.Housing;
import com.ynov.a_location.databinding.RowLayoutHousingBinding;
import com.ynov.a_location.fragment.ListCityFragmentDirections;
import com.ynov.a_location.fragment.ListHousingFragmentDirections;
/*
import com.ynov.a_location.fragment.ListHousingFragmentDirections;
*/

import java.util.ArrayList;


public class HousingAdapter extends RecyclerView.Adapter<HousingHolder> {
    ArrayList<Housing> housingArrayList;

    public HousingAdapter() {
        housingArrayList = new ArrayList<>();
    }

    public void addHousing(Housing m){
        housingArrayList.add(m);
        notifyItemInserted(housingArrayList.size()-1);
    }


    public void setHousingArrayList(ArrayList<Housing> housingArrayList) {
        this.housingArrayList = housingArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HousingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowLayoutHousingBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_layout_housing,
                parent,
                false
        );
        return new HousingHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HousingHolder holder, int position) {
        Housing housing = housingArrayList.get(position);
        ListHousingFragmentDirections.ActionListHousingFragmentToDetailHousingFragment action = ListHousingFragmentDirections.actionListHousingFragmentToDetailHousingFragment(housing);
        holder.itemView.setOnClickListener(
                (view)-> Navigation.findNavController(holder.itemView)
                        .navigate((NavDirections) action)
        );
        holder.binding.setHousing(housing);
        Picasso.get().load("https://flutter-learning.mooo.com" + housing.getIllustrations().getUrl()).resize(120, 60).into(holder.binding.imageViewHousing);
    }

    @Override
    public int getItemCount() {
        return housingArrayList.size();
    }
}
