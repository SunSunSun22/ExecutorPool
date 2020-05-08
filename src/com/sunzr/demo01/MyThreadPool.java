package com.sunzr.demo01;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *自定义线程池类
 * 成员变量：
 * 1：任务队列  集合 需要考虑线程安全
 * 2: 核心线程数
 * 3: 当前线程数量
 * 4: 最大线程数量
 * 5: 任务队列的长度
 * 成员变量
 *   1：提交任务  将任务添加到集合中，需要判断是否超出了任务总长度
 *   2: 执行任务  判断当前线程的数量，决定创建核心线程还是非核心线程
 */
public class MyThreadPool
{
    //1 任务队列  集合 需要考虑线程安全
    private List<Runnable> tasks = Collections.synchronizedList(new LinkedList<>());

    //2: 核心线程数
    private int corePoolSize;

    //3: 当前线程数量
    private int num;

    //4: 最大线程数量
    private int maxSize;

    //5: 任务队列的长度
    private int workSize;

    public MyThreadPool(int corePoolSize, int maxSize, int workSize)
    {
        this.corePoolSize = corePoolSize;
        this.maxSize = maxSize;
        this.workSize = workSize;
    }

    //1:提交任务
    public void submit(Runnable r){
        //判断当前集合中的任务数量，是否超出了最大任务数量
        if (tasks.size()>=workSize){
            System.out.println("任务："+r+"被丢弃了。。。");
        }else {
            tasks.add(r);
            execTask(r);
        }
    }

    //2:执行任务
    private void execTask(Runnable r)
    {
        //判断当前线程池中的线程总数量，是否超出了核心数
        if (num < corePoolSize){
            new MyWorker("核心线程："+num,tasks).start();
            num++;
        }else if (num < maxSize){
            new MyWorker("非核心线程："+num,tasks).start();
            num++;
        }else{
            System.out.println("任务："+r+" 被缓存了。。。");
        }
    }

}
