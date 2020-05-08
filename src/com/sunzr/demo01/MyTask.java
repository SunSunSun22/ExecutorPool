package com.sunzr.demo01;

/*
*   自定义线程池练习，任务类
*   包含任务编号，每一个任务执行时间设计为0.2秒
*
* */
public class MyTask implements Runnable
{
    private int id;

    //初始化Id属性
    public MyTask(int id)
    {
        this.id = id;
    }

    @Override
    public void run()
    {
        String name = Thread.currentThread().getName();
        System.out.println("线程："+name+" 即将执行的任务"+id);
        try
        {
            Thread.sleep(200);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("线程："+name+"完成了任务 "+id);

    }

    @Override
    public String toString()
    {
        return "MyTask{" +
            "id=" + id +
            '}';
    }
}
