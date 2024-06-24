package com.arahansa.view.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ClockBorderPanel extends JPanel implements Runnable, ConstraintPanel {

    private final Image clockBorderImage1 = Toolkit.getDefaultToolkit().createImage("src/main/resources/img/cl1.png");
	private final Image clockBorderImage2 = Toolkit.getDefaultToolkit().createImage("src/main/resources/img/cl2.png");
	private final Image clockBorderImage3 = Toolkit.getDefaultToolkit().createImage("src/main/resources/img/cl3.png");

    private final Image[] imges = {clockBorderImage1, clockBorderImage2, clockBorderImage3};
    private int imageIndex = 0;

    private Object constrains;

    public ClockBorderPanel(Object constrains) {
        this.constrains = constrains;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imges[imageIndex], 0, 0, this);
    }

    @Override
    public void run() {
        while (true) { 
            try {
                Thread.sleep(10000);    // 10초
                imageIndex = (imageIndex==2) ? 0 : ++imageIndex;
                repaint();
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public Object getConstrains() {
        return this.constrains;
    }
    
}