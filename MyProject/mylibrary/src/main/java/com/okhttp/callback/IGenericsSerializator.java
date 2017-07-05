package com.okhttp.callback;

/**
 * Created by sm on 17/03/17.
 */
public interface IGenericsSerializator {
    <T> T transform(String response, Class<T> classOfT);
}
