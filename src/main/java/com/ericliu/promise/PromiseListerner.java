package com.ericliu.promise;

/**
 * @Author: liuhaoeric
 * Create time: 2018/11/06
 * Description:
 */
public interface PromiseListerner<T> {

    void callback(Promise<T> defaultPromise);

    void callbackException(Throwable e);
}
