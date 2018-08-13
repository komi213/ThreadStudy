package com.study.tool.ThreadTest;

import java.util.concurrent.Callable;

public class MyCallTask implements Callable<Integer>{
	private int upperBounds;
	public MyCallTask(int upperBounds) {
		this.upperBounds = upperBounds;
	}
	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for(int i=1;i<=upperBounds;i++){
			sum += 1;
		}
		return sum;
	}
	/*Java 5以前实现多线程有两种实现方法：一种是继承Thread类；另一种是实现Runnable接口。
	两种方式都要通过重写run()方法来定义线程的行为，推荐使用后者，因为Java中的继承是单继承，一个类有一个父类，
	如果继承了Thread类就无法再继承其他类了，显然使用Runnable接口更为灵活。
	补充：Java 5以后创建线程还有第三种方式：实现Callable接口，该接口中的call方法可以在线程执行结束时产生一个返回值，*/
	/*
	 * (1)Callable规定的方法是call(),Runnable规定的方法是run().
		(2)Callable的任务执行后可返回值，而Runnable的任务是不能返回值得
		(3)call方法可以抛出异常，run方法不可以
		(4)运行Callable任务可以拿到一个Future对象，表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，
		并检索计算的结果。通过Future对象可以了解任务执行情况，可取消任务的执行，还可获取执行结果。
	 */
	
}
