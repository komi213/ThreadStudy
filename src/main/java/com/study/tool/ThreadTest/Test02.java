package com.study.tool.ThreadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test02 {
	public static void main(String[] args) {
		//测试:Java 5以后创建线程还有第三种方式：实现Callable接口，该接口中的call方法可以在线程执行结束时产生一个返回值
		List<Future<Integer>> list = new ArrayList<>();
		ExecutorService service = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++){
			int randomInt = (int) (Math.random() * 10);//[0-10)随机整数
			System.out.println("随机整数:"+randomInt);
			list.add(service.submit(new MyCallTask(randomInt)));
		}
		service.shutdown();
		for(Future<Integer> future:list){
			try {
				System.out.println(future.get());//获取结果。如果结果还没有准备好，get方法会阻塞直到取得结果
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
