package pack.gui;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyMemojang extends JFrame implements ActionListener {
	// 메뉴아이템
	JMenuItem mnuNew, mnuSave, mnuOpen, mnuExit;
	JMenuItem mnuCopy, mnuPaste, mnuCut, mnuDel;
	JMenuItem mnuAbout, mnuEtc1, mnuEtc2;

	// 팝업 메뉴
	JPopupMenu popup;
	JMenuItem mWhite, mBlue, mYellow;

	JButton btnCopy = new JButton("copy");
	JButton btnPaste = new JButton("Paste");
	JButton btnCut = new JButton("Cut");
	JButton btnDel = new JButton("Del");
	JPanel pn = new JPanel();
	JTextArea txtMemo = new JTextArea("");
	String copyText;

	public MyMemojang() {
		super("제목없음 - 메모장");
		initLayout();
		menuLayout();

		setBounds(200, 200, 400, 300);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				int re = JOptionPane.showConfirmDialog(MyMemojang.this, "정말 종료 할까요?", "종료",
						JOptionPane.YES_NO_OPTION);
				if (re == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}

			}
		});
	}

	private void initLayout() {
		pn.add(btnCopy);
		pn.add(btnCut);
		pn.add(btnDel);
		pn.add(btnPaste);
		add("South", pn);
		JScrollPane pane = new JScrollPane(txtMemo);
		// add("Center",txtMemo);
		add("Center", pane);

		btnCopy.addActionListener(this);
		btnCut.addActionListener(this);
		btnDel.addActionListener(this);
		btnPaste.addActionListener(this);

	}

	private void menuLayout() {
		JMenuBar menubar = new JMenuBar();

		JMenu mnuFile = new JMenu("파일");// 주메뉴

		mnuNew = new JMenuItem("새로만들기");
		mnuOpen = new JMenuItem("열기...");
		mnuSave = new JMenuItem("저장...");
		mnuExit = new JMenuItem("종료");
		mnuFile.add(mnuNew);
		mnuFile.add(mnuOpen);
		mnuFile.add(mnuSave);
		mnuFile.addSeparator();
		mnuFile.add(mnuExit);

		JMenu mnuEdit = new JMenu("편집");
		mnuCopy = new JMenuItem("복사");
		mnuPaste = new JMenuItem("붙여넣기");
		mnuCut = new JMenuItem("잘라내기");
		mnuDel = new JMenuItem("삭제");
		mnuEdit.add(mnuCopy);
		mnuEdit.add(mnuPaste);
		mnuEdit.add(mnuCut);
		mnuEdit.add(mnuDel);

		JMenu mnuHelp = new JMenu("도움말");
		mnuAbout = new JMenuItem("메모장 정보...");
		JMenu mnuEtc = new JMenu("기타");
		mnuEtc1 = new JMenuItem("계산기");
		mnuEtc2 = new JMenuItem("메모장");
		mnuEtc.add(mnuEtc1);
		mnuEtc.add(mnuEtc2);
		mnuHelp.add(mnuAbout);
		mnuHelp.add(mnuEtc);

		menubar.add(mnuFile);// 메뉴바에 메뉴 장착
		menubar.add(mnuEdit);
		menubar.add(mnuHelp);
		setJMenuBar(menubar);// Frame에 메뉴바 장착

		mnuNew.addActionListener(this);
		mnuOpen.addActionListener(this);
		mnuSave.addActionListener(this);
		mnuExit.addActionListener(this);
		mnuCopy.addActionListener(this);
		mnuPaste.addActionListener(this);
		mnuCut.addActionListener(this);
		mnuDel.addActionListener(this);
		mnuAbout.addActionListener(this);
		mnuEtc1.addActionListener(this);
		mnuEtc2.addActionListener(this);

		// 팝업메뉴
		popup = new JPopupMenu();
		JMenu mColor = new JMenu("색상선택");
		mWhite = new JMenuItem("하양");
		mBlue = new JMenuItem("파랑");
		mYellow = new JMenuItem("노랑");
		mColor.add(mWhite);
		mColor.add(mBlue);
		mColor.add(mYellow);
		popup.add(mColor);
		txtMemo.add(popup);

		mWhite.addActionListener(this);
		mBlue.addActionListener(this);
		mYellow.addActionListener(this);

		txtMemo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
					popup.show(txtMemo, e.getX(), e.getY());
				}
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCopy || e.getSource() == mnuCopy) {
			// System.out.println(txtMemo.getSelectedText());
			copyText = txtMemo.getSelectedText();
		} else if (e.getSource() == btnCut || e.getSource() == mnuPaste) {
			copyText = txtMemo.getSelectedText();

			int start = txtMemo.getSelectionStart();
			int end = txtMemo.getSelectionEnd();
			txtMemo.replaceRange(null, start, end);

		} else if (e.getSource() == btnDel || e.getSource() == mnuCut) {
			int start = txtMemo.getSelectionStart();
			int end = txtMemo.getSelectionEnd();
			txtMemo.replaceRange(null, start, end);

		} else if (e.getSource() == btnPaste || e.getSource() == mnuDel) {
			// txtMemo.setText(copyText);
			int p = txtMemo.getCaretPosition();
			txtMemo.insert(copyText, p);
		} else if (e.getSource() == mnuNew) {
			txtMemo.setText("");
			this.setTitle("제목없음 - 메모장");

		} else if (e.getSource() == mnuOpen) {
			FileDialog dialog = new FileDialog(this, "열기", FileDialog.LOAD);
			dialog.setDirectory(".");
			dialog.setVisible(true);
			if (dialog.getFile() == null)
				return;
			// System.out.println("박스 반환정보:"+dialog.getDirectory()+dialog.getFile());
			String dfName = dialog.getDirectory() + dialog.getFile();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(dfName));
				txtMemo.setText("");
				String line;
				while ((line = reader.readLine()) != null) {
					txtMemo.append(line + "\n");
				}

				reader.close();

				this.setTitle(dialog.getFile() + "메모장");
			} catch (Exception e2) {
				System.out.println("열기에러" + e2);
			}
		}

		else if (e.getSource() == mnuSave) {
			// 경로명 및 파일명 얻기 대화상자 호출
			FileDialog dialog = new FileDialog(this, "저장", FileDialog.SAVE);
			dialog.setDirectory(".");
			dialog.setVisible(true);
			if (dialog.getFile() == null)
				return;
			// System.out.println("박스 반환정보:"+dialog.getDirectory()+dialog.getFile());
			String dfName = dialog.getDirectory() + dialog.getFile();
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("c:/work/abc.txt"));
				writer.write(txtMemo.getText());
				writer.close();

				this.setTitle(dialog.getFile() + "메모장");
			} catch (Exception e2) {
				System.out.println("저장 오류:" + e2);
			}
		} else if (e.getSource() == mnuExit) {
			System.exit(0);

		} else if (e.getSource() == mnuAbout) {
			new MemoAbout(this);
			// System.out.println("창 호출 후");

		} else if (e.getSource() == mnuEtc1) {
			try {
				Runtime runtime = Runtime.getRuntime();
				runtime.exec("calc.exe");
			} catch (Exception e2) {

			}
		} else if (e.getSource() == mnuEtc2) {
			try {
				Runtime.getRuntime().exec("notepad.exe");
			} catch (Exception e2) {

			}
		} else if (e.getSource() == mWhite) {
			txtMemo.setBackground(Color.WHITE);
		} else if (e.getSource() == mBlue) {
			txtMemo.setBackground(Color.BLUE);
		} else if (e.getSource() == mYellow) {
			txtMemo.setBackground(Color.YELLOW);
		}

		txtMemo.requestFocus();

	}

	public static void main(String[] args) {
		new MyMemojang();

	}

}
