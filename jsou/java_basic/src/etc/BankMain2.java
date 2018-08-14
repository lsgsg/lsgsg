package etc;

//import pack2.Bank;
//import pack2.Animal;
import pack2.*;
import java.lang.System;
import java.lang.String;
import java.util.Scanner;


public class BankMain2 {

	public static void main(String[] args) {
		// pack2.Bank tom2=new pack2.Bank();
		Bank tom2 = new Bank();
		Animal ani=new Animal();
		
		System.out.println("public member는 project내에서 참조 가능");
		System.out.println(tom2.imsi2); //O public 접근 지정자
		//System.out.println(tom2.imsi); //X default 접근 지정자
		Scanner sc = new Scanner(System.in);

	}

}
