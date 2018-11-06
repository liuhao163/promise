package com.ericliu.promise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: liuhaoeric
 * Create time: 2018/11/06
 * Description:
 */
interface Promise<T> {

    boolean isSuccess();

    void addListerner(PromiseListerner... listerners);

    boolean trySuccess(T r);

    boolean tryFailed(Throwable e);

    boolean isDone();

    T get();

    Throwable getExcetpion();

}


