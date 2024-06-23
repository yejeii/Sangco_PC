package com.arahansa.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SeatGUI extends JFrame {

    public static void main(String[] args) {
		new SeatGUI();
	}

    public SeatGUI() {
		
		add(new MyPanel(), BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("SeatTestGui");
		setSize(400, 400);
	}

	class MyPanel extends JPanel{
		Image img;
		public MyPanel() {
			img = Toolkit.getDefaultToolkit().createImage("src/main/resources/img/gameOff.png");
		}
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(img, 99, 99, this);
		}
	}
}
