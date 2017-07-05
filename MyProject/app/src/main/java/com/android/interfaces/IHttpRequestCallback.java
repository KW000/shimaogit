package com.android.interfaces;


/**
 * http调用完毕后，异步返回结果操作接口
 * @author admin
 *
 */
public interface IHttpRequestCallback {
	
	void onRequestCallBack(String result);
}
