import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.print.DocFlavor.STRING;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MapTest extends JFrame {
	private JPanel panel ;
	private JTextField txt1,txt2,txt3,txt4;
	private JButton btnSearch, btnShowMap;
	
	public MapTest() {
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5,5)); // 상 좌 하 우
		panel.setLayout(null);
		setContentPane(panel);
		
		//1행;
		JLabel label = new JLabel("역이름");
		label.setBounds(30,15,90,15);
		panel.add(label);
		
		txt1 = new JTextField();
		txt1.setBounds(100,13,200,20);
		txt1.setColumns(10);
		panel.add(txt1);
		
		btnSearch = new JButton("검색");
		btnSearch.setBounds(350,12,100,21);
		panel.add(btnSearch);
		
		
		//2행;
		JLabel label2 = new JLabel("주소");
		label2.setBounds(30,45,90,15);
		panel.add(label2);
		
		txt2 = new JTextField();
		txt2.setBounds(100,43,200,20);
		txt2.setEditable(false);
		panel.add(txt2);
		
		btnShowMap = new JButton("지도 출력");
		btnShowMap.setBounds(350,42,100,21);
		panel.add(btnShowMap);
		
		//3행;
		JLabel label3 = new JLabel("위도");
		label3.setBounds(30,75,90,15);
		panel.add(label3);
		
		txt3 = new JTextField();
		txt3.setBounds(100,73,200,20);
		txt3.setEditable(false);
		panel.add(txt3);
		
		//4행;
		JLabel label4 = new JLabel("경도");
		label4.setBounds(30,105,90,15);
		panel.add(label4);
		
		txt4 = new JTextField();
		txt4.setBounds(100,103,200,20);
		txt4.setEditable(false);
		panel.add(txt4);
		
		
		
		setBounds(200,200,500,200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//이벤트 처리
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchWord = txt1.getText();
				String searchText = "https://maps.googleapis.com/maps/api/geocode/xml?address=";
				String searchUrl = searchText + searchWord + "&language%20=%20ko";
				
				try {
					URL url = new URL(searchUrl);
					BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
					String line = "";
					String address = "";
					String lat = "";
					String lng = "";
					
					while((line = br.readLine()) != null ) {
						//System.out.println(line);
						if(line.contains("<formatted_address>")) {
							//주소
							//System.out.println("line : " + line);
							line = line.replace("<formatted_address>", "");
							line = line.replace("</formatted_address>", "");
							address = line.trim();
							
						}else if (line.contains("<lat>")) {
							line = line.replace("<lat>", "");
							line = line.replace("</lat>","");
							lat = line.trim();
							
						}else if (line.contains("<lng>")) {
							line = line.replace("<lng>", "");
							line = line.replace("</lng>","");
							lng = line.trim();
							break;
						}
						
					}//end while
					
					
					txt2.setText(address);
					txt3.setText(lat);
					txt4.setText(lng);
					
					br.close();
				} catch (Exception e2) {
					System.out.println("읽기 오류 : " + e2);
				}
			}
		});
		
		btnShowMap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String lat = txt3.getText();
				String lng = txt4.getText();
				String url = "https://www.google.co.kr/maps/@" + lat + "," + lng + ",16z";//",16z" 확대 축소 비율
				//자바에서 브라우저 띄우기; IE 실행
				ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Internet Explorer\\iexplore.exe",url);
				try {
					pb.start(); // 네트워크에서는 thread를 쓰ㄴ는 건 기본;
				} catch (Exception e2) {
					System.out.println("ie 연결 err : " + e2);
				}
			}
		});
		
		
	}
	
	public static void main(String[] args) {
	new MapTest();

	}

}
