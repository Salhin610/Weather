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
import com.planradar.weather.DB.citiesTable.CitiesDataBase;
import com.planradar.weather.R;
import com.planradar.weather.adapters.HomeAllCitiesAdapter;
import com.planradar.weather.adapters.HomeCitiesAdapter;
import com.planradar.weather.databinding.FragmentHomeBinding;
import com.planradar.weather.models.CityModel;
import com.planradar.weather.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements CityInfoClick, CitiesItemClick, Constants, CityAddedRemovedListener {

    private HomeViewModel mViewModel;

    private FragmentHomeBinding binding;

    CitiesItemClick citiesItemClick;
    CityInfoClick cityInfoClick;
    CityAddedRemovedListener cityAddedRemovedListener;

    BottomSheetDialog bottomSheetDialog;
    Context context;
    List<CityModel> selectedCityList;
    HomeCitiesAdapter homeCitiesAdapter ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        context = getContext();
        bottomSheetDialog = new BottomSheetDialog(context);
        View root = binding.getRoot();
        citiesItemClick = this;
        cityInfoClick = this;
        cityAddedRemovedListener = this;
        homeCitiesAdapter = new HomeCitiesAdapter(new ArrayList<>(),citiesItemClick,cityInfoClick);
        mViewModel.getAllCities(context);
        mViewModel.retrieveSelectedCitiesFromRoomDB(context);

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
        mViewModel.getAllCities(context);

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
        selectedCityList = citiesList;
            binding.citiesRv.addItemDecoration(new DividerItemDecoration(getContext(),0));
            binding.citiesRv.setLayoutManager(new LinearLayoutManager(getContext()));
            homeCitiesAdapter = new HomeCitiesAdapter(citiesList,citiesItemClick,cityInfoClick);
            binding.citiesRv.setAdapter(homeCitiesAdapter);
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
        cityModel.setSelected(true);
        new Thread(() -> CitiesDataBase.getInstance(context).CitiesDao().
                updateCity(cityModel)).start();
    }

    @Override
    public void removeCity(CityModel cityModel) {
        cityModel.setSelected(false);
        new Thread(() -> CitiesDataBase.getInstance(context).CitiesDao().
                updateCity(cityModel)).start();

    }
}