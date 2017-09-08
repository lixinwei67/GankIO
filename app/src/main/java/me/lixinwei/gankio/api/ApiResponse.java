package me.lixinwei.gankio.api;

import android.support.annotation.Nullable;

import java.io.IOException;

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
public class ApiResponse<T> {

    public final int code;

    @Nullable
    public final T body;
    @Nullable
    public final String errorMessage;

    public ApiResponse(Response<T> response) {
        code = response.code();

        if (response.isSuccessful()) {

            body = response.body();
            errorMessage = null;

        } else {
            String message = null;
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().string();
                } catch (IOException ignored) {
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }
            errorMessage = message;
            body = null;
        }

    }

    public ApiResponse(Throwable t) {

        code = 500;
        body = null;

        if (t != null) {
            errorMessage = t.getMessage();
        } else {
            errorMessage = null;
        }

    }

    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }
}
