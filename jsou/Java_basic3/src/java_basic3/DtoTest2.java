package java_basic3;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DtoTest2 {
	ArrayList<HaksaengDto> list;
	

	public DtoTest2() {
		list = new ArrayList<HaksaengDto>();
	}

	public void inputData(String[] datas) {
		// HaksaengDto dto;

		for (int i = 0; i < datas.length; i++) {
			// System.out.println(datas[i]);
			StringTokenizer tok = new StringTokenizer(datas[i], ",");
			String irum = tok.nextToken();
			int kor = Integer.parseInt(tok.nextToken());
			int eng = Integer.parseInt(tok.nextToken());
			int mat = Integer.parseInt(tok.nextToken());

			HaksaengDto dto = new HaksaengDto();
			dto.setName(irum);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			list.add(dto);

		}

	}

	public void printData() {// 각 학생의 이름, 총점, 평균 출력
		System.out.println("이름\t총점\t평균");

		for (HaksaengDto hak : list) {
			int tot = hak.getKor() + hak.getEng() + hak.getMat();
			int avg = tot / 3;
			System.out.println(hak.getName() + "\t" + tot + "\t" + avg);
			
			
		}

	}

	public static void main(String[] args) {

		// String ss="kbs,mbc,sbs";
		// StringTokenizer tok=new StringTokenizer(ss,",");
		// System.out.println(tok.nextToken());
		// String s=tok.nextToken();
		// System.out.println(s);

		String[] datas = new String[3];
		datas[0] = "신선해,100,100,100";
		datas[1] = "신기루,80,70,60";
		datas[2] = "신기한,80,88,77";

		DtoTest2 test2 = new DtoTest2();
		test2.inputData(datas);
		test2.printData();

	}

}
