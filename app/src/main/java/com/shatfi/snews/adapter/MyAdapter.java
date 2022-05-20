package com.shatfi.snews.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.shatfi.snews.DetailBerita;
import com.shatfi.snews.R;
import com.shatfi.snews.retrofitjson.News;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<News> newsList;

    public MyAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    class MyClassAdapter extends  RecyclerView.ViewHolder{

        private ImageView img;
        private TextView title;
        private ImageLoader imageLoader;
        private LinearLayout click;

        public MyClassAdapter(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            imageLoader = ImageLoader.getInstance();
            click = itemView.findViewById(R.id.click);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_news, parent, false);
        return new MyClassAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        ((MyClassAdapter) holder).title.setText(newsList.get(i).getTitle());
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .build();
        ((MyClassAdapter) holder).imageLoader.init(configuration);
        ((MyClassAdapter) holder).imageLoader.displayImage(newsList.get(i).getUrlToImage(), ((MyClassAdapter) holder).img);
        ((MyClassAdapter) holder).click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Kamu Klik" + newsList.get(i).getTitle(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, DetailBerita.class);
                intent.putExtra("title", newsList.get(i).getTitle());
                intent.putExtra("urlToImage", newsList.get(i).getUrlToImage());
                intent.putExtra("content", newsList.get(i).getContent());
                intent.putExtra("url", newsList.get(i).getUrl());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
