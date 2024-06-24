package com.arahansa.view.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

// 메인 배경 화면
public class ManagerViewMainPanel extends JPanel implements ConstraintPanel {
        
    private final Image image;
    private final Object constrains;

    public ManagerViewMainPanel(String path, Object constrains) {
        this.image = Toolkit.getDefaultToolkit().createImage(path);
        this.constrains = constrains;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, this);
    }

    @Override
	public void update(Graphics g) {
		super.update(g);
	}

    @Override
    public Object getConstrains() {
        return this.constrains;
    }

}