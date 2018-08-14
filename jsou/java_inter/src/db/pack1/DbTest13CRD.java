package db.pack1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DbTest13CRD extends JFrame implements ActionListener {
	private JButton btnInsert, btnDelete, btnExit;
	private String[][] datas = new String[0][4];
	private String[] title = {"코드","상품명","수량","단가"};
	private DefaultTableModel model = new DefaultTableModel(datas, title);
	private JTable table = new JTable(model);
	private JLabel lblCou = new JLabel("건수 : 0 ");
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public DbTest13CRD() {
		
		btnInsert = new JButton("추가");
		btnDelete = new JButton("삭제");
		btnExit = new JButton("종료");
		
		btnInsert.addActionListener(this);
		btnDelete.addActionListener(this);
		btnExit.addActionListener(this);
		JPanel panel = new JPanel();
		panel.add(btnInsert);
		panel.add(btnDelete);
		panel.add(btnExit);
		add("North",panel);
		
		//table의 열폭조정
		//System.out.println(table.getColumnModel());
		System.out.println(table.getColumnCount());
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		
		JScrollPane pane = new JScrollPane(table);
		add("Center",pane);
		add("South",lblCou);
		
		setTitle("상품 처리");
		setResizable(false);
		setBounds(200,200,300,250);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int re = JOptionPane.showConfirmDialog(DbTest13CRD.this, "정말 종료하시게? ","종료",JOptionPane.OK_CANCEL_OPTION);
				if(re == JOptionPane.OK_OPTION) {
					try {
						try {
							if(rs != null) rs.close();
							if(pstmt != null) pstmt.close();
							if(conn != null) conn.close();
							System.exit(0);
						} catch (Exception e2) {
							// TODO: handle exception
						}
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});
		accDb();
		
	}
	private void accDb() {
		try {
			// 1. Driver 클래스 로딩;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (Exception e) {
			System.out.println("Driver 로딩 실패" + e.getMessage());
			return;
		}
		dispData();
	}
	
	private void dispData() {
		model.setNumRows(0);//테이블의 초기화;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			String sql = "select * from sangdata order by code desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int cou = 0 ;
			while(rs.next()) {
				String []imsi = { 
						rs.getString("code"),
						rs.getString("sang"),
						rs.getString("su"),
						rs.getString("dan")
				};
				model.addRow(imsi);
				cou++;
			}
			lblCou.setText("건수 : " + cou );
		} catch (Exception e) {
			System.out.println("dispData err " + e.getMessage());
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInsert) {// 상품추가 
			InsertForm InsertForm = new InsertForm(this);
			dispData(); //추가 후 자료 다시 읽기;
		}else if ( e.getSource() == btnDelete ){ //상품삭제
		String delNo = JOptionPane.showInputDialog(this, "삭제할 코드입력 ");
		if(delNo == null) return;
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");	
				//삭제 여부 묻기 필요 : 만들어봐랑;
				String sql = "delete from sangdata where code = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,delNo);
				
				if(pstmt.executeUpdate()==0) {
					JOptionPane.showMessageDialog(this, delNo + "등록된 코드가 아닙니다.");
					try {
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}else {
					
				}
				dispData(); // 삭제 후 자료 읽기;
			} catch (Exception e2) {
				System.out.println("삭제 실패 : " + e2);
			}finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e2) {
			
				}
			}
			
		}else if ( e.getSource() == btnExit) {//종료
			
		}
		
	}
	
	//자료추가용 내부클라스 - 대화상자
	
	class InsertForm extends JDialog implements ActionListener {
		JTextField txtSang = new JTextField();
		JTextField txtSu = new JTextField();
		JTextField txtDan = new JTextField();
		
		JButton btnOk = new JButton("등록");
		JButton btnCancel = new JButton("삭제");
		
		public InsertForm(JFrame frame) {
			super(frame,"상품추가");
			setModal(true); // Modal dialog;
			
			//추가화면 디자인;
			 JPanel pn1 = new JPanel(new GridLayout(4,2));
			 pn1.add(new JLabel("품명 : "));
			 pn1.add(txtSang);
			 pn1.add(new JLabel("수량 : "));
			 pn1.add(txtSu);
			 pn1.add(new JLabel("단가 : "));
			 pn1.add(txtDan);
			 pn1.add(btnOk);
			 pn1.add(btnCancel);
			 
			 btnOk.addActionListener(this);
			 btnCancel.addActionListener(this);
			 
			 add("North",new JLabel("*자료입력*"));
			 add("Center",pn1);
			 
			 setBounds(210, 210, 150, 150);
			 setVisible(true);
			 
			 addWindowListener(new WindowAdapter() {
				 @Override
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
			 
		}
		//추가다이아로그 액션퍼폼드;
		@Override
		public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnOk ) { //새상품 추가 작업
					//입력자료 오류검사 필요  -- 우리가 해볼것
					try {
						if(txtSang.getText().equals("")) {
						JOptionPane.showMessageDialog(this, "품명을 입력하세요");
							txtSang.requestFocus();
							return;
						
						}else if(txtSu.getText().equals("")) {
							JOptionPane.showMessageDialog(this, "수량을 입력하세요");
							txtSu.requestFocus();
							return;
						}else if(txtDan.getText().equals("")) {
							JOptionPane.showMessageDialog(this, "단가을 입력하세요");
							txtDan.requestFocus();
							return;
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
					try {
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
				
					//등록 가능한 상태 
					try {
						conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
						
						//새상품 코드 구하기;
						int new_code = 0;
						String sql = "select max(code) from sangdata ";
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						if(rs.next()) {
							new_code = rs.getInt(1);
						}else {
							
						}
						sql = "insert into sangdata values(?,?,?,?)";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, new_code + 1);
						pstmt.setString(2, txtSang.getText().trim());//trim() 앞뒤의 공백을 제거한다
						pstmt.setString(3, txtSu.getText().trim());
						pstmt.setString(4, txtDan.getText().trim());
						
						if(pstmt.executeUpdate() > 0) {
							JOptionPane.showMessageDialog(this, "등록성공" );
						}else {
							JOptionPane.showMessageDialog(this, "등록실패" );
							return;
						}
						// System.out.println(new_code + 1);
					} catch (Exception e2) {
						System.out.println("등록 실패 : " + e2.getMessage());
						
					}finally {
						try {
							if(rs != null) rs.close();
							if(pstmt != null) pstmt.close();
							if(conn != null) conn.close();
						} catch (Exception e2) {
							// TODO: handle exception
						}
					}
				}else { //입력자료 화면 지우기
					txtSang.setText(null);
					txtSu.setText(null);
					txtDan.setText(null);
					txtSang.requestFocus();
				}
			}
			
		}
	
	
	

	public static void main(String[] args) {
		new DbTest13CRD();

	}

}
