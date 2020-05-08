package com.sunzr.demo04;

import java.util.concurrent.*;

/**
 * 练习异步计算结果
 */
public class FutureDemo
{
    public static void main(String[] args) throws Exception
    {
        //1：获取线程池对象
        ExecutorService es = Executors.newCachedThreadPool();
        //2:创建callable类型的任务对象
        Future<Integer> f = es.submit(new MyCall(1, 1));
        //3: 判断任务是否已经完成
        //test01(f);
        boolean cancel = f.cancel(true);
        //System.out.println("取消的结果"+cancel);
        //Integer v = f.get(1, TimeUnit.SECONDS); 由于等待时间过短，任务会来不及完成，报异常
        //System.out.println("任务执行的结果"+v);

    }

    //正常测试流程
    private static void test01(Future<Integer> f)
        throws InterruptedException, ExecutionException
    {
        boolean done = f.isDone();
        System.out.println("第一次判断任务是否已经完成"+done);

        boolean cancelled = f.isCancelled();
        System.out.println("第一次判断任务是否已经取消"+cancelled);

        Integer v = f.get();
        System.out.println("任务执行的结果是："+v);

        boolean done2 = f.isDone();
        System.out.println("第2次判断任务是否已经完成"+done2);
        boolean cancelled2 = f.isCancelled();
        System.out.println("第2次判断任务是否已经取消"+cancelled2);
    }
}

class MyCall implements Callable<Integer>{
    //通过构造方法传两个参数
    private int a ;
    private int b ;

    public MyCall(int a, int b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception
    {
        String name = Thread.currentThread().getName();
        System.out.println("准备开始执行。。。");
        Thread.sleep(2000);
        System.out.println("线程执行完成。。。");
        return a+b;
    }
}
