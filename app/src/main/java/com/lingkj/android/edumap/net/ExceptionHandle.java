package com.lingkj.android.edumap.net;

import android.util.Log;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * author: panlijun
 * time: 2018/5/9 上午9:43
 * detail:
 */
public class ExceptionHandle  {

    private static final String TAG = "ApiException";
    //未授权
    private static final int UNAUTHORIZED = 401;
    //禁止访问
    private static final int FORBIDDEN = 403;
    //该网页不存在
    private static final int NOT_FOUND = 404;
    //请求超时
    private static final int REQUEST_TIMEOUT = 408;
    //服务器异常
    private static final int INTERNAL_SERVER_ERROR = 500;
    //
    private static final int BAD_GATEWAY = 502;
    //服务器不可用
    private static final int SERVICE_UNAVAILABLE = 503;

    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, ERROR.HTTP_ERROR, "协议出错");
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    Log.e(TAG, ex.errorCode + "");
                    ex.errorMsg = "网络错误";
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new ApiException(resultException, resultException.errorCode, "服务器出错");
            ex.errorMsg = resultException.errorMsg;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(e, ERROR.PARSE_ERROR, "解析错误");
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, ERROR.NETWORD_ERROR, "网络出错");
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ApiException(e, ERROR.SSL_ERROR, "证书验证失败");
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            ex = new ApiException(e, ERROR.TIMEOUT_ERROR, "连接超时");
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new ApiException(e, ERROR.TIMEOUT_ERROR, "连接超时");
            return ex;
        } else {
            ex = new ApiException(e, ERROR.UNKNOWN, "网络出错");
            return ex;
        }
    }


    /**
     * 约定异常
     */
    class ERROR {
        /**
         * 网络出错
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;

        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = 1006;
    }

    public static class ApiException extends Exception {
        public int errorCode;
        //用于展示的异常信息
        public String errorMsg;

        public ApiException(Throwable throwable, int errorCode, String errorMsg) {
            super(throwable);
            this.errorCode = errorCode;
            this.errorMsg = errorMsg;
        }
    }

    public class ServerException extends RuntimeException {
        public int errorCode;
        public String errorMsg;
    }
}
