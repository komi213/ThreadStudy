package com.study.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author komi
 * @deprecated 线程第二课: 传统定时器
 *
 */
public class TraditionalTimerTest {
	private static int count = 0;
	public static void main(String[] args) {
		
		/*new Timer().schedule(new TimerTask(){
			@Override
			public void run() {
				System.out.println("bombing");
			}
		}, 10000,3000);*///10后执行第一次，之后每隔3秒执行一次
		
		//实现 2秒一次，再4秒一次。再2、4，2、4。
		class MyTimerTask extends TimerTask{

			@Override
			public void run() {
				// TODO Auto-generated method stub
				count = (count+1)%2;
				System.out.println("bombing!");
				new Timer().schedule(new MyTimerTask(), 2000+2000*count);
			}
			
		}
		new Timer().schedule(new MyTimerTask(), 2000);
		
		while(true){
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
