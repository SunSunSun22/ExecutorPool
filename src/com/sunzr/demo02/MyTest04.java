package com.sunzr.demo02;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 练习Excuters 获取 ExcutersService,测试关闭线程池的方法
 */
public class MyTest04
{
    public static void main(String[] args)
    {
        //test1();
        test2();

    }

    private static void test1()
    {
        //1:使用工厂类获取线程池对象
        ExecutorService es = Executors.newSingleThreadExecutor();
        //2:提交任务
        for (int i = 1; i <=10 ; i++)
        {
            es.submit(new MyRunnable4(i));
        }
        //3：关闭线程池，仅仅是不再接收新的任务，之前的任务还会正常执行
        es.shutdown();
    }


    private static void test2()
    {
        //1:使用工厂类获取线程池对象
        ExecutorService es = Executors.newSingleThreadExecutor(new ThreadFactory()
        {
            int n = 1;
            @Override
            public Thread newThread(Runnable r)
            {
                return new Thread(r,"自定义的线程名称"+n++);
            }
        });
        //2:提交任务
        for (int i = 1; i <=10 ; i++)
        {
            es.submit(new MyRunnable4(i));
        }

        List<Runnable> list = es.shutdownNow();
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
    }



}

/**
 * 任务类 包含任务id,打印出哪个线程正在执行
 */
class MyRunnable4 implements Runnable{

    private  int id;

    public MyRunnable4(int id)
    {
        this.id = id;
    }

    @Override
    public void run()
    {
        //获取线程名称，打印一句话
        String name = Thread.currentThread().getName();
        System.out.println(name+"执行了任务。。。"+id);
    }

    @Override
    public String toString()
    {
        return "MyRunnable4{" +
            "id=" + id +
            '}';
    }
}
