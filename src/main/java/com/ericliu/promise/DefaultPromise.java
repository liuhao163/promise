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
public class DefaultPromise<T> extends AbstractDefaultPromise<T> {

    private AtomicBoolean success = new AtomicBoolean(false);
    private AtomicBoolean done = new AtomicBoolean(false);
    private AtomicBoolean failed = new AtomicBoolean(false);

    private Object wait = new Object();

    private List<PromiseListerner> listernerList = new ArrayList<>();

    private AtomicReference<T> result = new AtomicReference(null);
    private AtomicReference<Throwable> exception = new AtomicReference(null);

    @Override
    public boolean isSuccess() {
        return success.get();
    }

    @Override
    public void addListerner(PromiseListerner... listerners) {
        if (listerners != null && listerners.length > 0) {
            for (PromiseListerner listerner : listerners) {
                listernerList.add(listerner);
            }
        }
    }

    public void notifyAll0() {
        for (PromiseListerner listerner : listernerList) {
            if (isSuccess()) {
                listerner.callback(this);
            }else{
                listerner.callbackException(exception.get());
            }
        }
    }

    @Override
    public boolean trySuccess(T r) {
        boolean res = success.compareAndSet(false, true);
        if (res) {
            result.compareAndSet(null, r);
            notifyAll0();
        }

        return res;
    }

    @Override
    public boolean tryFailed(Throwable e) {
        boolean res = failed.compareAndSet(false, true);
        if (res) {
            exception.compareAndSet(null, e);
            notifyAll0();
        }
        return res;
    }

    @Override
    public boolean isDone() {
        return success.get() || failed.get();
    }

    @Override
    public T get() {
        while (!isSuccess()) {
            try {
                wait.wait();
            } catch (InterruptedException e) {
            }
        }

        return result.get();
    }

    @Override
    public Throwable getExcetpion() {
        while (!isSuccess()) {
            try {
                wait.wait();
            } catch (InterruptedException e) {
            }
        }

        return exception.get();
    }

}


