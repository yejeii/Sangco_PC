package com.arahansa.view.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

// 메인 배경 화면
public class PanelImgLoad extends JPanel {
        
    Image image;

    public PanelImgLoad(String path) {
        image = Toolkit.getDefaultToolkit().createImage(path);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, this);
    }
}