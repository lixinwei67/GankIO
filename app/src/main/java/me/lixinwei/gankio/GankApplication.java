package me.lixinwei.gankio;

import android.app.Application;

import com.google.gson.Gson;

import org.litepal.LitePal;

import java.util.concurrent.TimeUnit;

import me.lixinwei.gankio.util.AppExecutors;
import me.lixinwei.gankio.util.GlobalHelper;
import me.lixinwei.gankio.util.LiveDataCallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     author : lixinwei
 *     e-mail : lixinwei@idongjia.cn
 *     time   : 2017/09/04
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class GankApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LitePal.initialize(this);


        GlobalHelper.INSTANCE.setGson(new Gson());
        GlobalHelper.INSTANCE.setAppExecutors(new AppExecutors());
        final int TIMEOUT = 60;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS);


        OkHttpClient client = clientBuilder.build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl("http://gank.io/api/");
        retrofitBuilder.client(client);
        retrofitBuilder.addCallAdapterFactory(LiveDataCallAdapterFactory.create());
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(GlobalHelper.INSTANCE.gson));

        Retrofit retrofit = retrofitBuilder.build();

        GlobalHelper.INSTANCE.setService(retrofit);


    }
}
