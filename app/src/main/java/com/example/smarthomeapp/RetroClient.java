package com.example.smarthomeapp;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.CustomLogger;
import utils.Utils;

public class RetroClient {
    public static final String SERVER_ADDRESS = "http://220.149.148.40:8080/";
    private static Object retrofitService;

    public static API getApiInterface(){
        return (API) getClient();
    }

    private static Object getClient() {
        if (retrofitService == null) {
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

            httpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    if (Utils.isNetworkConnected()==false){
                        throw new NetworkNotConnectedException();
                    }
                    return chain.proceed(chain.request());

                }
            });

            httpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
            httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);

            if (CustomLogger.IS_DEBUG){
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClientBuilder.addInterceptor(logging);
            }

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory());
            gsonBuilder.serializeNulls();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .client(httpClientBuilder.build())
                    .build();
            retrofitService = retrofit.create(API.class);
        }
        return retrofitService;
    }

    private static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory{
        public <T> TypeAdapter <T> create(Gson gson, TypeToken<T> type){
            Class<T> rawType = (Class<T>) type.getRawType();
            if(rawType != String.class){
                return null;
            }
            return (TypeAdapter<T>) new StringAdapter();
        }
    }

    private static class StringAdapter extends TypeAdapter<String> {
        @Override
        public void write(JsonWriter writer, String value) throws IOException{
            if(TextUtils.isEmpty(value)){
                writer.nullValue();
                return;
            }
            writer.value(value);
        }

        @Override
        public String read(com.google.gson.stream.JsonReader in) throws IOException{
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return "";
            }
            return in.nextString();
        }
    }
    //코드 끝
}


