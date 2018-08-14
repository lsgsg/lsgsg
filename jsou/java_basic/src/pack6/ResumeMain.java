package pack6;

public class ResumeMain {

	public static void main(String[] args) {
		R_Tom tom=new R_Tom();
		tom.setIrum("톰");
		tom.setPhone("111-1111");
		tom.setJuso("강남구 테헤란로 111");
		tom.print();
		
		System.out.println();
		tom.playJava(false);
		//tom.chageData(); 못부름
		Resume.changeData();
		
		System.out.println("-------------");
		R_James james=new R_James();
		james.setIrum("제임스");
		james.setPhone("123-1234");
		james.setSkill("웹 전문가");
		james.print();
		
		System.out.println("\n인사 담당자가 이력서 보기------");
		System.out.println();
		
		Resume[] resBox=new Resume[2];
		resBox[0]=tom;
		resBox[1]=james;
		
		for(Resume kbs:resBox) {
			kbs.print();
			
			System.out.println("다음 지원자 정보**************");
		}

	}

}
