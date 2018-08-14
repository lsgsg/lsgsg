package db.pack1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Dbtest_gui extends JFrame implements ActionListener {
	JButton btnAll = new JButton("전체");
	JButton btnM = new JButton("남");
	JButton btnF = new JButton("여");
	JTextArea txtResult = new JTextArea();
	
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	
	public Dbtest_gui() {
		setTitle("고객 테이블 출력");
		
		layInit();
		accDb();
		setBounds(200,200,300,250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","scott", "tiger");
			stmt = conn.createStatement();
			String sql = "select gogek_no, gogek_name, gogek_jumin from gogek ";
			
			if(e.getSource() == btnAll ) { 
				
			}else if(e.getSource() == btnM){
				sql += "where gogek_jumin like '%-1%'";
			}else if(e.getSource() == btnF) {
				sql += "where gogek_jumin like '%-2%'";
			}
			txtResult.setText("비웠음\n");// or "" ; 출력 대상 자료를 깨끗하게 비워준다. 
			rs = stmt.executeQuery(sql);
			int cou = 0;
			while(rs.next()) {
				System.out.println(rs.getString(2));
				String str = rs.getString("gogek_no") + "\t" + 
							 rs.getString("gogek_name") + "\t" + 
							 rs.getString("gogek_jumin")+"\n";
				txtResult.append(str);
				cou++;
			}
			txtResult.append("인원수 : " + cou + "명");
		} catch (Exception e2) {
			System.out.println("actionPerformed err : " + e2);
		}finally {
			try {
				if(rs != null)rs.close();
				if(stmt != null )stmt.close();
				if(conn != null)conn.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	private void layInit() {
		JPanel panel = new JPanel();
		panel.add(btnAll);
		panel.add(btnF);
		panel.add(btnM);
		
		txtResult.setEditable(false);// 읽기기능만 가능하게 read only;
		JScrollPane pane = new JScrollPane(txtResult);
		
		add("Center",pane);
		add("North",panel);
		
		btnAll.addActionListener(this);
		btnM.addActionListener(this);
		btnF.addActionListener(this);
	}
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}
		
	}
	
	
	
	
	public static void main(String[] args) {
		new Dbtest_gui();
	}
}
