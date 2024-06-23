package com.arahansa.view.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public // 광원처리 클래스
class MyStarPanel extends JPanel implements Runnable {

    Image img;
    int i = 1;
    int sleep = 25; 
    int sx = 77, sy=0;

    public MyStarPanel() {
        img = Toolkit.getDefaultToolkit().createImage("src/main/resources/img/starDdong.png");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(img!=null) g.drawImage(img, sx, sy, this);
    }

    @Override
    public void run() {
        try {
            do { 
                Thread.sleep(sleep);
                switch (i) {
                    case 1 -> {
                        sy+=2; if(sy>791) i=2;
                    }
                    case 2 -> {
                        sx+=2; if(sx>1507) i=3;
                    }
                    case 3 -> {
                        sy-=2; if(sy<53) i=4;
                    }
                    case 4 -> {
                        sx-=2; if(sx<77) i=1;
                    }
                }
                repaint();
            } while (true);
        } catch (InterruptedException e) {
        }
    }
}
