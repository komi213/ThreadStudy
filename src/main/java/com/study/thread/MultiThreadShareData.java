package com.study.thread;

/**
 * @Description: 第7课：多个线程访问共享对象和数据的方式
 * @Author: komi
 * @Date: 2018/5/22 0:07
 */
public class MultiThreadShareData {
    public static void main(String[] args) {
        final ShareData1 data1 = new ShareData1();
        for(int i=0;i<50;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data1.decrement();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data1.increment();
                }
            }).start();
        }

    }
}

class ShareData1{
    private int j = 0;
    public synchronized void increment(){
        j++;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("increment:"+j);
    }
    public synchronized void decrement(){
        j--;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("decrement:"+j);
    }
}
