package com.ixuea.courses.mymusic.component.api;

import com.ixuea.courses.mymusic.component.observer.ObserverAdapter;
import com.ixuea.courses.mymusic.model.response.BaseResponse;
import com.ixuea.courses.mymusic.util.HttpUtil;

import okhttp3.Response;

/**
 * 网络请求Observer
 *
 * @param <T>
 */
public abstract class HttpObserver<T> extends ObserverAdapter<T> {
    /**
     * 请求成功
     *
     * @param data
     */
    public abstract void onSucceeded(T data);

    /**
     * 请求失败
     *
     * @param data
     * @param e
     * @return true:自己处理;false:框架处理
     */
    public boolean onFailed(T data, Throwable e) {
        return false;
    }

    @Override
    public void onNext(T t) {
        super.onNext(t);

        onEnd();

        if (isSucceeded(t)) {
            //请求正常
            onSucceeded(t);
        } else {
            //请求出错了
            handlerRequest(t, null);
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        onEnd();

        //处理错误
        handlerRequest(null, e);
    }

    /**
     * 处理错误网络请求
     *
     * @param data
     * @param error
     */
    private void handlerRequest(T data, Throwable error) {
        if (onFailed(data, error)) {
            //回调了请求失败方法
            //并且该方法返回了true

            //返回true就表示外部手动处理错误
            //那我们框架内部就不用做任何事情了
        } else {
            HttpUtil.handlerRequest(data, error);
        }
    }

    /**
     * 请求结束，成功失败都会调用(调用前调用)，使用在这里隐藏加载提示
     */
    public void onEnd() {

    }

    private boolean isSucceeded(T t) {
        if (t instanceof Response) {
            //retrofit里面的响应对象

            //获取响应对象
            Response response = (Response) t;

            //获取响应码
            int code = response.code();

            //判断响应码
            if (code >= 200 && code <= 299) {
                //网络请求正常
                return true;
            }
        } else if (t instanceof BaseResponse) {
            //判断具体的业务请求是否成功
            BaseResponse response = (BaseResponse) t;

            //状态码为0表示成功
            //这是我们和服务端的一个规定
            return response.isSucceeded();
        }

        return false;
    }


}
