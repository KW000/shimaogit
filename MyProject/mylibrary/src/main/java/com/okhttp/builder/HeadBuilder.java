package com.okhttp.builder;

import com.okhttp.OkHttpUtils;
import com.okhttp.request.OtherRequest;
import com.okhttp.request.RequestCall;

/**
 * Created by sm on 17/03/17.
 */
public class HeadBuilder extends GetBuilder {
    @Override
    public RequestCall build() {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
