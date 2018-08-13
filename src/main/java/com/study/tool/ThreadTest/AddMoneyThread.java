package com.study.tool.ThreadTest;

/**
 * 存钱线程类
 * @author komi
 *
 */
public class AddMoneyThread implements Runnable{
	private Account account;//存入帐户
	private double money;//存入金额
	public AddMoneyThread(Account account,double money){
		this.account = account;
		this.money = money;
	}
	
	@Override
	public void run() {
		//2种方案：在线程调用存款方法时对银行账户进行同步
		/*synchronized (account) {
			account.deposit(money);
		}*/
		account.deposit(money);
	}

}
