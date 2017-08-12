package id.daprin.iaknewsapp.rest;

import id.daprin.iaknewsapp.model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bagus on 12/08/2017.
 */

public interface ApiService {

    @GET("articles")
    Call<ApiResponse> getTechCrunchArticle(
            @Query("source") String source,
            @Query("apiKey") String apiKey
    );

}
