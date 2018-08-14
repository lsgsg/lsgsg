package pack1;

public class Test4 {

	public static void main(String[] args) {
		// 논리 연산자 수행시 주의 사항
		boolean a = true, b = false, c;
		
		c = a || b;
		System.out.println(c);
		c = a && b;
		System.out.println(c);
		
		System.out.println("------------");
		boolean b1, b2;
		//System.out.println(aa());
		b1 = aa();
		System.out.println(b1); 
		System.out.println(bb());
		
		System.out.println("\n주의할 점-----------");
		//b2 = aa() || bb();
		b2 = bb() || aa();
		System.out.println(b2);
		
		System.out.println();
		//b2 = aa() && bb();;
		b2 = bb() && aa();;
		System.out.println(b2);
		
		System.out.println("\n주의할 점 해결?--모든 수행을 원할 시--");
		b2 = aa() | bb();
		System.out.println();
		b2 = bb() | aa();
		System.out.println(b2);
		
		System.out.println(";;;;;;;;;;;;;;;");
		b2 = aa() & bb();
		System.out.println();
		b2 = bb() & aa();
		System.out.println(b2);
		
		System.out.println("종료");
		
	}

	public static boolean aa() { //인자는 없고, 반환값은 boolean
		System.out.println("aa 처리");
		return true;
		
	}
	
	public static boolean bb() { //인자는 없고, 반환값은 boolean
		System.out.println("bb 수행");
		return false;
		
	}
}