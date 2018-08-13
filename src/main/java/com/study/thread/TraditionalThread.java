package com.study.thread;

/**
 * @author komi
 * @deprecated 线程第一课:线程两种创建方式
 */
public class TraditionalThread {
	public static void main(String[] args) {
		//第一种：创建线程;Thread()匿名子类
		Thread th1 = new Thread(){
			@Override
			public void run() {
				while(true){
					//休眠5秒
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("1:"+Thread.currentThread().getName());
					System.out.println("2:"+this.getName());
				}
			}
		};
		th1.start();
		//第二种：Runnable方式，更体现面向对象方式(推荐)
		Thread th2 = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					//休眠5秒
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//Thread.currentThread()得到当前线程对象
					System.out.println("1:"+Thread.currentThread().getName());
				}
			}
		});
		th2.start();
		
		
		//思考问题(是运行runnable.run还是子类run?)：new Thread(runnable.run){子类run}.start()
		//子类run覆盖了父类run方式，不会去调用runnable.run。
		new Thread(new Runnable(){
			public void run() {
				while(true){
					//休眠5秒
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//Thread.currentThread()得到当前线程对象
					System.out.println("runnable:"+Thread.currentThread().getName());
				}
			}
		})
		{
			public void run() {
				while(true){
					//休眠5秒
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//Thread.currentThread()得到当前线程对象
					System.out.println("thread:"+Thread.currentThread().getName());
				}
			}
		}
		.start();
		
		
		
		
	}
}
