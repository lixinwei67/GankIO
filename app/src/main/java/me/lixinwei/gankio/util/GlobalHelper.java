package me.lixinwei.gankio.util;

import com.google.gson.Gson;

import me.lixinwei.gankio.api.GankService;
import retrofit2.Retrofit;

/**
 * <pre>
 *     author : lixinwei
 *     e-mail : lixinwei@idongjia.cn
 *     time   : 2017/09/04
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class GlobalHelper {

    // 全局接口
    public GankService service;
    // 全局线程池
    public AppExecutors appExecutors;
    // 全局gson
    public Gson gson;

    public static GlobalHelper INSTANCE = new GlobalHelper();

    private GlobalHelper() {

    }


    public void setService(Retrofit retrofit) {
        service = retrofit.create(GankService.class);
    }

    public void setAppExecutors(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}

