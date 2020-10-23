package com.sunbeam.test.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.sunbeam.test.R;

public class DetailsActivity extends AppCompatActivity {

    //this activity loads the webview for the details activity

    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        webView = findViewById(R.id.web);
        embedWebView();

    }

    public void embedWebView()
    {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.loadUrl(url);

    }
}
