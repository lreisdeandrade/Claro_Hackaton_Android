package br.com.claro.hackaton.nfcservice

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.threeten.bp.Clock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Leandro on 22/02/2018.
 */
internal const val BASE_URL: String = "https://api.github.com/"

/**
 * Created by gibran.lyra on 23/08/2017.
 */
object GitHubApiModule {
    lateinit var retrofit: Retrofit private set

    fun setRetrofit(logLevel: LoggingInterceptor.Level = LoggingInterceptor.Level.FULL) {
        val builder = OkHttpClient.Builder()
        val loggingInterceptor = LoggingInterceptor(Clock.systemDefaultZone(), logLevel)
        builder.addInterceptor(loggingInterceptor)

        val okClient = builder.build()
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create(getGsonBuilder()))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun getGsonBuilder(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create()
    }
}