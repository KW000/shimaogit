package com.okhttp.builder;

import java.util.Map;

/**
 * Created by sm on 17/03/17.
 */
public interface HasParamsable {
    OkHttpRequestBuilder params(Map<String, String> params);
    OkHttpRequestBuilder addParams(String key, String val);
}
