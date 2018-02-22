package br.com.claro.hackaton.nfcservice;

import android.support.annotation.Nullable;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leandro on 23/01/2018.
 */

public class NfcApiModule {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit retrofit;

    public static void setRetrofit(@Nullable LoggingInterceptor.LogLevel logLevel) {

        LoggingInterceptor interceptor = new LoggingInterceptor(logLevel);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        builder.addInterceptor(chain -> chain.proceed(chain.request()));
//        builder.addInterceptor(chain -> {
//            Request request = chain.request().newBuilder()
//                    .addHeader("Authorization", String.format("Bearer %s", API_KEY)).build();
//            return chain.proceed(request);
//        });
        OkHttpClient okClient = builder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create(getDefaultGsonBuilder()))
                .build();
    }

    public static Gson getDefaultGsonBuilder() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

}
