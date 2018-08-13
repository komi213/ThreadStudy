package com.study.tool.ThreadTest;

public class SellThread implements Runnable{
   int tickets=10;
   public void run(){
      while(true){
    	  //加上一个同步关键字
    	  synchronized(this){
    		  if(tickets>0){
	    		 try//让线程睡眠10毫秒，只是修改了这里
	             {
	                 Thread.sleep(10);
	             } catch (InterruptedException e){
	                 e.printStackTrace();
	             }
	            System.out.println("obj:"+Thread.currentThread().getName()+" sell tickets:"+tickets);
	            tickets--;
	         }
    	  }
      }
   }
}
