package pack7;

public class Outer {
	private int a;
	private Inner inner;
	
	public Outer() {
		a=10;
		Inner inn=new Inner();
		
	
	}
	
	public void aa() {
		System.out.println("외부에 있는 aa 메소드");
		bb();
		inner.cc();
	}
	
	private void bb() {
		System.out.println("외부에 있는 bb 메소드");
	}
	
	public class Inner {//내부 클래스
		int b;
		
		public Inner() {
			b=20;
		}
		void cc() {
			System.out.println("내부에 있는 cc 메소드");
			System.out.println("a:"+a+", b:"+b);
			bb();
			
		}
		class InnerInner{
			
		}
		
	}
	
	public static void main(String[] args) {
		Outer outer=new Outer();
		outer.aa();
		Outer.Inner inn=outer.new Inner();
		inn.cc();
		
		outer.inner.cc();
	}

}
