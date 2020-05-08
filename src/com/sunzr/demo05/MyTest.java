package com.sunzr.demo05;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 主程序类，测试任务类
 */
public class MyTest
{
    public static void main(String[] args)
    {
        //创建一个线程池对象
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3,5,1,TimeUnit.SECONDS,new LinkedBlockingDeque<>(15));
        //循环创建任务对象
        for (int i = 1; i <=20 ; i++)
        {
            MyTask myTask = new MyTask("客户"+i);
            pool.submit(myTask);
        }

        //3关闭线程池
        pool.shutdown();

    }
}
