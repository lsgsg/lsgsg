package db.pack1;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.function.IntConsumer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class DbTestTableClick extends JFrame {
	private static final LayoutManager FlowLayout = null;
	String[][] datas = new String[0][4];//0행 4열;
	String[] title = {"부서번호","부서명","부서위치","부서전화"};
	DefaultTableModel model;
	JTable table;
	JLabel lblCount;
	JTextArea textArea = new JTextArea();
	
	Connection conn;
	Statement stmt;
	PreparedStatement stmt2,stmt3;
	ResultSet rs,rs3;
	String bunum; 
	
	public DbTestTableClick() {
		setTitle("부서별 직원정보");
		layInit();
		accDb();
		setBounds(1000,250,400,700);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int cf = JOptionPane.showConfirmDialog(DbTestTableClick.this, "정말 종료하시게? ","종료",JOptionPane.YES_NO_OPTION);
				if(cf == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					try {
						if (rs != null) rs.close();
						if (stmt2 != null) stmt2.close();
						if (stmt != null) stmt.close();
						if (conn != null) conn.close();
					} catch (Exception e2) {
					
					}
				}else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		//setLayout(FlowLayout);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table = (JTable)e.getComponent();
				model = (DefaultTableModel)table.getModel();
				try {
					
					//String Cfix = (String)();
					
					bunum = (String)(model.getValueAt(table.getSelectedRow(), 0));
					System.out.println(bunum);
					msClick();
					
					
				} catch (Exception e2) {
					System.out.println("err : " + e);
				}finally {
					try {
						if (rs != null) rs.close();
						if (stmt2 != null) stmt2.close();
						if (stmt != null) stmt.close();
						if (conn != null) conn.close();
					} catch (Exception e2) {
					
					}
				}
			}
		});
		buserT();
		
	}
	private void msClick() {
		int cou;
		try {
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		
		
		
		String jikbu = "select jikwon_name,jikwon_no from jikwon where buser_num=?";
		stmt2 = conn.prepareStatement(jikbu);
		stmt2.setString(1, bunum);
		rs = stmt2.executeQuery();
		
		
		textArea.setText(null);
		
		
		String jikgo = "select gogek_name, gogek_tel, gogek_jumin from gogek where gogek_damsano = ?";
		stmt3 = conn.prepareStatement(jikgo);
		
		
		
		while(rs.next()) {
			cou=0;
			String str = rs.getString(1);
			textArea.append("직원명 : " + str + "\n" + "관리고객 " + "\n\n\n");
			stmt3.setString(1, rs.getString(2));
			rs3 = stmt3.executeQuery();
			
		
		
			


			textArea.append("고객명"+ "\t " +"고객전화"+ "\t " +"고객주민"+"\n");
			
			
			while(rs3.next()) {
				String str3 = rs3.getString(1)+ "\t " +
						  rs3.getString(2)+ "\t" +
						  rs3.getString(3)+ "\n "; 
				textArea.append(str3 + "\n\n");
				cou++;
			}
			
			if(cou>0) {
				
			}else {
				textArea.append("담당고객 없음\n\n");
			}
			textArea.append("---------------------------------- \n\n");
			
		}
		} catch (Exception e) {
			System.out.println("click err : " + e);
		}
		
	}
	private void layInit() {
		model = new DefaultTableModel(datas, title);
		table = new JTable(model);
		JScrollPane pane = new JScrollPane(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		add("North",pane);
		textArea.setEditable(false);//읽기만 가능
		JScrollPane pane2= new JScrollPane(textArea);
		add("Center",pane2);
	}
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
		} catch (Exception e) {
			System.out.println("드라이버 로딩 에러 :" + e);
		}finally {
			try {
				if (rs != null) rs.close();
				if (stmt2 != null) stmt2.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}catch (Exception e2) {
				
			}
		}
		
		
	}
	private void buserT() {
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
				String sql = "select buser_no, buser_name, buser_loc, buser_tel from buser order by buser_no";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				int cou = 0;
				
				while(rs.next()) {
					String buser_no = rs.getString(1);
					String buser_name = rs.getString(2);
					String buser_loc = rs.getString(3);
					String buser_tel = rs.getString(4);
					String [] imsi = {buser_no,buser_name,buser_loc,buser_tel};
					model.addRow(imsi);
					cou++;
				}
				lblCount = new JLabel("부서 건수 : " + cou );
				add("South",lblCount);
			} catch (Exception e) {
				// TODO: handle exception
			}

	}
	public static void main(String[] args) {
		new DbTestTableClick();

	}

}
