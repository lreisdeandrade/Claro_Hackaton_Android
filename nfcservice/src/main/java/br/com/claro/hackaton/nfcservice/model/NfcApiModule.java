package br.com.claro.hackaton.nfcservice.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.Nullable;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leandro on 22/02/2018.
 */

public class NfcApiModule {

    private static final String BASE_URL = "https://service.us.apiconnect.ibmcloud.com/gws/apigateway/api/4d88866c15adf934a4964c33d7c9f62d5610f9defd2227e9a391adaa690cc96c/hackathon/";
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
