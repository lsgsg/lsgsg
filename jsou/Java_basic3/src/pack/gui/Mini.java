package pack.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Mini extends JFrame implements ActionListener {
	JTextField txtSu1, txtSu2;
	JLabel lblResult;
	ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton plus, minus, gobhagi, nanugi;

	public Mini() {

		setTitle("미니 계산기");
		layIn();

		setBounds(200, 200, 300, 300);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void layIn() {
		setLayout(new GridLayout(5, 1));

		// 1
		JLabel lbl1 = new JLabel("숫자1 : ");
		txtSu1 = new JTextField("", 15);
		JPanel pn1 = new JPanel();
		pn1.add(lbl1);
		pn1.add(txtSu1);
		add(pn1);

		// 2
		JLabel lbl2 = new JLabel("숫자2 : ");
		txtSu2 = new JTextField("", 15);
		JPanel pn2 = new JPanel();
		pn2.add(lbl2);
		pn2.add(txtSu2);
		add(pn2);

		// 3
		plus = new JRadioButton("덧셈");
		minus = new JRadioButton("뺄셈");
		gobhagi = new JRadioButton("곱셈");
		nanugi = new JRadioButton("나눗셈");
		buttonGroup.add(plus);
		buttonGroup.add(minus);
		buttonGroup.add(gobhagi);
		buttonGroup.add(nanugi);
		JPanel pn3 = new JPanel();

		pn3.add(new JLabel("연산선택 : "));
		pn3.add(plus);
		pn3.add(minus);
		pn3.add(gobhagi);
		pn3.add(nanugi);
		add(pn3);

		// 4
		JButton btnMath = new JButton("계산");
		btnMath.addActionListener(this);
		JButton btnZero = new JButton("초기화");
		btnZero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSu1.setText(null);
				txtSu2.setText(null);
				buttonGroup.clearSelection();
				lblResult.setText(null);

			}

		});
		JButton btnEnd = new JButton("종료");
		btnEnd.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		;

		JPanel pn4 = new JPanel();
		pn4.add(btnMath);
		pn4.add(btnZero);
		pn4.add(btnEnd);

		add(pn4);

		// 5
		lblResult = new JLabel("결과 : ", JLabel.CENTER);
		add(lblResult);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (txtSu1.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "숫자1을 입력");
			txtSu1.requestFocus();
			return;
		}

		if (txtSu2.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "숫자2를 입력");
			txtSu2.requestFocus();
			return;
		}
		// 숫자 여부 판단
		int su1 = 0, su2 = 0;
		try {
			su1 = Integer.parseInt(txtSu1.getText());
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "나이는 숫자만 허용");
			txtSu1.requestFocus();
			return;

		}
		try {
			su2 = Integer.parseInt(txtSu2.getText());
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "나이는 숫자만 허용");
			txtSu2.requestFocus();
			return;

		}

		String choice = "";
		int a = 0;
		int total = 0;

		if (plus.isSelected()) {
			choice = "덧셈";
			total = su1 + su2;
		} else if (minus.isSelected()) {
			choice = "뺄셈";
			if (su1 >= su2) {
				total = su1 - su2;
			} else {
				total = su2 - su1;
			}
		} else if (gobhagi.isSelected()) {
			choice = "곱셈";
			total = su1 * su2;
		} else {
			choice = "나눗셈";
			if (su1 == 0 || su2 == 0) {
				JOptionPane.showMessageDialog(this, "0으로는 나눗셈 불가");
				txtSu1.setText(null);
				txtSu2.setText(null);
				txtSu1.requestFocus();
				a = 1;
			}

			else if (su1 >= su2) {
				total = su1 / su2;
			} else {
				total = su2 / su1;
			}
		}

		if (a == 1) {
			String ss1 = "나눗셈에는 0 입력 불가";
			lblResult.setText(ss1);

		} else {
			String ss2 = "결과:" + txtSu1.getText() + "와" + txtSu2.getText() + "의 " + choice + "은" + total + "입니다";

			lblResult.setText(ss2);
		}
	}

	public static void main(String[] args) {
		new Mini();

	}

}
