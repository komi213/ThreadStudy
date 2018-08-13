package com.study.thread;

/**
 * 第三课：线程同步
 * @author komi
 *
 */
public class TraditionalThreadSync {
	public static void main(String[] args) {
		//在static方法中不能new内部类实例对象
		//static方法运行时,可以没有外部类对象,而new内部类时可以调用外部类对象，
		//这时没有外部类对象。
		new TraditionalThreadSync().init();
	}
	private void init(){
		final Outputer output = new Outputer();
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					output.output("zhanxiaoxiang");
				}
				
			}
			
		}).start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					output.output("lihuoming");
				}
			}
			
		}).start();
	}
	static class Outputer{
		//output1与output2都是锁类对象（new Outputer()）,可同步
		//锁this对象(类对象在类自己内部用this表示)
		public void output(String name){
			synchronized (this) {
				for(int i=0,len = name.length();i<len;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}
		
		//锁this对象
		public synchronized void output2(String name){
			for(int i=0,len = name.length();i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		//output3与output4都是锁字节码（Outputer.class）,可同步
		//锁字节码（Outputer.class）
		public void output3(String name){
			synchronized (Outputer.class) {
				for(int i=0,len = name.length();i<len;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
			
		}
		//加static，也是锁字节码（Outputer.class）
		public static synchronized void output4(String name){
			for(int i=0,len = name.length();i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
	}
}
