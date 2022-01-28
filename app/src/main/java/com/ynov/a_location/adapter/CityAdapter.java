package com.ynov.a_location.adapter;

import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ynov.a_location.R;
import com.ynov.a_location.bo.City;
import com.ynov.a_location.bo.CityImage;
import com.ynov.a_location.databinding.RowLayoutCityBinding;
import com.ynov.a_location.fragment.ListCityFragment;
import com.ynov.a_location.fragment.ListCityFragmentDirections;
import com.ynov.a_location.fragment.ListHousingFragment;

import java.util.ArrayList;
import java.util.List;


public class CityAdapter extends RecyclerView.Adapter<CityViewHolder> {
    ArrayList<City> arrayListCitys;

    public CityAdapter() {
        this.arrayListCitys = new ArrayList<>();
    }

    public void setArrayListCitys(ArrayList<City> arrayListCitys) {
        this.arrayListCitys = arrayListCitys;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowLayoutCityBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_layout_city,
                parent,
                false
        );
        return new CityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City city = arrayListCitys.get(position);
        ListCityFragmentDirections.ActionListCityFragmentToListHousingFragment action = ListCityFragmentDirections.actionListCityFragmentToListHousingFragment(city.getId());
        holder.itemView.setOnClickListener(
                (view)-> Navigation.findNavController(holder.itemView)
                        .navigate((NavDirections) action)
        );
        holder.binding.setCity(city);
        Picasso.get().load("https://flutter-learning.mooo.com" + city.getImage().getUrl()).resize(120, 60).into(holder.binding.imageViewCity);
    }

    @Override
    public int getItemCount() {
        return arrayListCitys.size();
    }

}
