package pack.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class IoTest0 {

	public static void main(String[] args) throws Exception {

		File f = new File("c:\\work\\lib.txt");
		FileReader fread = new FileReader(f);
		BufferedReader bread = new BufferedReader(fread);
		
		int count=0;

		while (true) {
			count++;
			String ss = bread.readLine();
			if(ss==null||count>30)break;
			
			String[]arrOfStr=ss.split(",");
			
			System.out.println("도서관명:"+arrOfStr[0]);
			System.out.println("시도명:"+arrOfStr[1]);
			System.out.println("도서관유형:"+arrOfStr[3]);
			System.out.println("자료수:"+arrOfStr[12]);
			System.out.println("전화번호:"+arrOfStr[19]);
			System.out.println("주소:"+arrOfStr[17]);
			
			
			
			
			
		}
		bread.close();
		fread.close();

	}

}//char [] check = ss.toCharArray();
