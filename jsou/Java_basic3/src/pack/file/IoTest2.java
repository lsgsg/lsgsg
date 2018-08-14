package pack.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class IoTest2 {
	public static void main(String[] args) throws Exception{
		//Console을 통한 입력
//		InputStreamReader in=new InputStreamReader(System.in);
//		BufferedReader br=new BufferedReader(in);
//		System.out.println("이름입력:");
//		String irum=br.readLine();
//		System.out.println("나이입력");
//		String nai=br.readLine();
//		System.out.println("이름은 "+irum+", 나이는 "+nai);
//		br.close(); in.close();
		
//		Scanner scanner=new Scanner(System.in);
//		System.out.println("이름입력:");
//		String ir=scanner.next();
//		System.out.println("나이입력:");
//		//int nai=scanner.nextInt();
//		double nai=scanner.nextDouble()
//		System.out.println("이름은 "+irum+", 나이는 "+nai);
		
		System.out.println("파일 읽기");
		File f =new File("c:\\work\\lib.txt");
		FileReader fr=new FileReader(f);
		BufferedReader br=new BufferedReader(fr);
		
		while(true) {
			String ss=br.readLine();//한 줄 읽기
			if(ss.contains("서울")||ss.equals(null))break;
			//if(ss==null)break;
			System.out.println(ss);
		}br.close(); fr.close();
	}

}
