package com.planradar.weather.ui.weather;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.planradar.weather.R;
import com.planradar.weather.databinding.FragmentWeatherBinding;
import com.planradar.weather.models.CityModel;
import com.planradar.weather.utils.Constants;
import com.planradar.weather.utils.ProgressBar;

public class WeatherFragment extends Fragment implements Constants {

    private WeatherViewModel mViewModel;


    FragmentWeatherBinding binding;
    Context context;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = getContext();
        CityModel cityModel = getArguments().getParcelable(bundleCity);
        binding.tvCityName.setText(cityModel.getCityName()+", "+cityModel.getCountry());


        ProgressBar.showProgressDialog(context);
        mViewModel.getWeather(cityModel.getCityName());
        binding.back.setOnClickListener(v->getActivity().onBackPressed());
        observers();

        return root;
    }

    private void observers() {

        mViewModel.description.observe(getViewLifecycleOwner(),description-> binding.tvDescription.setText(description));
        mViewModel.humidity.observe(getViewLifecycleOwner(),description-> binding.tvHumidity.setText(description));
        mViewModel.windSpeed.observe(getViewLifecycleOwner(),description-> binding.tvWindSpeed.setText(description));
        mViewModel.temperature.observe(getViewLifecycleOwner(),description-> binding.tvTemperature.setText(description));
        mViewModel.callSucceed.observe(getViewLifecycleOwner(),succeeded->{
            ProgressBar.hideProgressDialog();

            if (!succeeded) {
                Toast.makeText(context,getString(R.string.someThingWrong),Toast.LENGTH_LONG).show();
            }
        });
        mViewModel.onFailure.observe(getViewLifecycleOwner(),failureMessage->{
            ProgressBar.hideProgressDialog();

                Toast.makeText(context,failureMessage,Toast.LENGTH_LONG).show();

        });
    }
}