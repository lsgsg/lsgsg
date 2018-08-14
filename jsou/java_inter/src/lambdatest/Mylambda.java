package lambdatest;

public class Mylambda {
	static class Inner implements Myinter{
		@Override
		public int addData(int a, int b) {
			// TODO Auto-generated method stub
			return a+b;
		}
		
	}
	public static void main(String[] args) {
		
		Inner inner = new Inner();//전통적방식
		System.out.println(inner.addData(3,4));
		Myinter myinter = (a,b) -> a + b; // 람다식
		System.out.println(myinter.addData(5, 3));
		
		Myinter myinter2 = (a,b) -> a * b; // 람다식
		System.out.println(myinter2.addData(100, 3));
	}

}
