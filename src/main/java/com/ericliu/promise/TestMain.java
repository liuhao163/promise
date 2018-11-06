package com.ericliu.promise;

import java.util.concurrent.Executors;

/**
 * @Author: liuhaoeric
 * Create time: 2018/11/07
 * Description:
 */
public class TestMain {

    public static void main(String[] args) {
        PromiseExecutor promiseExecutor = new PromiseExecutor(Executors.newFixedThreadPool(10));

        Promise<Integer> defaultPromise = new DefaultPromise<>();
        defaultPromise.addListerner(new PromiseListerner() {
            @Override
            public void callback(Promise defaultPromise) {
                System.out.println("this is callback"+defaultPromise.get());
            }

            @Override
            public void callbackException(Throwable e) {
                System.out.println("this is callback exception");
                e.printStackTrace();
            }
        });


        promiseExecutor.execute(() -> {
//            System.out.println("this is command");
//            String s=null;
//            System.out.println(s.substring(0));
//            try {
//                Thread.sleep(10000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            return 1;
        }, defaultPromise);


    }
}
