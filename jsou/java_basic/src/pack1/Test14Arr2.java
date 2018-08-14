package pack1;

public class Test14Arr2 {

	public static void main(String[] args) {
		// 다차원 배열 - 2차원 배열은 1차원 배열의 묶음
		int su[][] = new int[3][4];// 3*4 2차원 배열
		System.out.println(su.length + " " + su[0].length + " " + su[1].length);
		su[0][0] = 100;
		System.out.println(su[0][0]);

		int num = 10;
		for (int i = 0; i < su.length; i++) {
			for (int j = 0; j < su[i].length; j++) {
				su[i][j] = num++;
			}
		}
		// System.out.println(su[0][0]);
		for (int i = 0; i < su.length; i++) {
			for (int j = 0; j < su[i].length; j++) {
				System.out.print(su[i][j] + " ");
			}
		}
		System.out.println();

		// 가변배열
		int[][] scores = new int[2][];
		scores[0] = new int[2];
		scores[1] = new int[3];
		System.out.println(scores.length + " " + scores[0].length + " " + scores[1].length);

		System.out.println();
		int[][] jum = { { 90, 96 }, { 88, 92 } };// 2*2
		for (int i = 0; i < jum.length; i++) {
			for (int j = 0; j < jum[i].length; j++) {
				System.out.print(jum[i][j] + " ");
			}
			System.out.println();

		}
		int[][] jum2 = { { 90, 96 }, { 88, 92, 77 } };// 가변
		for (int i = 0; i < jum2.length; i++) {
			for (int j = 0; j < jum2[i].length; j++) {
				System.out.print(jum2[i][j] + " ");
			}
			System.out.println();
		
			
		}

	}
}
