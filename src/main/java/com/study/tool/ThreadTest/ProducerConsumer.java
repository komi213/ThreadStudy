package com.study.tool.ThreadTest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * java多线程模拟生产者消费者问题
 * ProducerConsumer是主类，Producer生产者，Consumer消费者，Product产品，Storage仓库
 * @author komi
 */
public class ProducerConsumer {
	public static void main(String[] args) {
		
		ProducerConsumer pc = new ProducerConsumer();
		Storage s = pc.new Storage();
		
		ExecutorService service = Executors.newCachedThreadPool();

		Producer p = pc.new Producer("张三", s);
		Producer p2 = pc.new Producer("李四", s);
		
		Consumer c = pc.new Consumer("王五", s);
        Consumer c2 = pc.new Consumer("老刘", s);
        Consumer c3 = pc.new Consumer("老林", s);
        /*
         * 方法execute()没有返回值，而submit()方法可以有返回值（通过Callable和Future接口）
		 *方法execute()在默认情况下异常直接抛出（即打印堆栈信息），不能捕获，但是可以通过自定义ThreadFactory的方式进行捕获（通过setUncaughtExceptionHandler方法设置），而submit()方法在默认的情况下可以捕获异常
		*方法execute()提交的未执行的任务可以通过remove(Runnable)方法删除，而submit()提交的任务即使还未执行也不能通过remove(Runnable)方法删除
		*/
        service.submit(p);
        //service.submit(p2);
        service.submit(c);
        service.submit(c2);
        service.submit(c3);
	}
	/**
	 * 消费者
	 * @author komi
	 *
	 */
	class Consumer implements Runnable{
		private String name;
		private Storage s = null;
		public Consumer(String name,Storage s){
			this.name = name;
			this.s = s;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while(true){
					System.out.println(name+"准备消费产品.");
					Product product = s.pop();
					System.out.println(name+"已消费("+product.toString()+").");
					System.out.println("===============");
					Thread.sleep(500);
				}
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 生产者
	 * @author komi
	 *
	 */
	class Producer implements Runnable{
		private String name;
		private Storage s = null;
		public Producer(String name,Storage s){
			this.name = name;
			this.s = s;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while(true){
					Product product = new Product((int) (Math.random() * 10000)); // 产生0~9999随机整数
					System.out.println(name+"准备生产("+product.toString()+").");
					s.push(product);
					System.out.println(name+"已生产("+product.toString()+").");
					System.out.println("===============");
					Thread.sleep(500);
				}
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 仓库，用来存放产品
	 * @author komi
	 *
	 */
	public class Storage{
		BlockingQueue<Product> queues = new LinkedBlockingQueue<Product>(2);
		/**
		 * 生产
		 * @param p 产品
		 * @throws InterruptedException 
		 */
		public void push(Product p) throws InterruptedException{
			queues.put(p);//put方法在队列满的时候会阻塞直到有队列成员被消费
		}
		/**
		 * 消费
		 * @return 产品
		 * @throws InterruptedException
		 */
		public Product pop() throws InterruptedException{
			return queues.take();//take方法在队列空的时候会阻塞，直到有队列成员被放进来
		}
	}
	
	/**
	 * 产品
	 */
	public class Product{
		private int id;
		public Product(int id){
			this.id = id;
		}
		public String toString(){// 重写toString方法
			return "产品:"+this.id;
		}
	}
}
