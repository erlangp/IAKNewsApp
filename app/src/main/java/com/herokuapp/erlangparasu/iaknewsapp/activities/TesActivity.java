package com.herokuapp.erlangparasu.iaknewsapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.herokuapp.erlangparasu.iaknewsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TesActivity extends AppCompatActivity {

    private static final String URL = "https://techcrunch.com" +
            "/2017/08/11" +
            "/uber-shareholder-group-asks-benchmark-to-step-down-from-board-following-kalanick-suit/";

    @BindView(R.id.webView)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setupWebView();
        mWebView.loadUrl(URL);
    }

    private void setupWebView() {
        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.setHorizontalScrollBarEnabled(false);

        WebSettings webSettings = mWebView.getSettings();

        // Enable zoom
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);
        // Enable javascript
        webSettings.setJavaScriptEnabled(true);
    }
}
