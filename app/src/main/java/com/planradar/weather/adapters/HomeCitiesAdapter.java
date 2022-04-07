package com.planradar.weather.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.planradar.weather.R;
import com.planradar.weather.models.CityModel;
import com.planradar.weather.ui.home.CitiesItemClick;
import com.planradar.weather.ui.home.CityInfoClick;

import java.util.ArrayList;
import java.util.List;

public class HomeCitiesAdapter extends RecyclerView.Adapter<HomeCitiesAdapter.ViewHolder> {

    List<CityModel> citiesList = new ArrayList<>();
    CitiesItemClick citiesItemClick;
    CityInfoClick cityInfoClick;

    public HomeCitiesAdapter(List<CityModel> citiesList, CitiesItemClick citiesItemClick, CityInfoClick cityInfoClick) {
        this.citiesList = citiesList;
        this.citiesItemClick = citiesItemClick;
        this.cityInfoClick = cityInfoClick;
    }

    @NonNull
    @Override
    public HomeCitiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_home_cities, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCitiesAdapter.ViewHolder holder, int position) {

        holder.cityName.setText(
                citiesList.get(position).getCityName() + ", " +
                        citiesList.get(position).getCountry()
        );
        holder.itemView.setOnClickListener(v->
                citiesItemClick.cityItemClickListener(citiesList.get(position) , holder.itemView)
        );
        holder.info.setOnClickListener(v->
                cityInfoClick.infoClickListener(citiesList.get(position), holder.info)
        );

    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cityName;
        ImageView info;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.tv_city_name);
            info = itemView.findViewById(R.id.iv_info);

        }

    }
}
