package me.lixinwei.gankio.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import me.lixinwei.gankio.api.ApiResponse;
import me.lixinwei.gankio.api.RepoGankResponse;
import me.lixinwei.gankio.model.Gank;
import me.lixinwei.gankio.model.GankData;
import me.lixinwei.gankio.repository.NetworkBoundResource;
import me.lixinwei.gankio.util.GlobalHelper;
import me.lixinwei.gankio.util.ListMemoryCache;
import me.lixinwei.gankio.vo.Resource;

/**
 * <pre>
 *     author : lixinwei
 *     e-mail : lixinwei@idongjia.cn
 *     time   : 2017/09/04
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@SuppressWarnings("unchecked")
public class TestViewModel extends ViewModel {
    private final AtomicBoolean init = new AtomicBoolean(false);
    private LiveData<Resource<List>> results;

    ListMemoryCache gank = new ListMemoryCache();


    public TestViewModel() {

        results = getDayGank();

    }

    public LiveData<Resource<List>> getResults() {
        return results;
    }

    private LiveData<Resource<List>> getCategory() {
        return new NetworkBoundResource<List, RepoGankResponse<List<Gank>>>(GlobalHelper.INSTANCE.appExecutors) {
            @Override
            protected void saveCallResult(@NonNull RepoGankResponse<List<Gank>> item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable List data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List> loadFromDb() {
                return gank.asLiveData();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<RepoGankResponse<List<Gank>>>> createCall() {
                return GlobalHelper.INSTANCE.service.getCategoryData("Android", 50, 1);
            }
        }.asLiveData();
    }

    public LiveData<Resource<List>> getDayGank() {
        return new NetworkBoundResource<List, RepoGankResponse<GankData>>(GlobalHelper.INSTANCE.appExecutors) {
            @Override
            protected void saveCallResult(@NonNull RepoGankResponse<GankData> item) {
                synchronized (gank.LOCK) {
                    List result = new ArrayList();
                    gank.setValue(result);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List> loadFromDb() {
                return gank.asLiveData();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<RepoGankResponse<GankData>>> createCall() {
                return GlobalHelper.INSTANCE.service.getDayGank("2017", "04", "21");
            }
        }.asLiveData();
    }
}
