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

public class DbTest3_gui extends JFrame implements ActionListener {
	JButton btnAll = new JButton("전체");
	JButton btnM = new JButton("남");
	JButton btnF = new JButton("여");
	JTextArea txtResult = new JTextArea();

	// sql담당
	Connection conn;
	Statement stmt;
	ResultSet rs;

	public DbTest3_gui() {
		setTitle("고객 테이블 출력");

		layInit();
		accDb();

		setBounds(200, 200, 300, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void layInit() {
		JPanel panel = new JPanel();
		panel.add(btnAll);
		panel.add(btnM);
		panel.add(btnF);

		txtResult.setEditable(false); // read only
		JScrollPane pane = new JScrollPane(txtResult);

		add("Center", txtResult);
		add("North", panel);

		btnAll.addActionListener(this);
		btnM.addActionListener(this);
		btnF.addActionListener(this);

	}

	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "tiger");
			stmt = conn.createStatement();
			String sql = "select gogek_no,gogek_name,gogek_jumin from gogek";

			if (e.getSource() == btnAll) {

			} else if (e.getSource() == btnM) {
				sql += " where gogek_jumin like '%-1%'";// where문 시작전 한칸띄어쓰기해야댐
				// sql +=" where substr(gogek_jumin,8,1)=1";
			} else if (e.getSource() == btnF) {
				sql += " where gogek_jumin like '%-2%'";
			}
			// System.out.println(sql);

			txtResult.setText(null);// ""

			rs = stmt.executeQuery(sql);
			int cou = 0;
			while (rs.next()) {
				// System.out.println(rs.getString(2));
				String str = rs.getString("gogek_no") + "\t" + rs.getString("gogek_name") + "\t"
						+ rs.getString("gogek_jumin") + "\n";
				txtResult.append(str);
				cou++;
			}
			txtResult.append("인원수 : " + cou + "명");

		} catch (Exception e2) {
			System.out.println("actionPerformed err:" + e2);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e3) {
				// TODO: handle exception
			}
		}

	}

	public static void main(String[] args) {
		new DbTest3_gui();

	}

}
