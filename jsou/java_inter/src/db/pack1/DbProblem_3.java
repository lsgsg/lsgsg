package db.pack1;

import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DbProblem_3 extends JFrame{
	// 1. DB 접속, SQL 실행 관련 멤버변수
	private Connection conn; 
	private PreparedStatement pstmt, pstmt2; // pstmt2: 관리고객 조회용 (직원명 조회 pstmt, rs를 닫을 수 없으므로)
	private ResultSet rs, rs2; // rs2 관리고객 조회용 
			
	// 2. Layout 관련 멤버변수
	private JTextArea txtArea; // = new JTextArea();
	
	// 3. Table 관련 멤버변수
	private JTable table;
	private DefaultTableModel model;
	private String[][] datas = new String[0][4]; // 테이블 구조 -> 4개의 컬럼
	private String [] colName = {"부서번호", "부서명", "부서전화", "부서위치"}; // 그 4개의 컬럼명
	
	// 생성자
	public DbProblem_3() {
		setTitle("부서별 직원정보");
		
		layInit(); // Frame 레이아웃 설정
		accDb(); // DB 연결 driver load + connection
		dispBuser(); // 부서 JTable에 데이터 출력
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int res = JOptionPane.showConfirmDialog(DbProblem_3.this, "정말 종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
				if(res == JOptionPane.YES_OPTION) {	
					try {
						if(rs != null) rs.close();
						if(rs2 != null) rs2.close();
						if(pstmt != null) pstmt.close();
						if(pstmt2 != null) pstmt2.close();
						if(conn != null) conn.close();
					}catch(Exception e2) {
						
					}
					System.exit(0);
				}else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});		
	}
	
	// 0-1) Frame 레이아웃 설정 메서드
	private void layInit() {
		setBounds(400,400,400,500);
		setLayout(new GridLayout(2, 1)); // 부서table, 직원txtArea
		
		// table 생성 (부서 데이터)
		model = new DefaultTableModel(datas, colName); // colName을 컬럼명으로 하는 4개의 컬럼을 갖는 table model 생성
		table = new JTable(model); // model의 데이터 구조의 table 생성
		
		// textArea 생성 (직원+고객 데이터)
		txtArea = new JTextArea();
		
		// JScrollPane에 컴포넌트 삽입
		JScrollPane sPanBuser = new JScrollPane(table); // 부서 데이터용 JScrollPane
		JScrollPane sPanJikGo = new JScrollPane(txtArea); // 직원+고객 데이터용 JScrollPane
		
		// Frame에 scrollJScrollPane 추가
		add(sPanBuser);
		add(sPanJikGo);
	
		// table에 mouse listener 추가
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				table = (JTable)e.getComponent();
//				System.out.println(table.getModel());
//				System.out.println(e.getComponent());
//				model = (DefaultTableModel)table.getModel();
//				System.out.println(model);
				int rowIdx = table.getSelectedRow(); // 선택된 행 인덱스
				int colIdx = table.getSelectedColumn(); // 선택된 컬럼 인덱스
				if(table.getColumnName(colIdx).equals("부서명")) {
//					System.out.println(table.getValueAt(rowIdx, 0)); // 선택된 행의 0번째컬럼(부서번호) 출력
					dispJikGo((String)table.getValueAt(rowIdx, 0)); // 선택된 행의 부서번호에 해당하는 직원+고객 데이터 출력 메서드 호출
				}
			}
		});
	}

	// 1) DB연결 메서드
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("accDb() err: " + e);
		} 
	}
	
	
	// 2) 부서 JTable에 데이터 출력하는 메서드
	private void dispBuser() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/orcl", "scott", "tiger");
		
			String sql = "select buser_no, buser_name, buser_tel, buser_loc from buser order by 1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String [] tmp = {rs.getString("buser_no"), 
							     rs.getString("buser_name"), 
								 rs.getString("buser_tel"), 
								 rs.getString("buser_loc")};
				model.addRow(tmp); // tmp 행 추가
			}
		}catch(Exception e) {
			System.out.println("dispBuser() err: "+ e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
			}
		}
		
	}
	
	// 3) JTextArea에 직원, 고객 데이터 출력 메서드
	private void dispJikGo(String buser_no) {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/orcl", "scott", "tiger");
			
			// 1 직원 이름만 가져오기
			String sql = "select jikwon_name, jikwon_no from jikwon where buser_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buser_no); // 매개변수로 받은 부서번호로 ? 바인드
			rs = pstmt.executeQuery();
			txtArea.setText(""); // 고객정보를 출력하기 전에 지우기
			
			int jikCnt=0; // 해당 부서에 근무하는 직원 수
			while(rs.next()) {
				// 2 해당 직원의 고객들 출력
				String sql2 = "select gogek_name, gogek_tel, substr(gogek_jumin,1,7) || '*******' gogek_jumin  from gogek where gogek_damsano = ?";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, rs.getString("jikwon_no")); // 직원 번호 조건 바인드
				rs2 = pstmt2.executeQuery();
			
				txtArea.append("직원명: " + rs.getString("jikwon_name") + 
						"\n ------------------------------------------\n");
			
				int cnt = 0; // 담당고객 수
				while(rs2.next()) {
					if(cnt==0) { // 고객 수가 0이라면  => 첫 번째 행이라면 데이터 출력 전에 타이틀을 출력
						txtArea.append("관리고객은\n");
						txtArea.append("고객명\t고객전화\t주민번호\n");
					}
					
					txtArea.append( rs2.getString("gogek_name") + "\t" +
									rs2.getString("gogek_tel") + "\t" +
									rs2.getString("gogek_jumin") + "\n"
									);
					cnt++; // 담당 고객 수 추가
				}
				if(cnt==0) {
					txtArea.append("담당 고객이 없습니다.\n");
				}
				txtArea.append("----------------------------------------------\n");
				txtArea.append("\n\n");
				jikCnt++; // 직원 수 추가
			}
			if(jikCnt == 0) { // 직원 수가 0이라면
				txtArea.append("해당 부서에서 근무하는 직원이 존재하지 않습니다.");
			}
		}catch(Exception e) {
			System.out.println("dispJikGo() err: " + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(rs2 != null) rs.close();
				if(pstmt2 != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
			}
		}
		
	}
	
	public static void main(String[] args) {
		new DbProblem_3();
	}
}
