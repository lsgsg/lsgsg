package lambdatest;

@FunctionalInterface //추상메소드 1개만 사용하도ㄱ록 강요
public interface Myinter {
	int addData(int a, int b);
//	int addData(int a, int b);@FunctionalInterface 한 순간, 인터페이스의 추상메소드를 한개만 써야함
}
