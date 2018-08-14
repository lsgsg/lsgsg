package java_store_pack;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import java.util.Date;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;





public class Java_banpum extends JFrame implements ActionListener {
	JButton btnSale, btnBan;
	JTextField addSang1, addSang2, addSang3, addSang4, addSang5;
	JTextField okSang1,okSang2,okSang3,okSang4,okSang5,okSang6,okSang7;
	JTextField billNum;
	JButton btnOk, btnAdd , btnPegi;
	JTextArea billArea = new JTextArea(40,25);
	JButton gogekCf;
	JRadioButton gogekY,gogekN;
	JTextField gogekNum;
	
	JButton bClose,bill,btnNew;
	JTextField totF,pointF,coinF;
	int iLast = 0; 	// 마지막 레코드 번호
	int toTal = 0;
	Connection conn,conn2,conn3;
	PreparedStatement pstmt,pstmt2,pstmt3;
	ResultSet rs1;
	ResultSet rs2,rs3;
	

	String sql,sql2,sql3 ;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOk ) { //반품상품확인
			try {
				billArea.setText(null);
				System.out.println("반품확인");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
				sql = "select b_irum,count(*) as su,b_hang ,b_panme,b_bill from bill group by b_irum , b_panme,b_bill,b_hang having b_bill = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, addSang1.getText());
				rs1 = pstmt.executeQuery();
				
				toTal = 0;
				int count=0;
				while(rs1.next()) {
					count++;
					System.out.println(count);
					int suNum = Integer.parseInt(rs1.getString("su"));
					int price = Integer.parseInt(rs1.getString("b_panme"));
					int calc = suNum * price;
					String str = "상품명 : " + rs1.getString("b_irum") + "\n" +
							     "수량 : " + rs1.getString("su") + "\n" +
							     "행사 : " + rs1.getString("b_hang") + "\n" +
							     "가격 : " + calc + "\n\n";
					toTal += calc;    
					billArea.append(str);
					
				}
				if(count == 0 ) {
					JOptionPane.showMessageDialog(this, "해당되지 않는 영수증번호입니다. 다시 확인해주세요 ");
					addSang1.setText("");
					addSang1.requestFocus();
					return;
				}
				totF.setText(String.valueOf(toTal));
				pointF.setText(String.valueOf(toTal));
				billArea.append("총 금액 :" + toTal + "원" );	
				
				
				
			} catch (Exception e2) {
				
				System.out.println("상품확인 에러 : " + e2);
			}finally {
				closeTry();
			}
			
		}else if(e.getSource() == bill ) {
			try {
				
				
				if(billArea.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "반품을 확인할 상품이 없습니다.");
					return;
				}
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
				sql = "SELECT B_SANGNUM, B_IRUM, B_HANG, B_IPGO, B_UTO, B_PANME, B_PEGI  FROM BILL WHERE B_BILL = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, addSang1.getText());
				rs1 = pstmt.executeQuery();
				
				while(rs1.next()) {
					conn2 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott","tiger");
					sql2 = "insert into P_CONVENIENCE values(?,?,?,?,?,?,?,?)";
					
					//P_SANGNUM,P_IRUM,P_HANG,P_IPGO,P_UTO,P_PANME,P_PEGI,BILLAFT
					pstmt2 = conn2.prepareStatement(sql2);
					
					System.out.println(rs1.getString("b_sangnum"));
					System.out.println(rs1.getString("b_irum"));
					System.out.println(rs1.getString("b_hang"));
					System.out.println(rs1.getString("b_ipgo"));
					System.out.println(rs1.getString("b_uto"));
					System.out.println(rs1.getString("b_panme"));
					System.out.println(rs1.getString("b_pegi"));
					
					
					
					pstmt2.setString(1, rs1.getString("b_sangnum"));
					
					pstmt2.setString(2, rs1.getString("b_irum"));
					pstmt2.setString(3, rs1.getString("b_hang"));
					pstmt2.setString(4, rs1.getString("b_ipgo").substring(0, 10));
					pstmt2.setString(5, rs1.getString("b_uto").substring(0, 10));
					pstmt2.setString(6, rs1.getString("b_panme"));
					pstmt2.setString(7, rs1.getString("b_pegi"));
					pstmt2.setString(8, null);
					
					pstmt2.executeUpdate();
				}
				
				delete();
				
				JOptionPane.showMessageDialog(this, "환불이 완료되었습니다.");
				
			
			} catch (Exception e2) {
				System.out.println("반품완료버튼 오류 : " + e2);
			}finally{
				closeTry();
			}
		}else if(e.getSource() == btnNew ) {
			reset();
		}else if(e.getSource() == bClose ) {
			reset();
		}

	}
	
	private void delete() {
		try {
			System.out.println("삭제버튼");
			conn3 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
			sql3 = "delete from bill where b_bill = ?";
			pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1, addSang1.getText());
			pstmt3.executeUpdate();
			
			billArea.setText("");
		} catch (Exception e) {
			System.out.println("delete err" + e);
		}

	}
	private void reset() {
		try {
			
			addSang1.setText("");
			billArea.setText(null);
			pointF.setText("");
			totF.setText("");
			
			
		} catch (Exception e2) {
			System.out.println(e2);
		}finally {
			closeTry();
		}
		

	}
	
	private void closeTry() {
		try {
			if (rs3 != null) rs1.close();
			if (rs2 != null) rs2.close();
			if (rs1 != null) rs3.close();
			if (pstmt3 != null) pstmt3.close();
			if (pstmt2 != null) pstmt2.close();
			if (pstmt != null) pstmt.close();
			if (conn3 != null) conn3.close();
			if (conn2 != null) conn2.close();
			if (conn != null) conn.close();
			
		} catch (Exception e3) {
			// TODO: handle exception
		}

	}

	public Java_banpum() {
		setTitle("반품");
		design();
		accDb();

		addListener();
		setBounds(200,200,380,850);
		setVisible(true);
		setAlwaysOnTop(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int re = JOptionPane.showConfirmDialog(Java_banpum.this,"종료?","종료",JOptionPane.YES_NO_OPTION);
				if(re == JOptionPane.YES_OPTION) {
					
					try {
						conn2 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
						sql2 = "UPDATE  P_CONVENIENCE  SET BILLAFT = NULL WHERE billaft = 'yes'";
						pstmt2 = conn2.prepareStatement(sql2);
						pstmt2.executeUpdate();
						
						
						conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
						sql =  "delete from bill where b_bill = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, okSang7.getText());
						pstmt.executeUpdate();
						
						if (rs1 != null) rs1.close();
						if (rs2 != null) rs2.close();
						if (pstmt2 != null) pstmt2.close();
						if (pstmt != null) pstmt.close();
						if (conn2 != null) conn2.close();
						if (conn != null) conn.close();
					}catch (Exception e2) {
						
					}
					dispose();
					
				}else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});
		
	
	}

	private void design() {
		//----- 영수증번호 입력창 -------
		setLayout(new FlowLayout());
		JPanel pPn1 = new JPanel(new FlowLayout());
		btnOk = new JButton("반품상품확인");
		addSang1 = new JTextField("", 10);
		pPn1.add(new JLabel("영수증번호 입력 : "));
		pPn1.add(addSang1);
		pPn1.add(btnOk);
		add(pPn1);
		
		
		//-0----반품 상품 확인창 area =------

		JScrollPane pane = new JScrollPane(billArea);
		billArea.setEditable(false);
		add(pane);
		
		

		
		//-- 영수증출력 전 창 ------ 계산 새로고침 취소 버튼 -----
		JPanel pPn6 = new JPanel();
		JPanel pPn7 = new JPanel();
		JPanel pPn8 = new JPanel();
		JPanel pPn9 = new JPanel();
		JLabel lblTot = new JLabel("총 금액 : ");
		JLabel lblpPint = new JLabel("환불금액 : ");

		totF = new JTextField("",7);
		pointF = new JTextField("",7);

		bill = new JButton("환불완료");
		btnNew = new JButton("새로고침");
		bClose = new JButton("취소");
		pPn6.add(lblTot);
		pPn6.add(totF);
		pPn7.add(lblpPint);
		pPn7.add(pointF);

		pPn9.add(bill);
		pPn9.add(btnNew);
		pPn9.add(bClose);
		add(pPn6);
		add(pPn7);
		add(pPn8);
		add(pPn9);

	}

	private void addListener() {
		btnOk.addActionListener(this);
		bClose.addActionListener(this);
		bill.addActionListener(this);
		btnNew.addActionListener(this);
	}

	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (Exception e) {
			System.out.println("book accDb 실패:" + e);
		}

	}



	public static void main(String[] args) {
			new Java_banpum();
			
	}

}
