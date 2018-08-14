

//UrlTest.java   

import java.io.*;

import java.net.URL;


//태그는 빼고 텍스트들만 읽어드림;
class UrlTest {

	public static void main(String args[]) {

		URL url = null;

		try {

			url = new URL("http://finance.daum.net/");  //웹문서 읽기

			System.out.println("url=" + url);



			InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");

			BufferedReader br = new BufferedReader(isr);

			String readLine = null;

			StringBuffer buffer = new StringBuffer();



			while ((readLine = br.readLine()) != null) {

				buffer.append(readLine);

			}

			br.close();

			//System.out.println(buffer.toString());

			

			HtmlParser htmlParser = new HtmlParser();  //html tag 제거

			String result = htmlParser.getConvData(buffer.toString());

			String regex = "[^\uAC00-\uD7AF\u1100-\u11FF\u3130-\u318F]+"; //[\uAC00-\uD7AF\u1100-\u11FF\u3130-\u318F] 한글만 추출

			//한글 처리:하나 이상에 매칭된다는 의미로 여러 개의 한글이 아닌 문자가 하나의 공백문자로 교체

			System.out.println(result.replaceAll(regex, " "));

			

			FileWriter fw = new FileWriter("mbc.txt"); //정제된 문자열을 파일로 저장

            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(result.replaceAll(regex, " "));

            bw.close();

            System.out.println("저장 완료");

		} catch (IOException e) {

			System.out.println("에러:" + e);

		}

	}

}
