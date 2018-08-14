package db.pack1;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class DbtestExam4 extends JFrame{                 
	
	String[][] datas;
	ArrayList<String> title = new ArrayList<>();
	DefaultTableModel model;
	JTable table;
	JTextArea txtResult = new JTextArea();
	
	
	Connection conn;
	PreparedStatement pstmt1, pstmt2, pstmt3;
	ResultSet rs1, rs2, rs3;
	
	
	
	public DbtestExam4() {
		setTitle("부서별 직원정보");
		
		layInit();
		accDb();
		
		setBounds(200, 200, 400, 500);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int re = JOptionPane.showConfirmDialog(DbtestExam4.this,
						"정말 종료하시겠습니까?", "종료", JOptionPane.OK_CANCEL_OPTION);
				if (re == JOptionPane.OK_OPTION) {
					try {
						if (rs1 != null) rs1.close();
						if (pstmt1 != null) pstmt1.close();
						if (rs2 != null) rs2.close();
						if (pstmt2 != null) pstmt2.close();
						if (conn != null) conn.close();
						System.exit(0);
					} catch (Exception e2) {
					}
				} else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table = (JTable)e.getComponent();
				model = (DefaultTableModel)table.getModel();
				
				if(table.getColumnName(table.getSelectedColumn()).equals("부서명")) {
					String buser = (String)model.getValueAt(table.getSelectedRow(), 0);
					display(buser);					
				}
			}
		});
	}
	
	private void layInit() {
		int cou = 0;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "tiger");
			String tsql = "SELECT column_name FROM user_tab_columns WHERE table_name = 'BUSER'";
			pstmt3 = conn.prepareStatement(tsql);
			rs3 = pstmt3.executeQuery();
			while(rs3.next()) {
				title.add(rs3.getString(1));
				cou++;
			}
			
		datas = new String[0][cou];
		
		
		model = new DefaultTableModel(datas, title.toArray());
		table = new JTable(model);
		JScrollPane pane1 = new JScrollPane(table);
		pane1.setPreferredSize(new Dimension(150, 110));
		add("North", pane1);
		
		txtResult.setEditable(false);
		JScrollPane pane2 = new JScrollPane(txtResult);
		add("Center", pane2);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "tiger");
					
			String sql = "select * from buser order by buser_no";
			pstmt1 = conn.prepareStatement(sql);
			rs1 = pstmt1.executeQuery();
			
			while(rs1.next()) {
				String[] imsi = {rs1.getString(1), rs1.getString(2), rs1.getString(4), rs1.getString(3)};
				model.addRow(imsi);
				}
			
		} catch (Exception e) {
			System.out.println("d여기다"+e);
		} finally {
			try {
				if (rs1 != null) rs1.close();
				if (pstmt1 != null) pstmt1.close();
				if (rs2 != null) rs2.close();
				if (pstmt2 != null) pstmt2.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
			}
		}
	}
	
	private void display(String buser) {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "tiger");
			
			String sql1 = "select jikwon_name, jikwon_no from jikwon where buser_num = " + buser;
			pstmt1 = conn.prepareStatement(sql1);
			rs1 = pstmt1.executeQuery();
			
			txtResult.setText(null);
			
			if(rs1.isBeforeFirst()) {			
				while(rs1.next()) {
					txtResult.append("직원명 : ");
					txtResult.append(rs1.getString(1) + "\n");
					
					String sql2 = "select gogek_name, gogek_tel, gogek_jumin from gogek where gogek_damsano = " + rs1.getString(2);
					pstmt2 = conn.prepareStatement(sql2);
					rs2 = pstmt2.executeQuery();
					
					if (rs2.isBeforeFirst() ) {    
						txtResult.append("관리고객은" + "\n" + "고객명\t고객전화\t주민번호\n");
						
						while(rs2.next()) {
							txtResult.append(rs2.getString(1) + "\t" +
									rs2.getString(2) + "\t" +
									rs2.getString(3) + "\n");
						}
					  
					} else {
						txtResult.append("담당 고객이 없습니다\n");
					}
				
					txtResult.append("\n");
					
				}
			} else {
				txtResult.append("직원이 없습니다");
			}	
		} catch (Exception e) {			
		} finally {
			try {
				if (rs1 != null) rs1.close();
				if (pstmt1 != null) pstmt1.close();
				if (rs2 != null) rs2.close();
				if (pstmt2 != null) pstmt2.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
			}
		}
	}
	
	public static void main(String[] args) {
		new DbtestExam4();
	}
}
