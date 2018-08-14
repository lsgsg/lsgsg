package pack2;

public class Bank {
	private int money = 1000;
	int imsi =1;
	public int imsi2=2;

	public Bank() {//생성자 값을주면 없어도 됌

	}

	public Bank(int money) {
		this.money += money;

	}

	public void dePosit(int amount) {// 입금
		if (amount > 0)
			money += amount;
	}
	public void withDraw(int amount) {//출금
		if((amount>0)&&(money-amount>=0)) {
			money-=amount;
		}else {
			System.out.println("출금액이 너무 많아요");
		}
	}
	
	public int getMoney() {//잔금확인
		return money;
	}

}
