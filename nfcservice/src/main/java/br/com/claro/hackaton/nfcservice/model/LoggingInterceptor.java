package br.com.claro.hackaton.nfcservice.model;

import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by Leandro on 22/02/2018.
 */

public final class LoggingInterceptor implements Interceptor {

    private static final String TAG = "OkHttp";

    public enum LogLevel {
        NONE,
        BASIC,
        HEADERS,
        FULL
    }

    // private final Clock clock;
    private final LogLevel logLevel;

    public LoggingInterceptor( LogLevel logLevel) {
//        this.clock = clock;
        this.logLevel = logLevel;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//
//        long startMs = clock.millis();
        if (logLevel.ordinal() >= LogLevel.BASIC.ordinal()) {
            Log.v(TAG, String.format("---> %s %s", request.method(), request.url()));
        }
        if (logLevel.ordinal() >= LogLevel.HEADERS.ordinal()) {
            Log.v(TAG, prettyHeaders(request.headers()));
        }
        if (logLevel.ordinal() >= LogLevel.FULL.ordinal() && request.body() != null) {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            try {
                copy.body().writeTo(buffer);
                final String bodyStr = buffer.readUtf8();
                Log.v(TAG, bodyStr);
            } finally {
                buffer.close();
            }
        }

        Response response = chain.proceed(request);

//        long tookMs = clock.millis() - startMs;
        if (logLevel.ordinal() >= LogLevel.BASIC.ordinal()) {
//            Log.v(TAG, String.format("<--- (%s) for %s %s in %sms", response.code(), request.method(), response.request().url(), tookMs));
        }
        if (logLevel.ordinal() >= LogLevel.HEADERS.ordinal()) {
            Log.v(TAG, prettyHeaders(response.headers()));
        }
        if (logLevel.ordinal() >= LogLevel.FULL.ordinal() && response.body() != null) {
            final ResponseBody responseBody = response.body();
            final String responseBodyString = responseBody.string();
            Log.v(TAG, responseBodyString);

            // response body was consumed, replace it with a copy
            final ResponseBody bodyCopy = ResponseBody.create(responseBody.contentType(), responseBodyString);
            response = response.newBuilder().body(bodyCopy).build();
        }

        return response;
    }

    private String prettyHeaders(Headers headers) {
        if (headers.size() == 0) return "";

        StringBuilder builder = new StringBuilder();
        builder.append("  Headers:");

        for (int i = 0; i < headers.size(); i++) {
            builder.append("\n    ").append(headers.name(i)).append(": ").append(headers.value(i));
        }

        return builder.toString();
    }
}

