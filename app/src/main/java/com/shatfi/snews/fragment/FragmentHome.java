package com.shatfi.snews.fragment;

import android.net.DnsResolver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.shatfi.snews.R;
import com.shatfi.snews.adapter.MyAdapter;
import com.shatfi.snews.retrofitconfig.GetJsonAll;
import com.shatfi.snews.retrofitconfig.RetrofitConfigToJson;
import com.shatfi.snews.retrofitjson.News;
import com.shatfi.snews.retrofitjson.NewsList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {

    GetJsonAll getJsonAll;
    List<News> newsList;
    String title, description;
    RecyclerView recycler_view;
    MyAdapter myAdapter;
    GridLayoutManager gm;

    View view;
    SpinKitView spinKitView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        spinKitView = view.findViewById(R.id.spin_kit);
        recycler_view = view.findViewById(R.id.recycler_view);
        gm = new GridLayoutManager(getContext(), 2);
        recycler_view.setLayoutManager(gm);
        newsList = new ArrayList<>();
        myAdapter = new MyAdapter(getContext(), newsList);
        recycler_view.setAdapter(myAdapter);
        getJsonAll = RetrofitConfigToJson.getResponses();
        getJsonAll.getNewsList("id", "730387225c4745af9e1b45562f7466aa").enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                Log.d("Berhasil", response + "");
                spinKitView.setVisibility(View.VISIBLE);

                if(response.isSuccessful()){
                    spinKitView.setVisibility(View.GONE);
                    newsList = response.body().getArticles();

                    title = newsList.get(0).getTitle();
                    description = newsList.get(0).getDescription();
                    Log.d("titleBerita", "Judul: "+title+" "+"Deskripsi: "+ description);

                    myAdapter = new MyAdapter(getContext(), newsList);
                    recycler_view.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                }


            }


            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("Gagal", t + "");
            }
        });

        return  view;
    }
}
