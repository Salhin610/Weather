package com.planradar.weather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.planradar.weather.R;
import com.planradar.weather.models.WeatherRecordModel;
import com.planradar.weather.utils.Constants;

import java.util.List;


public class WeatherHistoryAdapter extends RecyclerView.Adapter<WeatherHistoryAdapter.ViewHolder> implements Constants {

    Context context;
    List<WeatherRecordModel> weatherRecordModelList;

    public WeatherHistoryAdapter(Context context, List<WeatherRecordModel> weatherRecordModelList) {
        this.context = context;
        this.weatherRecordModelList = weatherRecordModelList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.descriptionTemperature.setText(weatherRecordModelList.get(position).getDescription()+", "+weatherRecordModelList.get(position).getTemperature());
        holder.date.setText(weatherRecordModelList.get(position).getDate());
        Glide.with(context)
                .asBitmap()
                .load(imagesBaseURL+weatherRecordModelList.get(position).getImageId()+".png")
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return weatherRecordModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionTemperature, date;
        ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            icon = itemView.findViewById(R.id.icon);
            descriptionTemperature = itemView.findViewById(R.id.description_temperature);
        }
    }
}
