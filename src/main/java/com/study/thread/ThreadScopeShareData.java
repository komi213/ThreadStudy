package com.study.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author: komi
 * @Date: 2018/5/6 21:54
 * @Description 第5课，同一线程范围内共享变量
 */
public class ThreadScopeShareData {
    //静态成员变量属于类，不属于某个对象
    //private static int data = 0;
    private static Map<Thread,Integer> threadData = new HashMap<>();
    public static void main(String[] args) {
        for(int i=0;i<2;i++){
            //设置数据
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()
                            +" has put data :"+data);
                    //key当前线程，value就是创造的数据
                    threadData.put(Thread.currentThread(),data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }

    }
    //A模块取数据
    static class A{
        public void get(){
            int data = threadData.get(Thread.currentThread());
            System.out.println("A from "+Thread.currentThread().getName()
                    +" get data :"+data);
        }
    }
    //B模块取数据
    static class B{
        public void get(){
            int data = threadData.get(Thread.currentThread());
            System.out.println("B from "+Thread.currentThread().getName()
                    +" get data :"+data);
        }
    }
}
