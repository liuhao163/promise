# promise

## Promise 票据

DefaultPromise-->AbstractDefaultPromise-->Promise
可以考虑将返回值放到redis直接实现DefaultPromise就好

有俩种状态
success:异步任务成功
faild:异步任务异常

其中get方法会阻塞线程不建议食用，建议在listerner中实现方法。
getExcetpion方法获取promise中的异常

## Command

逻辑处理类

## PromiseExecutor 异步线

构造方法维护一个线程池。通过execute执行Cammand逻辑，并且将异常和返回值赋值给promise。完成后可执行listerner中的方法。

