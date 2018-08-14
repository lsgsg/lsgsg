package java_basic3;

import java.util.Vector;

public class VectorTest {
	public static void main(String[] args) {
		//Vector<Object> v = new Vector<>(5, 3); // 선언도 가능
		Vector<Object> v = new Vector<>();
		v.add('a'); // auto boxing : 기본형을 객체로 변환해서 저장함으로 아무타입이나 집어 넣을 수 있으나 컬렉션에 들어오는 타입을 설정 하여 막을 수 있음.
		v.addElement("홍길동");
		v.add(100);
		v.add(12.3456);
		Vector vector = new Vector<>();
		v.add(vector);
		System.out.println("백터 크기는 " + v.size());
		
		for(Object k:v) {
			System.out.println(k);
		}
	}
}
