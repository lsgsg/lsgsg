package kr.co.charchar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Homepage {
	JLabel logoLabel, userEmail, password;
	JTextField name;
	JPasswordField pass;
	JButton login, register;
	static JFrame frame;
	Connection conn;
	Statement stmt;
	ResultSet rs;
	String sql, dbEmail, dbPass;

	public JPanel createContentPane() throws IOException {
		JPanel homeGUI = new JPanel();
		homeGUI.setLayout(null);
		homeGUI.setBackground(Color.WHITE);

		String dir = "C:\\work\\jsou\\java_chacha\\src\\";
		ImageIcon logo = new ImageIcon(dir + "logo.png");
		logoLabel = new JLabel(logo);

		logoLabel.setLocation(-10, -140);
		logoLabel.setSize(400, 400);

		userEmail = new JLabel("Email           : ");
		userEmail.setLocation(80, 110);
		userEmail.setSize(120, 20);

		name = new JTextField();
		name.setLocation(175, 115);
		name.setSize(120, 30);

		password = new JLabel("Password  : ");
		password.setLocation(80, 150);
		password.setSize(120, 30);

		pass = new JPasswordField();
		pass.setLocation(175, 155);
		pass.setSize(120, 20);

		login = new JButton("Login");
		login.setLocation(80, 190);
		login.setSize(100, 20);
		login.setForeground(Color.BLACK);

		register = new JButton("Register");
		register.setLocation(200, 190);
		register.setSize(100, 20);
		register.setForeground(Color.BLACK);

		homeGUI.add(logoLabel);
		homeGUI.add(userEmail);
		homeGUI.add(name);
		homeGUI.add(password);
		homeGUI.add(pass);
		homeGUI.add(login);
		homeGUI.add(register);

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Name = name.getText();
				String Pass = new String(pass.getPassword());
				boolean answer = false;

				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "tiger");
					sql = "select m_email, m_pass from member";
					stmt = (Statement) conn.createStatement();
					stmt.executeQuery(sql);
					rs = stmt.getResultSet();
					
					System.out.println("");
					
					while (rs.next()) {
						
						dbEmail = rs.getString("m_email");
						dbPass = rs.getString("m_pass");

						if (dbEmail.equals(Name) && dbPass.equals(Pass)) {
							JOptionPane.showMessageDialog(null, "로그인 성공");
							answer = true;
							break;
						} else {
							JOptionPane.showMessageDialog(null, "이메일과 비밀번호를 확인해주세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
							name.setText(null);
							pass.setText(null);
							name.requestFocus();
							
						}
						rs.close();
						stmt.close();
						conn.close();
					}

				} catch (Exception e2) {
					System.out.println("action listener err: " + e2);
				}
			}
		});
		return homeGUI;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					createAndShowGUI();
				} catch (Exception e) {
					System.out.println("런 에러: " + e);
				}
			}
		});

	}

	static void createAndShowGUI() throws IOException {
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame("CharChar Homepage");

		Homepage home = new Homepage();
		frame.setContentPane(home.createContentPane());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(380, 300);
		frame.setLocation(500, 220);
		frame.setVisible(true);

	}
}
