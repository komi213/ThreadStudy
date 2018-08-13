package com.study.thread;

/**
 * 线程第4课：实现 子线程循环10次，接着主线程循环20次，
 * 子线程循环10次，接着主线程循环20次，如此循环3次。
 * @author komi
 *
 */
public class TraditionalThreadCommunication {
	
	public static void main(String[] args) {
		
		final Business business = new Business();
		//子线程
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=1;i<=3;i++){
					business.sub(i);
				}
			}
			
		}).start();
		
		//main方法(也是线程)做主线程
		for(int i=1;i<=3;i++){
			business.main(i);
		}
	}
	
		
}

class Business{
	//默认子线程先运行
	private boolean bShouldSub = true;
	//子线程任务(执行10次输出)
	public synchronized void sub(int i){
		//永远在while循环里而不是if语句下使用wait。这样，循环会在线程睡眠前后都检查wait的条件
		//if(!bShouldSub){
		while(!bShouldSub){
			try {
				this.wait();//不该我执行就等待
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int j=1;j<=10;j++){
			System.out.println("sub thread sequence of "+j+",loof of "+i);
		}
		//sub这次任务做完该main
		bShouldSub = false;
		this.notify();
		
	}
	//主线程任务(执行20输出)
	public synchronized void main(int i){
		//永远在while循环里而不是if语句下使用wait。这样，循环会在线程睡眠前后都检查wait的条件
		//if(bShouldSub){
		while(bShouldSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int j=1;j<=20;j++){
			System.out.println("main thread sequence of "+j+",loof of "+i);
		}
		bShouldSub = true;
		this.notify();
	}
	
}
