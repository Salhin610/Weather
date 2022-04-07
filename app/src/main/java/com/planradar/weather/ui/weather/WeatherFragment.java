package com.planradar.weather.ui.weather;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planradar.weather.databinding.FragmentWeatherBinding;
import com.planradar.weather.models.CityModel;
import com.planradar.weather.utils.Constants;

public class WeatherFragment extends Fragment implements Constants {

    private WeatherViewModel mViewModel;


    FragmentWeatherBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        CityModel cityModel = getArguments().getParcelable(bundleCity);

        mViewModel.getWeather();
        binding.back.setOnClickListener(v->getActivity().onBackPressed());
        observers();
        return root;
    }

    private void observers() {

    }
}