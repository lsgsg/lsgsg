package db.pack1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DbTest4RecMove extends JFrame implements ActionListener {
	JButton btnF, btnN, btnL, btnP;
	JTextField txtNo, txtName;

	Connection conn;
	Statement stmt;
	ResultSet rs;

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnF) rs.first();
			else if (e.getSource() == btnP) rs.previous();
			else if (e.getSource() == btnN) rs.next();
			else if (e.getSource() == btnL) rs.last();
			display();
		} catch (Exception e2) {}
	}

	private void layInit() {

		txtNo = new JTextField("", 5);
		txtName = new JTextField("", 10);
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("직원자료 : "));
		panel1.add(txtNo);
		panel1.add(txtName);
		add("North", panel1);

		btnF = new JButton("|<<");
		btnP = new JButton("<");
		btnN = new JButton(">");
		btnL = new JButton(">>|");
		JPanel panel2 = new JPanel();
		panel2.add(btnF);
		panel2.add(btnP);
		panel2.add(btnN);
		panel2.add(btnL);
		add("Center", panel2);
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
			rs = stmt.executeQuery("select jikwon_no,jikwon_name from jikwon order by jikwon_no");
			rs.next();// 레코드 이동;
			display();// 레코드 포인터를 이동할때마다 자료를 뿌려주는 역할;

		} catch (Exception e) {
			System.out.println("accDb err : " + e);
		}

	}

	private void display() {
		try {
			txtNo.setText(rs.getString("jikwon_no"));
			txtName.setText(rs.getString("jikwon_name"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "자료의 처음 또는 마지막입니다.");
		}

	}

	public DbTest4RecMove() {

		setTitle("고객 테이블 출력");

		layInit();
		accDb();
		setBounds(200, 200, 300, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new DbTest4RecMove();

	}

}
