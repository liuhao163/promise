package com.ericliu.promise;

/**
 * @Author: liuhaoeric
 * Create time: 2018/11/06
 * Description:
 */
public interface Command<T> {

    T doCommand();
}
