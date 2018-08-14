package java_basic3;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetTest {//중복이없고 순서도없음
	public static void main(String[] args) {
		// 컬렉션은 자료구조를 의미하고 배열과 비슷한 구조를 가짐. 기존배열 보완
		// 컬렉션 HashSet<Object>
		HashSet<Object> list = new HashSet<>(); // <> 안에 들어가는 객체타입만 담아 둠. object는 모든 것을 담아줌.
		list.add("lee"); // add는 내용 추가. string 들어감.
		list.add(12); // int로 들어감.
		TryTest test = new TryTest();
		list.add(test);
		System.out.println(list.size()); // 컬렉션 list에 3개가 들어가 있기 때문에 3을 출력.
		
		list.clear(); // 컬렉션 내에 모든 내용을 지움
		System.out.println(list.size());
		
		list.add("lee");
		list.add("kim");
		list.add("choi");
		list.add("han");
		list.add("han"); // 중복을 허용하지 않음
		System.out.println(list.size()); // 중복이 하나 있기 때문에 list size는 4가 되는 것.
		
		list.remove("kim"); // 컬렉션내에 list 내용 하나를 지움.
		System.out.println(list.size()); 
		System.out.println(list);
		//System.out.println(list.toArray()); // 주소값 보여 주기.
		
		myprint(list); // set 타입
		myprint(list.toArray()); // object arr타입
		
	}
	
	
	
	public static void myprint(Set set) { // set 타입
		StringBuffer sb = new StringBuffer(); // 스트링 버퍼
		String imsi = null;
		
		Iterator<Object> iter = set.iterator();
		while(iter.hasNext()) {
			String ss = (String)iter.next();
			System.out.println(ss);
			
			
			//imsi += ss; // 문자열 더하기. 비권장. 속도가 떨어짐.
			sb.append(ss); // 문자열 더하기. 권장. 속도가 빠름.
			sb.append(" ");
		}
		
		System.out.println(sb.toString()); // 문자열 더하기 출력
	}
	
	public static void myprint(Object[] obj) { // object arr타입
		for(int i = 0; i < obj.length; i++ ) {
			System.out.println(obj[i]);
		}

	}
}
