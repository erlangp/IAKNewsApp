package com.herokuapp.erlangparasu.iaknewsapp.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.herokuapp.erlangparasu.iaknewsapp.R;
import com.herokuapp.erlangparasu.iaknewsapp.models.Article;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.progressbar1)
    ProgressBar mProgressBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nestedScrollView)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.fabFavorite)
    FloatingActionButton mFab;

    private static final String TAG = "Tag." + DetailActivity.class.getSimpleName();
    public static final String EXTRA_ARTICLE = "article";
    private Article mArticle;
    private boolean mIsFav = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                if (bundle.containsKey(EXTRA_ARTICLE)) {
                    Article article = bundle.getParcelable(EXTRA_ARTICLE);
                    if (article != null) {
                        mArticle = article;
                        setupViews();
                        return;
                    }
                }
            }
        }
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: ");

        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                //       mWebView.stopLoading();
                finish();
                return true;
            //break;
        }

        return super.onOptionsItemSelected(item);
    }

    /* Custom methods -------------------------------------------------------------------------- */

    private void setupViews() {
        Log.d(TAG, "setupViews: ");

        setupActionBar();
        initWebView();
        setupFab();

        mProgressBar.setMax(100);
        mWebView.loadUrl(mArticle.getUrl());
    }

    private void setupActionBar() {
        Log.d(TAG, "setupActionBar: ");

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }

        actionBar.setDisplayHomeAsUpEnabled(true);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_action_close);
        actionBar.setHomeAsUpIndicator(drawable);

        actionBar.setTitle(mArticle.getTitle());
        actionBar.setSubtitle(mArticle.getUrl());
    }

    private void setupFab() {
        Log.d(TAG, "setupFab: ");

        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY,
                                       int oldScrollX, int oldScrollY) {
                if (scrollY < oldScrollY) {
                    mFab.show();
                } else {
                    mFab.hide();
                }
            }
        });

        mFab.setImageResource(mIsFav ? R.drawable.ic_action_name : R.drawable.ic_action_fav_0);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                mIsFav = !mIsFav;
                mFab.setImageResource(mIsFav ? R.drawable.ic_action_name : R.drawable.ic_action_fav_0);
            }
        });
    }

    private void initWebView() {
        Log.d(TAG, "initWebView: ");

        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.d(TAG, "onProgressChanged: newProgress=" + String.valueOf(newProgress));
                mProgressBar.setProgress(newProgress);
                mProgressBar.setVisibility(newProgress == 100 ? View.GONE : View.VISIBLE);
            }
        });

        WebSettings webSettings = mWebView.getSettings();
        // Enable zoom
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);
        // Enable Javascript
        webSettings.setJavaScriptEnabled(true);
    }
}
