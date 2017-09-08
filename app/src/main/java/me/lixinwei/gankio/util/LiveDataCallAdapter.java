package me.lixinwei.gankio.util;

import android.arch.lifecycle.LiveData;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import me.lixinwei.gankio.api.ApiResponse;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <pre>
 *     author : lixinwei
 *     e-mail : lixinwei@idongjia.cn
 *     time   : 2017/09/04
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ApiResponse<R>>> {

    private final Type responseType;

    public LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<ApiResponse<R>> adapt(final Call<R> call) {

        final AtomicBoolean started = new AtomicBoolean(false);

        return new LiveData<ApiResponse<R>>() {
            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(Call<R> call, Response<R> response) {
                            postValue(new ApiResponse<R>(response));
                        }

                        @Override
                        public void onFailure(Call<R> call, Throwable t) {
                            postValue(new ApiResponse<R>(new RuntimeException("网络异常,请检查您的网络")));
                        }
                    });
                }
            }
        };
    }
}
