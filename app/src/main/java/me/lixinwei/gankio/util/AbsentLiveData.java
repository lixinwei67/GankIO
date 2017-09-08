package me.lixinwei.gankio.util;

import android.arch.lifecycle.LiveData;

/**
 * <pre>
 *     author : lixinwei
 *     e-mail : lixinwei@idongjia.cn
 *     time   : 2017/09/08
 *     desc   : 一个具有 null 值的 LiveData 类
 *     version: 1.0
 * </pre>
 */
public class AbsentLiveData extends LiveData {
    private AbsentLiveData() {
        postValue(null);
    }
    public static <T> LiveData<T> create() {
        //noinspection unchecked
        return new AbsentLiveData();
    }
}

