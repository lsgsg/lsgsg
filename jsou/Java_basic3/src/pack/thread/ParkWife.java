package pack.thread;

public class ParkWife extends Thread{
	@Override
	public void run() {
		
		BankMain.myBank.minusMoney(2000);
		System.out.println("아내 출금 후 잔고:"+BankMain.myBank.getMoney());
	}

}
