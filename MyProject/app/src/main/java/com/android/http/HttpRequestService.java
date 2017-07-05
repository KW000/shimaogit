package com.android.http;

import android.content.Context;
import com.okhttp.OkHttpUtils;
import com.okhttp.callback.StringCallback;
import java.util.Map;
import okhttp3.Call;

/**
 * 作者：flyframe on 2017/3/17 16:05
 * 邮箱：jxsm6@163.com
 */
public class HttpRequestService {

    private static volatile HttpRequestService httpRequestService;

    private StringCallback mCallback;

    public static HttpRequestService getInstance() {
        if (null == httpRequestService) {
            synchronized (HttpRequestService.class) {
                if (null == httpRequestService) {
                    httpRequestService = new HttpRequestService();
                }
            }
        }
        return httpRequestService;
    }

    /**
     * @Title: HttpRequestService.doRequestData
     * @Description: 网络请求方法
     * @Param: @param context上下文
     * @Param: @param url路径
     * @Param: @param dialogTitle提示框标题
     * @Param: @param params请求参数
     * @Param: @param callback回调函数
     * @Return: void
     */
    public void doRequestData(final Context context, String url, final String dialogTitle,
                              Map<String, String> params,final StringCallback callback){
        if (url != null && url != "") {
            this.mCallback = callback;
        }
        OkHttpUtils.post().url(url).params(params).build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onError(call,e,id);
            }

            @Override
            public void onResponse(String response, int id) {
                callback.onResponse(response,id);
            }

        });
    }

}
