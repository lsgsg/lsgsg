package db.pack1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesRead {

	public static void main(String[] args) {
		Properties properties = new Properties();
		
		try {
			properties.load( new FileInputStream("C:\\work\\jsou\\java_inter\\src\\db\\pack1\\ex1.properties"));
			System.out.println(properties.getProperty("mes1"));
			System.out.println(properties.getProperty("mes2"));
			System.out.println(properties.getProperty("mes3"));
		} catch (Exception e) {
			System.out.println("읽기 실패 : " + e );
		}
		
		//write
		try {
			
			properties.setProperty("mes1", "nice");
			properties.setProperty("mes2", "good");
			properties.setProperty("mes3", "hello");
			properties.store( new FileOutputStream("C:\\work\\jsou\\java_inter\\src\\db\\pack1\\ex1.properties"), null);
			System.out.println("저장성공");
		} catch (Exception e) {
			System.out.println("쓰기 실패 : " + e );
		}

	}

}
