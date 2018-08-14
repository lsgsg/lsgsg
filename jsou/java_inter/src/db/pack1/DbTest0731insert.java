package db.pack1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class DbTest0731insert extends JFrame implements ActionListener {
	JButton btnInsert;
	JTextField code, su, pumname, dan;
	JTextArea sangpum = new JTextArea();

	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	public DbTest0731insert() {
		setTitle("상품처리");
		layInit();
		accDb();
		setBounds(550, 350, 800, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display();
	}

	private void layInit() {

		code = new JTextField("", 5);
		pumname = new JTextField("", 5);
		su = new JTextField("", 5);
		dan = new JTextField("", 5);

		JPanel pn1 = new JPanel();
		pn1.add(new JLabel("코드 : "));
		pn1.add(code);
		pn1.add(new JLabel("품명 : "));
		pn1.add(pumname);
		pn1.add(new JLabel("수량 : "));
		pn1.add(su);
		pn1.add(new JLabel("단가 : "));
		pn1.add(dan);
		btnInsert = new JButton("추가");
		pn1.add(btnInsert);
		add("North", pn1);

		sangpum.setEditable(false);
		JScrollPane sangdata = new JScrollPane(sangpum);
		add("Center", sangdata);

		btnInsert.addActionListener(this);

	}

	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

		} catch (Exception e) {
			System.out.println("accDb err " + e);
		}
	}

	private void display() {
		try {
			sangpum.setText(null);
			int cou = 0;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from sangdata order by code");
			sangpum.append("코드 " + "\t" + "상품" + "\t" + "수량" + "\t" + "단가" + "\n");
			while (rs.next()) {
				String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
						+ "\n";
				cou++;
				sangpum.append(str);
			}

			sangpum.append("\n\n\n 건수 : " + cou);

		} catch (Exception e) {

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//셀렉트로 읽어서 코드값을 비교한후 false가 없으면 진행 true가 있으면 중복값이 있다를 알ㄻ;
		
		//숫자여부 판단
		try {
			Double.parseDouble(code.getText());
			Double.parseDouble(su.getText());
			Double.parseDouble(dan.getText());

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "숫자를 입력해주세요");
			return;
		}

		try {
			String sql = "insert into sangdata values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code.getText());
			pstmt.setString(2, pumname.getText());
			pstmt.setString(3, su.getText());
			pstmt.setString(4, dan.getText());

			
			

			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "추가성공");
			display();
		} catch (Exception e2) {
			//중복여부 판단.
			JOptionPane.showMessageDialog(null, "코드가 중복됩니다.");
			code.setText(null);

		}
		
		
	}

	public static void main(String[] args) {
		new DbTest0731insert();

	}

}
