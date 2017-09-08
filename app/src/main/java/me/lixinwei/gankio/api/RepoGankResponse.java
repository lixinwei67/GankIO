package me.lixinwei.gankio.api;

import java.util.List;

/**
 * <pre>
 *     author : lixinwei
 *     e-mail : lixinwei@idongjia.cn
 *     time   : 2017/09/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class RepoGankResponse<T> {
    private List<String> category;
    private boolean error;
    private T result;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
