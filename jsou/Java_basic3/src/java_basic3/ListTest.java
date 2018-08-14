package java_basic3;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		// List Type의 Arraylist : 순서가 있고 중복을 허용 함.
		ArrayList<String> list = new ArrayList<>(); // ArrayList를 사용하고 String을 허용 함.
		list.add("kim");
		list.clear();
		list.add("kim");
		list.add("lee");
		list.add("kim");
		list.add("park");
		list.add("choi");
		
		System.out.println(list.size());
		System.out.println(list.get(1)); // list에 있는 1번째 있는 것을 보여줌.
		
		//list.remove(1); // list에 있는 1번째 것이 지워짐. 이것도 밑에라인도 둘 다 사용.
		//list.remove("lee");
		
		System.out.println(list.contains("kim")); // contains : 있는지 없는지 확인. 맞으면 true, 없으면 false.
		System.out.println(list.indexOf("park")); // indexof : 찾는 내용에 인덱스 번호를 확인 할 수 있음.
		
		System.out.println();
		pr1(list);
		System.out.println();
		pr2(list);
		
	}
	
	public static void pr1(List<String> list) { // 아규먼트에는 리스트, 배열리스트<타입> 타입변수
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	public static void pr2(ArrayList<String> list) {
		for(String k:list) { // 향상된 for(타입 변수이름:배열이나 컬렉션)
			System.out.println(k);
		}
	}

}
