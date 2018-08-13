package com.study.thread;

import java.util.Random;

/**
 * @Author: komi
 * @Date: 2018/5/6 21:54
 * @Description 第6课，ThreadLocal实现（同一线程范围内共享变量）
 * 光从类名上看，也很难知道这个类的用途，容易误解为本地线程，其实它根本就不是线程。
 * ThreadLocal是解决多线程的并发访问的一种方式，为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突。
 */
public class ThreadLocalTest {
    //保存一个数据
    private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();
    //利用对象保存多个数据
    private static ThreadLocal<MyThreadScopeData> myThreadScopeDataThreadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        for(int i=0;i<2;i++){
            //设置数据
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()
                            +" has put data :"+data);
                    x.set(data);
                    /*MyThreadScopeData myData = new MyThreadScopeData();
                    myData.setName("name"+data);
                    myData.setAge(data);
                    myThreadScopeDataThreadLocal.set(myData);*/
                    MyThreadScopeData.getThreadInstance().setName("name"+data);
                    MyThreadScopeData.getThreadInstance().setAge(data);

                    new A().get();
                    new B().get();
                }
            }).start();
        }

    }
    //A模块取数据
    static class A{
        public void get(){
            int data = x.get();
            System.out.println("A from "+Thread.currentThread().getName()
                    +" get data :"+data);
            /*MyThreadScopeData myData = myThreadScopeDataThreadLocal.get();
            System.out.println("A from "+Thread.currentThread().getName()
                    +" getMyData :"+myData.getName()+","+myData.getAge());*/
            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("A from "+Thread.currentThread().getName()
                    +" getMyData :"+myData.getName()+","+myData.getAge());

        }
    }
    //B模块取数据
    static class B{
        public void get(){
            int data = x.get();
            System.out.println("B from "+Thread.currentThread().getName()
                    +" get data :"+data);
            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("B from "+Thread.currentThread().getName()
                    +" getMyData :"+myData.getName()+","+myData.getAge());
        }
    }
}

//设计成与当前线程有关类的实例
class MyThreadScopeData{
    private MyThreadScopeData(){}
    /*
    public static synchronized MyThreadScopeData getInstance(){
        //单例模式-懒汉式(getInstance()被调用才会实例化)
        if(instance == null){
            instance = new MyThreadScopeData();
        }
        return instance;
    }
    private static MyThreadScopeData instance = null;*/

    public static MyThreadScopeData getThreadInstance(){
        //取当前线程实例,有就直接返回，没有就创建并保存到ThreadLocal，再返回
        MyThreadScopeData instance = map.get();
        if(instance == null){
            instance = new MyThreadScopeData();
            map.set(instance);
        }
        return instance;
    }
    private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
