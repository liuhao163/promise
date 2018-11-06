# promise

## Promise 票据

有俩种状态
success:异步任务成功
faild:异步任务异常

其中get方法阻塞队列获取方绘制
getException方法获取异常

## PromiseExecutor 消息队列

构造方法维护一个线程池。通过execute执行Cammand逻辑，并且将异常和返回值赋值给promise。完成后可执行listerner中的方法。

