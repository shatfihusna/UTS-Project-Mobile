package com.shatfi.snews;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class DetailBerita extends AppCompatActivity {

    String title, image, content, url;
    TextView titles;
    ImageView images;
    WebView webku;
    ImageLoaderConfiguration configuration;
    ImageLoader loader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_berita);

        titles = findViewById(R.id.title);
        images = findViewById(R.id.image);
        webku = findViewById(R.id.webku);

        loader = ImageLoader.getInstance();

        title = getIntent().getStringExtra("title");
        image = getIntent().getStringExtra("urlToImage");
        content = getIntent().getStringExtra("content");
        url = getIntent().getStringExtra("url");

        webku.loadUrl(url);
        webku.setWebViewClient(new WebViewClient());

         /*Log.d("titleKu ", title);
        Toast.makeText(getApplicationContext(), "Kamu tadi klik "+ title, Toast.LENGTH_LONG).show();
        titles.setText(title);
        configuration = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .build();
        loader.init(configuration);
        loader. displayImage(image, images);
        webku.loadData(content, "text/html", "utf-8");

        webku.setWebViewClient(new WebViewClient()); */
    }
}
