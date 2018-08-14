package java_basic3;

import java.util.ArrayList;
import java.util.Scanner;

public class DtoTest0 {
	ArrayList<StudDto> list = new ArrayList<>();
	

	public void insertData() {
		StudDto dto = null;
		

		while (true) {
			dto = new StudDto();
			System.out.println("이름입력");
			Scanner sc = new Scanner(System.in);
			String Irum = sc.next();
			dto.setIrum(Irum);
			System.out.println("국어점수입력");
			int Jumsu1 = sc.nextInt();
			dto.setJumsu1(Jumsu1);
			System.out.println("영어점수입력");
			int Jumsu2 = sc.nextInt();
			dto.setJumsu2(Jumsu2);

			list.add(dto);
			System.out.println("계속할까요? y/n 입력");
			String keep=sc.next();
			
			if(keep.equals("n"))
				break;
			
			
			
		}
		
	}

	public void dispData() {

		for (int k = 0; k < 1; k++) {
			StudDto dto = (StudDto) list.get(k);
			System.out.println("이름:" + dto.getIrum() + ",국어점수:" + dto.getJumsu1() + ",영어점수:" + dto.getJumsu2() + ",총점:"
					+ (dto.getJumsu1() + dto.getJumsu2()));
			
		}
		System.out.println("응시인원"+list.size()+"명");
		
	}

	public static void main(String args[]) {
		DtoTest0 test0 = new DtoTest0();
		test0.insertData();
		test0.dispData();

	}

}
