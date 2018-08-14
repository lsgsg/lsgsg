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



public class Java_sale extends JFrame implements ActionListener {
	JButton btnSale, btnBan;
	JTextField addSang1, addSang2, addSang3, addSang4, addSang5;
	JTextField okSang1,okSang2,okSang3,okSang4,okSang5,okSang6,okSang7;
	JTextField billNum;
	JButton btnOk, btnAdd , btnPegi;
	JTextArea billArea = new JTextArea(15,20);
	JButton gogekCf;
	JRadioButton gogekY,gogekN;
	JTextField gogekNum;
	
	JButton bClose,bill,btnNew;
	JTextField totF,pointF,coinF;
	int iLast = 0; 	// 마지막 레코드 번호
	int toTal = 0;
	Connection conn,conn2;
	PreparedStatement pstmt,pstmt2;
	ResultSet rs1;
	ResultSet rs2;
	

	String sql,sql2 ;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOk ) { //상품확인
			try {
				//System.out.println(addSang1.getText());
				
				if(addSang1.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "바코드번호를 입력해 주세요");
				}
				
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
				sql = "select p_irum, p_hang, p_ipgo, p_uto, p_panme,p_pegi from p_convenience where p_sangnum = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, addSang1.getText());
				rs1 = pstmt.executeQuery();
				
				rs1.next();
				okSang1.setText(rs1.getString("p_irum"));
				okSang2.setText(rs1.getString("p_hang"));
				okSang3.setText(rs1.getString("p_ipgo"));
				okSang4.setText(rs1.getString("p_uto"));
				okSang5.setText(rs1.getString("p_panme"));
				okSang6.setText(rs1.getString("p_pegi"));
				
				
				
				
				 if(rs1.getString("p_pegi").equals("O")) {
					 JOptionPane.showMessageDialog(this, "이미 폐기처리된 상품입니다. 다른 상품으로 계산하세요");
					 addSang1.setText("");
					 okSang1.setText("");
					 okSang2.setText("");
					 okSang3.setText("");
					 okSang4.setText("");
					 okSang5.setText("");
					 okSang6.setText("");
					return;
				 }
				 
				 
				Date dt = new Date();
				//System.out.println(dt.toString());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
				//System.out.println(sdf.format(dt).toString()); 
				
				//System.out.println(sdf);
				
				String today = sdf.format(dt).toString();
				
				
				  String strStartDate = rs1.getString("p_uto");
			        String strEndDate = sdf.format(dt).toString();
			        String strFormat = "yyyy-MM-dd";    //strStartDate 와 strEndDate 의 format
			        
			        //SimpleDateFormat 을 이용하여 startDate와 endDate의 Date 객체를 생성한다.
			        SimpleDateFormat sdf2 = new SimpleDateFormat(strFormat);
			        try{
			            Date startDate = sdf2.parse(strStartDate);
			            Date endDate = sdf2.parse(strEndDate);
			 
			            //두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.
			            long diffDay = (startDate.getTime() - endDate.getTime()) / (24*60*60*1000);
			            //System.out.println(diffDay+"일");
			            if(diffDay <= -1) {
			            	btnAdd.setEnabled(false);
							JOptionPane.showMessageDialog(this, "계산 불가 \n 폐기상품 입니다.\n  폐기관리버튼을 눌러주세요");
						}
			        }catch (Exception e2) {
			        	System.out.println("날짜계산 오류 : " + e2);
					}
				
				
				
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "존재하지 않거나 이미 판매완료된 상품입니다. ");
				System.out.println("상품확인 에러 : " + e2);
			}finally {
				closeTry();
			}
			
		}else if(e.getSource() == btnAdd ) { // 상품추가 
			
			try {
				conn2 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
				sql2 = "update  p_convenience  set billAft = 'yes' where p_sangnum = ?";
				pstmt2 = conn2.prepareStatement(sql2);
				pstmt2.setString(1, addSang1.getText());
				pstmt2.executeUpdate();
				
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
				sql = "insert into bill values (?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, addSang1.getText());
				pstmt.setString(2, okSang1.getText());
				pstmt.setString(3, okSang2.getText());
				
				pstmt.setString(4, okSang3.getText().substring(0, 10));
				pstmt.setString(5, okSang4.getText().substring(0, 10));
				
				pstmt.setString(6, okSang5.getText());
				pstmt.setString(7, okSang6.getText());
				pstmt.setString(8, okSang7.getText());
				
				pstmt.executeUpdate();
				display();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "이미 추가된 상품입니다.");
				System.out.println("상품추가 버튼 오류 : " + e2);
			}finally {
				closeTry();
			}
			
			addSang1.setText("");
			okSang1.setText("");
			okSang2.setText("");
			okSang3.setText("");
			okSang4.setText("");
			okSang5.setText("");
			okSang6.setText("");
			
			
			
			
			
		}else if(e.getSource() == btnPegi ) {
			
			try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
			sql = "update p_convenience set p_pegi = 'O' where p_sangnum = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, addSang1.getText());
			pstmt.executeUpdate();
			
			addSang1.setText("");
			okSang1.setText("");
			okSang2.setText("");
			okSang3.setText("");
			okSang4.setText("");
			okSang5.setText("");
			okSang6.setText("");
			btnAdd.setEnabled(true);
			
			} catch (Exception e2) {
				System.out.println("폐기관리작업 오류 " + e2);
			}
			
		}else if(e.getSource() == gogekCf ) {
			
		}else if(e.getSource() == bill ) {
			try {
				
				
				
				if(pointF.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "결제액을 받지 않았습니다.");
					conn2 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
					sql2 = "UPDATE  P_CONVENIENCE  SET BILLAFT = NULL WHERE billaft = 'yes'";
					pstmt2 = conn2.prepareStatement(sql2);
					pstmt2.executeUpdate();
					
					conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
					sql =  "delete from bill where b_bill = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, okSang7.getText());
					pstmt.executeUpdate();
					
					pointF.requestFocus();
					
					init();
					return;
				}
				
				int tot = Integer.parseInt(totF.getText());
				int change = Integer.parseInt(pointF.getText());
				
				int coinCf = change -tot;
				System.out.println(coinCf);
				if(coinCf < 0 ) {
					JOptionPane.showMessageDialog(this, "결제가 불가합니다. 결제액을 확인해주세요");
					
					conn2 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
					sql2 = "UPDATE  P_CONVENIENCE  SET BILLAFT = NULL WHERE billaft = 'yes'";
					pstmt2 = conn2.prepareStatement(sql2);
					pstmt2.executeUpdate();
					
					conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
					sql =  "delete from bill where b_bill = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, okSang7.getText());
					pstmt.executeUpdate();
					
					pointF.requestFocus();
					
					init();
					return;
				}
				
				if(billArea.getText().equals("")) {
					System.out.println("영수증란에 아무것도 없음");
					try {
						addCalc();
					} catch (Exception e2) {
						
					}finally {
						closeTry();
					}
				}
				sql = "DELETE FROM P_CONVENIENCE WHERE BILLAFT = 'yes'";
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				
				
				JOptionPane.showMessageDialog(this, "계산이 완료되었습니다.");
				addSang1.setText("");
				okSang1.setText("");
				okSang2.setText("");
				okSang3.setText("");
				okSang4.setText("");
				okSang5.setText("");
				okSang6.setText("");
				init();
				btnAdd.setEnabled(true);
				billArea.setText(null);
			
			} catch (Exception e2) {
				
			}finally{
				closeTry();
			}
		}else if(e.getSource() == btnNew ) {
			reset();
		}else if(e.getSource() == bClose ) {
			reset();
		}

	}
	
	private void reset() {
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
			
			addSang1.setText("");
			okSang1.setText("");
			okSang2.setText("");
			okSang3.setText("");
			okSang4.setText("");
			okSang5.setText("");
			okSang6.setText("");
			init();
			btnAdd.setEnabled(true);
			billArea.setText(null);
			
		} catch (Exception e2) {
			System.out.println(e2);
		}finally {
			closeTry();
		}
		

	}
	
	private void closeTry() {
		try {
			if (rs1 != null) rs1.close();
			if (rs2 != null) rs2.close();
			if (pstmt2 != null) pstmt2.close();
			if (pstmt != null) pstmt.close();
			if (conn2 != null) conn2.close();
			if (conn != null) conn.close();
			
		} catch (Exception e3) {
			// TODO: handle exception
		}

	}
	private void addCalc() {
		try {
			conn2 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
			sql2 = "update  p_convenience  set billAft = 'yes' where p_sangnum = ?";
			pstmt2 = conn2.prepareStatement(sql2);
			pstmt2.setString(1, addSang1.getText());
			pstmt2.executeUpdate();
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
			sql = "insert into bill values (?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, addSang1.getText());
			pstmt.setString(2, okSang1.getText());
			pstmt.setString(3, okSang2.getText());
			
			pstmt.setString(4, okSang3.getText().substring(0, 10));
			pstmt.setString(5, okSang4.getText().substring(0, 10));
			
			pstmt.setString(6, okSang5.getText());
			pstmt.setString(7, okSang6.getText());
			pstmt.setString(8, okSang7.getText());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("편의점 영수증 출력여부 update & 영수증자료 insert 오류 : " + e);
		}

	}
	public Java_sale() {
		setTitle("판매");
		design();
		accDb();
		init();
		addListener();
		setBounds(100,100,380,700);
		setVisible(true);
		setAlwaysOnTop(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int re = JOptionPane.showConfirmDialog(Java_sale.this,"종료?","종료",JOptionPane.YES_NO_OPTION);
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
		//----- 상품번호 입력창 -------
		setLayout(new FlowLayout());
//		setEnabled(false);
		JPanel pPn1 = new JPanel(new FlowLayout());
		//pPn1.setSize(470,20);
		//pPn1.setLocation(0, 10);
		btnOk = new JButton("상품확인");
		addSang1 = new JTextField("", 10);
		pPn1.add(new JLabel("바코드 번호 : "));
		pPn1.add(addSang1);
		pPn1.add(btnOk);
		add(pPn1);
		
		//-----상품확인필드창 ------
		JPanel pPn2 = new JPanel();
		pPn2.setLayout(new GridLayout(7, 1));
		okSang1 = new JTextField("", 10);
		okSang2 = new JTextField("", 10);
		okSang3 = new JTextField("", 10);
		okSang4 = new JTextField("", 10);
		okSang5 = new JTextField("", 10);
		okSang6 = new JTextField("", 10);
		okSang7 = new JTextField("", 10);
		pPn2.add(new JLabel("상품명 : "));
		pPn2.add(okSang1);
		pPn2.add(new JLabel("행사 : "));
		pPn2.add(okSang2);
		pPn2.add(new JLabel("입고날짜 : "));
		pPn2.add(okSang3);
		pPn2.add(new JLabel("유통기한 : "));
		pPn2.add(okSang4);
		pPn2.add(new JLabel("가격 : "));
		pPn2.add(okSang5);
		pPn2.add(new JLabel("폐기상품 : "));
		pPn2.add(okSang6);
		pPn2.add(new JLabel("영수증번호 : "));
		pPn2.add(okSang7);
		
		//pPn2.setLocation(0,50);
		//pPn2.setSize(470,100);
		add(pPn2);
		
		
		//-----물품추가 폐기관리버튼 -----
		JPanel btnplus = new JPanel();
		btnplus .setLayout(new GridLayout(1, 2));
		btnAdd = new JButton("추가");
		btnPegi = new JButton("폐기상품관리");
		btnplus.add(btnAdd);
		btnplus.add(btnPegi);
		//btnplus.setLocation(0, 400);
		//btnplus.setSize(300,50);
		
		add(btnplus);
		
		//-0----영수증 출력 area =------

		JScrollPane pane = new JScrollPane(billArea);
		billArea.setEditable(false);
		//btnplus.setLocation(0, 2000);
		//btnplus.setSize(470,300);
		add(pane);
		
		
		//-- 고객정보 확인 -----------
		gogekCf = new JButton("고객정보확인");
		gogekY = new JRadioButton("Yes");
		gogekN = new JRadioButton("No");
		JLabel lblgobun = new JLabel("고객번호 : ");
		gogekNum = new JTextField("",5);
		gogekNum.setEditable(false);
		JPanel pPn4 = new JPanel();
		JPanel pPn5 = new JPanel();
		pPn4.add(gogekCf);
		pPn4.add(gogekY);
		pPn4.add(gogekN);
		pPn5.add(lblgobun);
		pPn5.add(gogekNum);
		pPn4.setLocation(0, 4000);
		pPn4.setSize(470,10);
		pPn5.setLocation(0, 5500);
		pPn5.setSize(470,10);
		add(pPn4);
		add(pPn5);
		
		//-- 영수증출력 전 창 ------ 계산 새로고침 취소 버튼 -----
		JPanel pPn6 = new JPanel();
		JPanel pPn7 = new JPanel();
		JPanel pPn8 = new JPanel();
		JPanel pPn9 = new JPanel();
		JLabel lblTot = new JLabel("총 금액 : ");
		JLabel lblpPint = new JLabel("결제금액 : ");
		JLabel lblpcoin = new JLabel("거스름돈 : ");
		totF = new JTextField("",7);
		pointF = new JTextField("",7);
		coinF = new JTextField("",7);
		bill = new JButton("계산");
		btnNew = new JButton("새로고침");
		bClose = new JButton("취소");
		pPn6.add(lblTot);
		pPn6.add(totF);
		pPn7.add(lblpPint);
		pPn7.add(pointF);
		
		pPn8.add(lblpcoin);
		pPn8.add(coinF);
		
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
		btnAdd.addActionListener(this);
		btnPegi.addActionListener(this);
		gogekCf.addActionListener(this);
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

	private void init() {
		System.out.println("init 확인");
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
			sql = "select b_bill from bill order by b_bill";
			pstmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE
					);// 레코드 이동이 좌우로 가능함
			rs1 = pstmt.executeQuery();
			rs1.last();
			iLast = rs1.getInt("b_bill");
			System.out.println(iLast);
			okSang7.setText(iLast + 1 + "");
		} catch (Exception e) {
			System.out.println("영수증번호 자동 에러 : " + e);
		}finally {
			try {
				if (rs1 != null) rs1.close();
				if (rs2 != null) rs2.close();
				if (pstmt2 != null) pstmt2.close();
				if (pstmt != null) pstmt.close();
				if (conn2 != null) conn2.close();
				if (conn != null) conn.close();
			}catch (Exception e2) {
				
			}
		}

	}

	private void display() {
		billArea.setText("");

		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
			sql = "select b_irum,count(*) as su,b_hang ,b_panme,b_bill from bill group by b_irum , b_panme,b_bill,b_hang having b_bill = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, okSang7.getText());
			rs1 = pstmt.executeQuery();
			System.out.println("display 확인");
			toTal = 0;
			while(rs1.next()) {
				int suNum = Integer.parseInt(rs1.getString("su"));
				int price = Integer.parseInt(rs1.getString("b_panme"));
				int calc = suNum * price;
				String str = "상품명 : " + rs1.getString("b_irum") + "\n" +
						     "수량 : " + rs1.getString("su") + "\n" +
						     "행사 : " + rs1.getString("b_hang") + "\n" +
						     "가격 : " + calc + "\n\n";
				
				
				toTal= toTal + calc;
				billArea.append(str);
//				int su = Integer.parseInt(rs1.getString("su"));
//				int hang = Integer.parseInt(rs1.getString("b_hang"));
//				if(hang == 1) {
//					if(su % 2 != 0) {
//					JOptionPane.showMessageDialog(this, "1+1 행사 제품입니다.");
//					}
//				}
			
			}
			billArea.append("총 금액 :" + toTal + "원" );	
			
			
			totF.setText(String.valueOf(toTal));
			int credit = Integer.parseInt(pointF.getText()) - toTal;
			coinF.setText(String.valueOf(credit));
			
		} catch (Exception e) {
			System.out.println("물품추가 영수증영역 오류 : " + e);
		}finally {
			closeTry();
		}
		

	}

	public static void main(String[] args) {
			new Java_sale();
			
	}

}
