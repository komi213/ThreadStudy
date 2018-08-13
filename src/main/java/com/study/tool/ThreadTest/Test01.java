package com.study.tool.ThreadTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test01 {
	public static void main(String[] args) {
		//测试100个线程往帐户1，存1元钱
		Account account1 = new Account();
		ExecutorService service = Executors.newFixedThreadPool(100);// 创建固定大小的线程池
		for(int i=1;i<=100;i++){
			service.execute(new AddMoneyThread(account1, 1));
		}
		/**
		 * 可以关闭 ExecutorService，这将导致其拒绝新任务。
		 * 提供两个方法来关闭 ExecutorService。
		 * shutdown() 方法在终止前允许执行以前提交的任务，
		 * 而 shutdownNow() 方法阻止等待任务启动并试图停止当前正在执行的任务。
		 * 在终止时，执行程序没有任务在执行，也没有任务在等待执行，并且无法提交新任务。
		 * 应该关闭未使用的 ExecutorService 以允许回收其资源。 
		 */
		service.shutdown();//关闭线程池  这将导致其拒绝新任务,方法在终止前允许执行以前提交的任务。
		//判断是否所有的线程已经运行完 
		while(!service.isTerminated()) {
			System.out.println("有任务没完成,账户余额: " + account1.getBalance());
			try {
				Thread.sleep(10);//等待10毫秒再判断
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("所有任务全部完成,账户余额: " + account1.getBalance());
		
		//测试100个线程往帐户2，存2元钱
		/*Account account2 = new Account();
		ExecutorService service2 = Executors.newFixedThreadPool(100);// 创建固定大小的线程池
		for(int i=1;i<=100;i++){
			service2.execute(new AddMoneyThread(account2, 2));
		}
		service2.shutdown();
		while(!service2.isTerminated()) {}
		System.out.println("账户余额: " + account2.getBalance());*/
		//结束-------------------------------
	}
}
