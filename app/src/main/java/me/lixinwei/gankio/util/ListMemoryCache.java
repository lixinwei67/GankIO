package me.lixinwei.gankio.util;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : lixinwei
 *     e-mail : lixinwei@idongjia.cn
 *     time   : 2017/09/04
 *     desc   : 内存缓存
 *     version: 1.0
 * </pre>
 */
public class ListMemoryCache<X> {

    // 实际数据
    private List<X> value;
    private final MutableLiveData<List<X>> result = new MutableLiveData<>();

    public final Object LOCK = new Object();

    public void setValue(List<X> value) {
        this.value = value;
    }

    public List<X> getValue() {
        return value;
    }

    /**
     * 更新数据 重置数据源
     */
    public void update() {

        if (value == null) {
            result.postValue(null);
        } else {
            result.postValue(new ArrayList<X>(value));
        }
    }

    public LiveData<List<X>> asLiveData() {
        update();
        return result;
    }
}
