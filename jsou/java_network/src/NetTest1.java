

import java.net.InetAddress;

public class NetTest1 {

	public static void main(String[] args) {
		InetAddress ia;
		InetAddress ia2[];
		
		try {
			ia = InetAddress.getByName("www.naver.com");//www.naver.com/210.89.164.90
			System.out.println(ia);
			System.out.println(ia.getHostAddress());//ip얻기
			System.out.println(ia.getHostName());//  도메인 얻기;
			
			System.out.println("==============");
			
			ia = InetAddress.getLocalHost();
			
			System.out.println(ia);
			System.out.println(ia.getHostAddress());// 내 컴퓨터의 ip얻기
			System.out.println(ia.getHostName());// 내 컴퓨터의 도메인 얻기;
			System.out.println();
			
			ia2 = InetAddress.getAllByName("www.daum.net");
			System.out.println(ia2.length); // 2개의 주소를 얻어냄
			
			for (InetAddress i : ia2) {
				System.out.println(i.getHostAddress());// 내 컴퓨터의 ip얻기
				System.out.println(i.getHostName());
			}

		} catch (Exception e) {
			System.out.println("err: " + e);
		}

	}

}
