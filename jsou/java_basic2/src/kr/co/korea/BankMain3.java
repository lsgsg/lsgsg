package kr.co.korea;

import pack2.Bank;

public class BankMain3 {
	

	public static void main(String[] args) {
		Bank tom3=new Bank();
		tom3.withDraw(500);
		tom3.dePosit(1500);
		System.out.println("잔고는 "+tom3.getMoney());

	}

}
