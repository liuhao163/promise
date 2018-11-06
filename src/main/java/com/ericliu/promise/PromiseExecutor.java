package com.ericliu.promise;

import java.util.concurrent.ExecutorService;

/**
 * @Author: liuhaoeric
 * Create time: 2018/11/06
 * Description:
 */
public class PromiseExecutor {
    private ExecutorService executorService;

    public PromiseExecutor(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public <T> Promise<T> execute(Command<T> command, PromiseListerner... promiseListerner) {
        DefaultPromise<T> defaultPromise = new DefaultPromise<T>();
        defaultPromise.addListerner(promiseListerner);
        return execute(command, defaultPromise);
    }

    public <T> Promise<T> execute(Command<T> command, Promise<T> defaultPromise) {
        executorService.execute(() -> {
            try {
                T res = command.doCommand();
                defaultPromise.trySuccess(res);
            } catch (Exception e) {
                defaultPromise.tryFailed(e);
            }
        });
        return defaultPromise;
    }
}
