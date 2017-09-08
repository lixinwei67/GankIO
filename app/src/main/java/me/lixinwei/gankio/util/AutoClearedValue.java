package me.lixinwei.gankio.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * <pre>
 *     author : lixinwei
 *     e-mail : lixinwei@idongjia.cn
 *     time   : 2017/09/08
 *     desc   : 如果 fragment 已经销毁，存储的值也会自动清除
 *     version: 1.0
 * </pre>
 */
public class AutoClearedValue<T> {
    private T value;
    public AutoClearedValue(Fragment fragment, T value) {
        FragmentManager fragmentManager = fragment.getFragmentManager();
        fragmentManager.registerFragmentLifecycleCallbacks(
                new FragmentManager.FragmentLifecycleCallbacks() {
                    @Override
                    public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                        if (f == fragment) {
                            AutoClearedValue.this.value = null;
                            fragmentManager.unregisterFragmentLifecycleCallbacks(this);
                        }
                    }
                },false);
        this.value = value;
    }

    public T get() {
        return value;
    }
}
