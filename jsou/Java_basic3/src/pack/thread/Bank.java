package pack.thread;

public class Bank {
	private int money=10000;//공유자원

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public synchronized void saveMoney(int save) {//입금
		int m =this.getMoney();
		try {
			Thread.sleep(2000);
		}catch(Exception e) {
			
		}
		this.setMoney(m+save);
	}
	
	public synchronized void minusMoney(int mon) {//출금
		int m =this.getMoney();
		try {
			Thread.sleep(3000);
		}catch(Exception e) {
			
		}
		this.setMoney(m-mon);
	}

}
