package me.lixinwei.gankio.api;

import android.arch.lifecycle.LiveData;

import java.util.List;

import me.lixinwei.gankio.model.Gank;
import me.lixinwei.gankio.model.GankData;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * <pre>
 *     author : lixinwei
 *     e-mail : lixinwei@idongjia.cn
 *     time   : 2017/09/04
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface GankService {

    @GET("day/{year}/{month}/{day}")
    LiveData<ApiResponse<RepoGankResponse<GankData>>> getDayGank(
            @Path("year") String year,
            @Path("month") String month,
            @Path("day") String day);

    @GET("data/{category}/{num}/{page}")
    LiveData<ApiResponse<RepoGankResponse<List<Gank>>>> getCategoryData(
            @Path("category") String category,
            @Path("num") int num,
            @Path("page") int page
    );
}
