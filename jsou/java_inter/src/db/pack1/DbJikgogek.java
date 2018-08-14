package db.pack1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DbJikgogek extends JFrame implements ActionListener {
	JButton btnF, btnN, btnL, btnP;
	JTextField jikno, jikname, jik, buname, butel;
	JTextArea gogek = new JTextArea();

	Connection conn;
	Statement stmt, stmt2;
	ResultSet rs, rs2;

	public DbJikgogek() {
		setTitle("고객관리 사원");
		layInit();
		accDb();
		setBounds(550, 350, 800, 300);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int cf = JOptionPane.showConfirmDialog(DbJikgogek.this, "정말 종료할까요? ", "종료", JOptionPane.YES_NO_OPTION);
				if (cf == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					try {
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (conn != null)
							conn.close();

					} catch (Exception e2) {
						// TODO: handle exception
					}

				} else if (cf == JOptionPane.NO_OPTION) {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});

	}

	private void layInit() {
		jikno = new JTextField("", 5);
		jikname = new JTextField("", 5);
		jik = new JTextField("", 5);
		buname = new JTextField("", 5);
		butel = new JTextField("", 10);
		
		jikno.setEditable(false);
		jikname.setEditable(false);
		jik.setEditable(false);
		buname.setEditable(false);
		butel.setEditable(false);
		
		JPanel pn1 = new JPanel();
		pn1.add(new JLabel("사번 : "));
		pn1.add(jikno);
		pn1.add(new JLabel("직원명 : "));
		pn1.add(jikname);
		pn1.add(new JLabel("직급 : "));
		pn1.add(jik);
		pn1.add(new JLabel("부서명 : "));
		pn1.add(buname);
		pn1.add(new JLabel("부서번호 : "));
		pn1.add(butel);
		add("North", pn1);

		btnF = new JButton("|<<");
		btnP = new JButton("<");
		btnN = new JButton(">");
		btnL = new JButton(">>|");
		JPanel pn2 = new JPanel();
		pn2.add(btnF);
		pn2.add(btnP);
		pn2.add(btnN);
		pn2.add(btnL);
		add("South", pn2);
		gogek.setEditable(false);// 읽기만 가능
		JScrollPane pane = new JScrollPane(gogek);
		add("Center", pane);

		btnF.addActionListener(this);
		btnN.addActionListener(this);
		btnP.addActionListener(this);
		btnL.addActionListener(this);

	}

	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "tiger");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(
					"select distinct jikwon_no, jikwon_name, jikwon_jik, buser_name, buser_tel ,gogek_damsano from jikwon \r\n"
							+ "left outer join buser on buser_num = buser_no "
							+ "left outer join gogek on gogek_damsano = jikwon_no order by jikwon_no");

			rs.next();
			display();

		} catch (Exception e) {
			System.out.println("accDb err : " + e);
		}

	}

	private void display() {
		try {
			jikno.setText(rs.getString("jikwon_no"));
			jikname.setText(rs.getString("jikwon_name"));
			jik.setText(rs.getString("jikwon_jik"));
			buname.setText(rs.getString("buser_name"));
			butel.setText(rs.getString("buser_tel"));

			display2();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "자료의 처음 또는 마지막 입니다.");
		}
	}

	private void display2() {
		try {
			int cou = 0;
			String jikwon_no = rs.getString("jikwon_no");

			gogek.setText(null);
			stmt2 = conn.createStatement();
			rs2 = stmt2.executeQuery(
					"select gogek_no,gogek_name, "
					+ "decode(substr(gogek_jumin,8,1),'2','여','1','남') as 성별 ,"
					+ "trunc(months_between(sysdate,to_date(substr(gogek_jumin,1,6),'rrmmdd'))/12) as 나이 "
					+ "from gogek "
					+ "right outer join jikwon "
					+ "on gogek_damsano = jikwon_no "
					+ "where gogek_damsano =" 
					+ jikwon_no);

			String str = "";
			
			gogek.append("고객번호 " + "\t" + "고객이름" + "\t" + "고객성별" + "\t" + "고객나이" + "\n");

			while (rs2.next()) {
				str = rs2.getString(1) + "\t" + 
					  rs2.getString(2) + "\t" + 
					  rs2.getString(3) + "\t" + 
					  rs2.getString(4) + "\n";
				cou++;
				gogek.append(str);
			}

			gogek.append("\n\n\n고객확보 수  : " + cou + "명");

		} catch (Exception e) {
			System.out.println("accDb err : " + e);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnF)
				rs.first();
			else if (e.getSource() == btnP)
				rs.previous();
			else if (e.getSource() == btnN)
				rs.next();
			else if (e.getSource() == btnL)
				rs.last();
			display();
		} catch (Exception e2) {

		}

	}

	public static void main(String[] args) {
		new DbJikgogek();

	}

}
