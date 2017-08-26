package com.herokuapp.erlangparasu.iaknewsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.herokuapp.erlangparasu.iaknewsapp.R;
import com.herokuapp.erlangparasu.iaknewsapp.models.Article;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView mWebView;

    private static final String TAG = "Tag." + DetailActivity.class.getSimpleName();
    public static final String EXTRA_ARTICLE = "article";
    public Article mArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                if (bundle.containsKey(EXTRA_ARTICLE)) {
                    Article article = (Article) bundle.getSerializable(EXTRA_ARTICLE);
                    if (article != null) {
                        mArticle = article;
                        setupViews();
                    }
                }
            }
        }

        finish();
    }

    /* Custom methods -------------------------------------------------------------------------- */

    private void setupViews() {
        Log.d(TAG, "setupViews: ");

        initWebView();
        mWebView.loadUrl(mArticle.getUrl());
    }

    private void initWebView() {
        Log.d(TAG, "initWebView: ");

        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.setHorizontalScrollBarEnabled(false);

        WebSettings webSettings = mWebView.getSettings();
        // Enable zoom
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);
        // Enable Javascript
        webSettings.setJavaScriptEnabled(true);
    }
}
