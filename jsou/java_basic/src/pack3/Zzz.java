package pack3;

//public class Zzz extends Object{//그냥 일반 클래스선언과 같음
public class Zzz {
	public int aa = 10;

	@Override
	public String toString() {
		return "푸하하하하";
	}

	public static void main(String[] args) {
		Zzz zz = new Zzz();
		System.out.println(zz);
		System.out.println(zz.toString());
	}

}
