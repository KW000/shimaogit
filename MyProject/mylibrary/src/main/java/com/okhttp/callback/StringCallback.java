package com.okhttp.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by sm on 17/03/17.
 */
public abstract class StringCallback extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException {
        return response.body().string();
    }
}
