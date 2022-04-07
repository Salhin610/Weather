package com.planradar.weather.ui.history;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planradar.weather.adapters.WeatherHistoryAdapter;
import com.planradar.weather.databinding.FragmentHistoryBinding;
import com.planradar.weather.models.CityModel;
import com.planradar.weather.utils.Constants;

public class HistoryFragment extends Fragment implements Constants {

    private HistoryViewModel mViewModel;



    FragmentHistoryBinding binding;
    Context context;
    CityModel cityModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = getContext();
         cityModel = getArguments().getParcelable(bundleCity);

        mViewModel.getHistory(context,cityModel);

        binding.back.setOnClickListener(v->getActivity().onBackPressed());

        observers();
        return root;
    }

    private void observers() {

        mViewModel.weatherRecordHistory.observe(getViewLifecycleOwner(), weatherRecordModelList -> {
            binding.history.addItemDecoration(new DividerItemDecoration(getContext(),0));
            binding.history.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.history.setAdapter(new WeatherHistoryAdapter(context,weatherRecordModelList));
        });
    }

}