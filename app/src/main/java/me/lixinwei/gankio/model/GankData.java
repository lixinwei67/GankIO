package me.lixinwei.gankio.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * <pre>
 *     author : lixinwei
 *     e-mail : lixinwei@idongjia.cn
 *     time   : 2017/09/04
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class GankData {
    @SerializedName("Android")
    public List<Gank> androidList;
    @SerializedName("休息视频")
    public List<Gank> 休息视频List;
    @SerializedName("iOS")
    public List<Gank> iOSList;
    @SerializedName("福利")
    public List<Gank> 妹纸List;
    @SerializedName("拓展资源")
    public List<Gank> 拓展资源List;
    @SerializedName("瞎推荐")
    public List<Gank> 瞎推荐List;
    @SerializedName("App")
    public List<Gank> appList;
}
