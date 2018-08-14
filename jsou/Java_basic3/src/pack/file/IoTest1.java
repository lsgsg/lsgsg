package pack.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class IoTest1 {

	public static void main(String[] args) throws IOException{
		//1byte 단위의 입출력
		System.out.println("1바이트 입력:");
		int a= System.in.read();
		System.out.println("입력값a:"+a+" "+(char)a);
		
		//문자열 출력
		OutputStreamWriter os=new OutputStreamWriter(System.out);
//		byte b=97;
//		os.write(b);
//		os.flush();
		
		//Buffer:입출력 성능 향상
//		BufferedWriter bw=new BufferedWriter(os, 1024);
//		PrintWriter out = new PrintWriter(bw);
//		out.println(123);
//		out.println("오늘은 금요일");
//		out.close();
//		bw.close(); //자원 해제:메모리반납 - GC
		
		System.out.println("파일로 출력");
		File f=new File("c:/work/iotest.txt");
		FileWriter fw=new FileWriter(f);
		BufferedWriter bw2=new BufferedWriter(fw,1024);
		PrintWriter out2=new PrintWriter(bw2);
		out2.println(1234);
		out2.println("문자열이 출력됨");
		out2.println("Stream을 사용함");
		out2.close();
		bw2.close();
		fw.close();
		
		System.out.println("파일 출력 성공");
			

	}
	

}
