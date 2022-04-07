package com.planradar.weather.adapters;

import android.telecom.StatusHints;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.planradar.weather.R;
import com.planradar.weather.models.CityModel;
import com.planradar.weather.ui.home.CityAddedRemovedListener;

import java.util.List;

public class HomeAllCitiesAdapter extends RecyclerView.Adapter<HomeAllCitiesAdapter.ViewHolder> {
    List<CityModel> citiesList ;
    CityAddedRemovedListener cityAddedRemovedListener;

    public HomeAllCitiesAdapter(List<CityModel> citiesList, CityAddedRemovedListener cityAddedRemovedListener) {
        this.citiesList = citiesList;
        this.cityAddedRemovedListener = cityAddedRemovedListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_home_city_selection, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b)
                cityAddedRemovedListener.addCity(citiesList.get(position));
            else
                cityAddedRemovedListener.removeCity(citiesList.get(position));

        });
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.cb_city);
        }
    }
}
