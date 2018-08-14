package db.pack1;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DbTest12Table extends JFrame{
	String[][] datas = new String[0][5]; // 0행 5열
	String[] title = {"코드","품명","수량","단가","금액"};
	DefaultTableModel model;
	JTable table;
	JLabel lblCount;
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public DbTest12Table() {
		setTitle("테이블 연습");
		
		layInit();
		accDb();
		
		setBounds(200,100,300,250);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table = (JTable)e.getComponent();// 마우스 클릭 대상 객체 얻어오기; -> 이벤트가 발생된 테이블을 얻을 수 있다.
				model = (DefaultTableModel)table.getModel();//모델은 값을 가지고 있음;
				//System.out.println(table);
				//System.out.println("행/열 번호 : " + table.getSelectedRow() + " / "+table.getSelectedColumn() );
				//System.out.println("열이릅 : " + table.getColumnName(table.getSelectedColumn()));//클릭할때마다 해당 열의 이름을 가져온다.
				//System.out.println("선택한 지점의 값 : " + model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
				//어느 행을 찍든 고정된 열의값을 가져오게 하고 싶을때, 
				//->
				System.out.println("열 고정 값 :" + model.getValueAt(table.getSelectedColumn(), 1));//1열기준으로 고정; 
			}
		});
		
	}
	
	private void layInit() {
		model = new DefaultTableModel(datas, title);
		table = new JTable(model);
		JScrollPane pane = new JScrollPane(table);
		
		add("Center",pane);
		
		
		

	}
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			pstmt = conn.prepareStatement("select * from sangdata order by code");
			rs = pstmt.executeQuery();
			
			int cou = 0 ;
			while(rs.next()) {
				String code = rs.getString("code");
				String sang = rs.getString("sang");
				String su = rs.getString("su");
				String dan = rs.getString("dan");
				int kum = rs.getInt("su") * rs.getInt("dan");
				String []imsi = {code,sang,su,dan,Integer.toString(kum)};
				model.addRow(imsi);
				cou++;
			}
			lblCount = new JLabel("건수 : "+ cou);
			add("South",lblCount);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null ) pstmt.close();
				if(conn != null) conn.close();
			}catch (Exception e2) {
				
			}
		}

	}
	
	public static void main(String[] args) {
	new DbTest12Table();

	}

}