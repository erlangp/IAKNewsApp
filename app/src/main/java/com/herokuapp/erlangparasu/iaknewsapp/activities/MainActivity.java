package com.herokuapp.erlangparasu.iaknewsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.herokuapp.erlangparasu.iaknewsapp.BuildConfig;
import com.herokuapp.erlangparasu.iaknewsapp.R;
import com.herokuapp.erlangparasu.iaknewsapp.adapters.NewsAdapter;
import com.herokuapp.erlangparasu.iaknewsapp.models.ApiResponse;
import com.herokuapp.erlangparasu.iaknewsapp.models.Article;
import com.herokuapp.erlangparasu.iaknewsapp.rest.ApiClient;
import com.herokuapp.erlangparasu.iaknewsapp.rest.ApiService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements
        NewsAdapter.OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String NEWS_SOURCE = "techcrunch";

    /**
     * Note:
     * RecyclerView require:
     * - LayoutManager
     * - Adapter
     * - ViewHolder
     */
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;
    private NewsAdapter mNewsAdapter;
    private ArrayList<Article> mArticles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Setup LayoutManager
        mLinearLayoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        // Setup Adapter
        mNewsAdapter = new NewsAdapter(mArticles);
        mNewsAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mNewsAdapter);

        // Fetch data from server
        getData();
    }

    /* Implements ------------------------------------------------------------------------------ */

    @Override
    public void onButtonReadMoreClicked(Article article) {
        Log.d(TAG, "onButtonReadMoreClicked: ");

        //Toast.makeText(MainActivity.this,
        //        "News Title: " + article.getTitle(),
        //        Toast.LENGTH_SHORT)
        //        .show();

        // Start new activity
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_ARTICLE, article);
        startActivity(intent);
    }

    /* Custom methods -------------------------------------------------------------------------- */

    private void getData() {
        Log.d(TAG, "getData: ");

        ApiService apiService = ApiClient.getApiClient()
                .create(ApiService.class);

        Call<ApiResponse> call = apiService.getArticles(
                NEWS_SOURCE,
                BuildConfig.NEWS_API_KEY
        );

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.d(TAG, "onResponse: ");

                ApiResponse apiResponse = response.body();
                if (apiResponse != null) {
                    mNewsAdapter.setData(apiResponse.getArticles());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: message=" + t.getMessage());
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Article> getDummyArticles() {
        Log.d(TAG, "getDummyArticles: ");

        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Article article = new Article();
            article.setTitle("Ini Merupakakan Title untuk menampilkan max line dari textview");
            article.setDescription("Ini merupakan deskripsi yang merupakan data random, yang bisa di copas. Ini merupakan deskripsi yang merupakan data random, yang bisa di copas.Ini merupakan deskripsi yang merupakan data random, yang bisa di copas.Ini merupakan deskripsi yang merupakan data random, yang bisa di copas.Ini merupakan deskripsi yang merupakan data random, yang bisa di copas.");
            article.setUrlToImage("https://tctechcrunch2011.files.wordpress.com/2017/08/aug_chart_1.png?w=764&h=400&crop=1");
            articles.add(article);
        }

        return articles;
    }
}
