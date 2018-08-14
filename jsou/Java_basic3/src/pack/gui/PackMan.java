package pack.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class PackMan extends JFrame implements KeyListener {
	Image image;
	int x, y, sel = 1;

	public PackMan() {
		super("상하좌우 화살표를 사용하세요");

		setLayout(null);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack1.jpg"));
		setBounds(200, 200, 300, 300);
		setBackground(Color.WHITE);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		x = getWidth() / 2;
		y = getHeight() / 2;
		addKeyListener(this);// frame에 KeyListener 장착
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		// System.out.println(key);
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_NUMPAD6) {
			// System.out.println("오른쪽");
			// if(sel==1)sel=2;else sel=1;
			sel = (sel == 1) ? 2 : 1;
			x = (x < getWidth()) ? x += 10 : -image.getWidth(this);

		} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_NUMPAD4) {
			sel = (sel == 3) ? 4 : 3;
			x = (x > 0) ? x -= 10 : getWidth() + image.getWidth(this);

		} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_NUMPAD2) {
			sel = (sel == 5) ? 6 : 5;
			y = (y < getHeight()) ? y += 10 : -image.getHeight(this);
		} else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_NUMPAD8) {
			sel = (sel == 7) ? 8 : 7;
			y = (y > 0) ? y -= 10 : getHeight() + image.getWidth(this);
		}
		repaint();
		// update(g);
	}

	@Override
	public void paint(Graphics g) {
		switch (sel) {
		case 1:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack1.jpg");
			break;
		case 2:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack2.jpg");
			break;
		case 3:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack3.jpg");
			break;
		case 4:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack4.jpg");
			break;
		case 5:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack5.jpg");
			break;
		case 6:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack6.jpg");
			break;
		case 7:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack7.jpg");
			break;
		case 8:
			image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack8.jpg");
			break;
		}

		// image = Toolkit.getDefaultToolkit().getImage("c:/work/pack/pack1.jpg");
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, x - image.getWidth(this) / 2, y - image.getHeight(this) / 2, this);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new PackMan();

	}

}
