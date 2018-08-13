package com.study.tool.ThreadTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 下面的例子演示了100个线程同时向一个银行账户中存入1元钱，在没有使用同步机制和使用同步机制情况下的执行情况。
 * @author komi
 *
 */
//银行账户
public class Account {
	private Lock accountLock = new ReentrantLock();
	private double balance;//帐户余额
	/**
	 * 存款
	 * @param money 存入金额
	 */
	//第1种方案.在银行账户的存款（deposit）方法上同步（synchronized）关键字
	/*public synchronized void deposit(double money){
		double newBalance = balance + money;
		try {
			Thread.sleep(10);//模拟此业务需要一段处理时间
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		balance = newBalance;
	}*/
	
	//2种方案：在线程调用存款方法时对银行账户进行同步
	/*synchronized (account) {
		account.deposit(money);
	}*/
			
	//第3种方案通过Java 5显示的锁机制，为每个银行账户创建一个锁对象，在存款操作进行加锁和解锁的操作
	public void deposit(double money){
		accountLock.lock();
		try {
			double newBalance = balance + money;
			try {
				Thread.sleep(10);//模拟此业务需要一段处理时间
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			balance = newBalance;
		}finally{
			accountLock.unlock();//写个try{}finally{}保证锁出现异常也会释放
		}
	}
	/**
     * 获得账户余额
     */
    public double getBalance() {
        return balance;
    }
}
