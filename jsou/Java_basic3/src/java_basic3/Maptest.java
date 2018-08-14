package java_basic3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Maptest {
	public static void main(String[] args) {
		HashMap<String, String> list = new HashMap<>(); // <key, value> 키와 값을 어떤 타입으로 설정할 것인지.
		
		list.put("0","lee");
		list.clear();
		
		list.put("0","lee");
		list.put("1","lee1");
		list.put("2","lee2");
		list.put("3","lee3");
		list.put("4","lee4");
		list.put("5","lee5");
		list.put("2","park"); // 같은 키 값은 덮어 쓰게 됨. 2번은 lee1에서 park으로 바뀜.
		System.out.println(list.size());
		System.out.println(list.containsKey("1")); // containsKey : 키값에 값이 있는지 없는지 확인.
		System.out.println(list.containsValue("lee2")); // containsValue : 입력한 값과 일치하는 값이 있는 확인.
		list.remove("0");
		
		System.out.println("-----------");
		
		display(list);
		
	}
	
	public static void display(Map map) { // hashmap는 map에 서브클래스
		Set set = map.keySet(); // set 타입으로 넘김.
		Iterator iter = set.iterator(); // set을 Iterator로 넘김.
		while(iter.hasNext()) { // hash에 있는 것.
			String key = (String)iter.next(); // 키값이 int이기 때문에 String으로 캐스팅하여 함.
			System.out.println(key + " " + map.get(key)); 
		}
	}
}
