package com.example.newsapplication.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapplication.ModelClass;
import com.example.newsapplication.R;
import com.example.newsapplication.mainNews;
import com.example.newsapplication.newsAdapter;
import com.example.newsapplication.utilities;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class technologyFragment extends Fragment {


     String apiKey = "fb7ad87d4c0a4e51af23b8caa9ea8248";

    ArrayList<ModelClass> modelClassArrayList;
    newsAdapter adapter;
    public static String country ="in";
    private String category = "technology";
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business, null);
        recyclerView = view.findViewById(R.id.recyclerViewBusiness);
        modelClassArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new newsAdapter(getContext(), modelClassArrayList);
        recyclerView.setAdapter(adapter);
        findNews();
        return view;
    }


    private void findNews(){
        utilities.getApiInterface().getCategoryNews(country,category, 100, apiKey).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if(response.isSuccessful())
                {
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });


    }

}

