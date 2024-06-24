package com.arahansa.view.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

// 광원처리 클래스
public class LightningPanel extends JPanel implements Runnable, ConstraintPanel {

    private final Image lightImage = Toolkit.getDefaultToolkit().createImage("src/main/resources/img/starDdong.png");
    private int sx = 77;
    private int sy = 0;

    private Object constrains;

    public LightningPanel(Object constrains) {
        this.constrains = constrains;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(lightImage, sx, sy, this);
    }

    @Override
    public void run() {
        int turnFlag = 1;

        // turnFlag 가 아래의 숫자일 때 광원 이동
        do { 
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (turnFlag) {
                case 1 -> {
                    // 위 -> 아래
                    sy+=2; if(sy>791) turnFlag=2;
                }
                case 2 -> {
                    // 왼 -> 오른
                    sx+=2; if(sx>1507) turnFlag=3;
                }
                case 3 -> {
                    // 오른 -> 위
                    sy-=2; if(sy<53) turnFlag=4;
                }
                case 4 -> {
                    // 오른 -> 왼
                    sx-=2; if(sx<77) turnFlag=1;
                }
            }
            repaint();
        } while (true);
    }

    @Override
    public Object getConstrains() {
        return this.constrains;
    }
}
