package java_basic3;

import java.util.ArrayList;

public class DtoTest1 {
	//ArrayList<> list = new ArrayList<>();
	ArrayList<StudentDto> list = new ArrayList<>();

	private void aa() {
		String persons[]=new String[3];
		persons[0]="홍길동";
		persons[1]="신길동";
		persons[2]="나길동";
		for(String s:persons) {
			System.out.println(s);
		}
	}
		public void insertData() {//여러 명의 학생정보를 기억
			StudentDto dto= null;
			dto=new StudentDto();
			dto.setHakbun("ks1001");
			dto.setIrum("손오공");
			dto.setJumsu(90);
			list.add(dto);
			
			dto=new StudentDto();
			dto.setHakbun("ks1002");
			dto.setIrum("사오정");
			dto.setJumsu(88);
			list.add(dto);
			
			dto=new StudentDto();
			dto.setHakbun("ks1003");
			dto.setIrum("엥에엥");
			dto.setJumsu(80);
			list.add(dto);
			
		}

	

	public void dispData(){
		System.out.println("저장된 학생수는"+ list.size()+"명");
		
		for(int k=0;k<list.size();k++) {
			//StudentDto dto=(StudentDto)list.get(k);
			StudentDto dto=(StudentDto)list.get(k);
			System.out.println(dto.getHakbun()+" "+dto.getIrum()+" "+dto.getJumsu());
		}
	}

	public static void main(String args[]) {
		DtoTest1 test1 = new DtoTest1();
		test1.aa();
		test1.insertData();
		test1.dispData();

	}

}
