package db.pack1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DbPstmt extends JFrame implements ActionListener{
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	JTextField txtCo, txtSang, txtSu, txtDan;
	JTextArea txtSel;
	JButton btnUp;
	
	
	public DbPstmt() {
		setTitle("상품처리");
		
		layinit(); // Frame Layout
		
		select(); // DB 연결 및 SangData 출력
		
		setBounds(300, 300, 370, 300);
		setVisible(true);
		setResizable(false);
		
		// 종료
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int re = JOptionPane.showConfirmDialog(DbPstmt.this, "정말로 종료 할까요?", "종료", JOptionPane.YES_NO_OPTION);
				if(re == JOptionPane.YES_OPTION) {
					try {
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();						
						setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					} catch (Exception e2) {
						System.out.println("DB Close Err : " + e2.getMessage());
					}
				} else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
				
			}
		});
		
		
	}
	
	private void layinit() {
		
		setLayout(new FlowLayout());
		
		txtCo = new JTextField("",10); // 코드
		txtSang = new JTextField("",10); // 상품명
		txtSu = new JTextField("",10); // 수량
		txtDan = new JTextField("",10); // 단가
		
		// 코드, 품명 추가
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("코드"));
		panel1.add(txtCo);
		panel1.add(new JLabel("품명"));
		panel1.add(txtSang);
		add(panel1);
		
		// 수량, 단가 추가
		JPanel panel2 = new JPanel();
		panel2.add(new JLabel("수량"));
		panel2.add(txtSu);
		panel2.add(new JLabel("단가"));
		panel2.add(txtDan);
		add(panel2);
		
		// 추가 버튼
		JPanel panel3 = new JPanel();
		btnUp = new JButton("추가");
		panel3.add(btnUp);
		add("Center", panel3);
		
		// SangData 출력 Text
		txtSel = new JTextArea(8,30);
		JScrollPane pane = new JScrollPane(txtSel);
		JPanel panel4 = new JPanel();
		panel4.add(pane);
		add("North", panel4);
		
		btnUp.addActionListener(this);
	}
	
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch (Exception e) {
			System.out.println("DB 에러 : " + e);
		} 
	}
	
	private void select() {
		try {
			accDb();
			// SangData 출력 SQL
			String sql = "select * from sangdata order by code";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// SangData 출력
			int cou = 0;
			txtSel.append("코드" + "\t" + "상품명" + "\t" + "수량" + "\t" + "단가" + "\n");
			while (rs.next()) {
				String str = (rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
						+ "\n");
				txtSel.append(str);
				cou++;
			}
			txtSel.append("건수 : " + cou);
		} catch (Exception e) {
			System.out.println("DB Select err :" + e);
		} finally {
			try {
				if (rs != null)
					pstmt.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnUp) {
			// 코드 Text 비었을 때
			if(txtCo.getText().equals("")) { 
				JOptionPane.showMessageDialog(this, "코드를 입력하시오"); 
				txtCo.requestFocus(); 
				return;
			}
			// 상품명 Text 비었을 때
			if(txtSang.getText().equals("")) { 
				JOptionPane.showMessageDialog(this, "상품명을 입력하시오"); 
				txtSang.requestFocus();
				return;
			}
			// 수량 Text 비었을 때
			if(txtSu.getText().equals("")) { 
				JOptionPane.showMessageDialog(this, "수량을 입력하시오"); 
				txtSu.requestFocus(); 
				return;
			}
			// 단가 Text 비었을 때
			if(txtDan.getText().equals("")) { 
				JOptionPane.showMessageDialog(this, "단가를 입력하시오"); 
				txtDan.requestFocus(); 
				return;
			}
			
			// 코드, 수량, 단가 숫자만 허용
			try {
				Integer.parseInt(txtCo.getText());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "코드는 숫자만 허용");
				txtCo.requestFocus();
				return;
			}
			
			try {
				Integer.parseInt(txtSu.getText()); 
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "수량은 숫자만 허용");
				txtSu.requestFocus();
				return;
			}
			
			try {
				Integer.parseInt(txtDan.getText()); 
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "단가는 숫자만 허용");
				txtDan.requestFocus();
				return;
			}
			
			insert();
			
			// 결과 출력 후 Text 비움
			txtCo.setText("");
			txtSang.setText("");
			txtSu.setText("");
			txtDan.setText("");
			
			
		}
	}
	
	private void insert() {
			
		// SangData Update SQL 및 코드 중복
		try {
			accDb();
			
			String sql = "select count(*) from sangdata where code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(txtCo.getText()));
			rs = pstmt.executeQuery();
			rs.next();
			if(Integer.parseInt(rs.getString(1)) > 0) {
				JOptionPane.showMessageDialog(this, "코드가 중복되었습니다", "경고", JOptionPane.ERROR_MESSAGE);
				txtCo.requestFocus();
				return;
			}
			
			String isql = "insert into sangdata values(?,?,?,?)";
			pstmt = conn.prepareStatement(isql);
			pstmt.setInt(1, Integer.parseInt(txtCo.getText()));
			pstmt.setString(2, txtSang.getText());
			pstmt.setInt(3, Integer.parseInt(txtSu.getText()));
			pstmt.setInt(4, Integer.parseInt(txtDan.getText()));
			pstmt.executeUpdate();
		} catch (Exception e2) {
			System.out.println("Insert Err : " + e2);
		} finally {
			try {
				if (rs != null)	pstmt.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
				txtSel.setText(null);
				select();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	public static void main(String[] args) {
		new DbPstmt();
	}
}
