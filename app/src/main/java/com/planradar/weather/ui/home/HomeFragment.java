package com.planradar.weather.ui.home;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.planradar.weather.R;
import com.planradar.weather.adapters.HomeAllCitiesAdapter;
import com.planradar.weather.adapters.HomeCitiesAdapter;
import com.planradar.weather.databinding.FragmentHomeBinding;
import com.planradar.weather.models.CityModel;
import com.planradar.weather.utils.Constants;

public class HomeFragment extends Fragment implements CityInfoClick, CitiesItemClick, Constants, CityAddedRemovedListener {

    private MainViewModel mViewModel;

    private FragmentHomeBinding binding;

    CitiesItemClick citiesItemClick;
    CityInfoClick cityInfoClick;
    CityAddedRemovedListener cityAddedRemovedListener;

    BottomSheetDialog bottomSheetDialog;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        context = getContext();
        bottomSheetDialog = new BottomSheetDialog(context);
        View root = binding.getRoot();
        citiesItemClick = this;
        cityInfoClick = this;
        cityAddedRemovedListener = this;
        mViewModel.getCities();
        actions();
        observers();
        return root;
    }

    private void actions() {
        binding.btnAddRemove.setOnClickListener(v->{
            showAddOrRemovePopUpDialog();
        });
    }

    private void showAddOrRemovePopUpDialog() {
        bottomSheetDialog.setContentView(R.layout.add_remove_city_dialog);
        RecyclerView rvAllCities;

        rvAllCities = bottomSheetDialog.findViewById(R.id.all_cities);
        mViewModel.getAllCities();

        mViewModel.allCities.observe(getViewLifecycleOwner(), allCitiesList->{
            rvAllCities.addItemDecoration(new DividerItemDecoration(getContext(),0));
            rvAllCities.setLayoutManager(new LinearLayoutManager(getContext()));
            rvAllCities.setAdapter(new HomeAllCitiesAdapter(allCitiesList,cityAddedRemovedListener));
        });



        bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        bottomSheetDialog.show();
    }

    private void observers() {
    mViewModel.cities.observe(getViewLifecycleOwner(),citiesList->{
            binding.citiesRv.addItemDecoration(new DividerItemDecoration(getContext(),0));
            binding.citiesRv.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.citiesRv.setAdapter(new HomeCitiesAdapter(citiesList,citiesItemClick, cityInfoClick));
    });
    }


    @Override
    public void infoClickListener(CityModel cityModel, View v) {
        Bundle selectedCityBundle = new Bundle();
        selectedCityBundle.putParcelable(bundleCity, cityModel);

        Navigation.findNavController(v).navigate(
                R.id.action_homeFragment_to_historyFragment,
                selectedCityBundle,
                new NavOptions.Builder()
                        .setEnterAnim(android.R.animator.fade_in)
                        .setExitAnim(android.R.animator.fade_out)
                        .build()
        );
    }

    @Override
    public void cityItemClickListener(CityModel cityModel, View v) {

        Bundle selectedCityBundle = new Bundle();
        selectedCityBundle.putParcelable(bundleCity, cityModel);


        Navigation.findNavController(v).navigate(
                R.id.action_homeFragment_to_weatherFragment,
                selectedCityBundle,
                new NavOptions.Builder()
                        .setEnterAnim(android.R.animator.fade_in)
                        .setExitAnim(android.R.animator.fade_out)
                        .build()
        );
    }

    @Override
    public void addCity(CityModel cityModel) {

        Log.e("AddCity", cityModel.getCityId()+"");
    }

    @Override
    public void removeCity(CityModel cityModel) {
        Log.e("RemoveCity", cityModel.getCityId()+"");

    }
}