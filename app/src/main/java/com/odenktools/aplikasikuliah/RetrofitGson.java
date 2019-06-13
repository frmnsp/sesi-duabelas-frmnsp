package com.odenktools.aplikasikuliah;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit Class Menggunakan GsonParser.
 */
public class RetrofitGson {

    private static RetrofitGson ourInstance;

    private Retrofit retrofit;

    private Map<String, String> requestHeaders = new HashMap<>();

    private ApiServices apiServices;

    private static final String BASE_URL = "http://kampus.odenktools.com/api/v1/";

    private static final String API_KEY = "Ctbm5oNWbsPbfmpW60yjbcvEmwXrJr5H";

    private RetrofitGson() {

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(2, TimeUnit.MINUTES);
        client.readTimeout(2, TimeUnit.MINUTES);
        client.writeTimeout(2, TimeUnit.MINUTES);

        // Tambahkan Authentifikasi pada default request.
        client.addInterceptor(this.headerInterceptor());

        // Logging agar memudahkan proses development (Error jadi terlihat).
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(logging);

        // Retrofit
        OkHttpClient clientBuild = client.build();
        Retrofit.Builder retrofit = new Retrofit.Builder();
        retrofit.baseUrl(RetrofitGson.BASE_URL);
        retrofit.client(clientBuild);
        retrofit.addConverterFactory(GsonConverterFactory.create(gson));

        this.retrofit = retrofit.build();
    }

    /**
     * Singleton Instance.
     *
     * @return RetrofitString
     */
    public static RetrofitGson getInstance() {
        if (ourInstance == null) {
            ourInstance = new RetrofitGson();
        }
        return ourInstance;
    }

    /**
     * Get Rest API.
     *
     * @return ApiServices
     */
    public ApiServices getApisServices() {
        if (this.apiServices == null) {
            this.apiServices = this.retrofit.create(ApiServices.class);
        }
        return this.apiServices;
    }

    /**
     * Set request headers.
     *
     * @param requestHeaders data header yang akan dipassing.
     */
    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    /**
     * Intercept request, Disini berfungsi untuk input data header atau lainnya.
     *
     * @return Interceptor
     */
    private Interceptor headerInterceptor() {
        return chain -> {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder();
            final String basic = "Bearer " + API_KEY;
            builder.addHeader("Accept", "application/json");
            builder.addHeader("Authorization", basic);
            builder.addHeader("Cache-Control", "no-cache");
            for (final String headerName : this.requestHeaders.keySet()) {
                final String headerValue = this.requestHeaders.get(headerName);
                builder.header(headerName, headerValue);
            }
            return chain.proceed(
                    builder.build()
            );
        };
    }
}

