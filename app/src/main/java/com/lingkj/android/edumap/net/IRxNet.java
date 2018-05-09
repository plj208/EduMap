package com.lingkj.android.edumap.net;

import com.google.gson.JsonObject;
import com.lingkj.android.edumap.bean.test;

import org.json.JSONObject;

import io.reactivex.Observable;


/**
 *
 * @author panlijun
 * 接口类
 */

public interface IRxNet {

    Observable<Object> getNet();


}
