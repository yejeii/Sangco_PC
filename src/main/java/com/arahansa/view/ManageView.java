package com.arahansa.view;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import com.arahansa.asset.Setting;
import com.arahansa.view.panel.ClockMessage;
import com.arahansa.view.panel.ImgClock;
import com.arahansa.view.panel.MyStarPanel;
import com.arahansa.view.panel.PanelImgLoad;

public class ManageView extends JFrame {

    private JLayeredPane layeredPane = new JLayeredPane();

    // JPanels
    private PanelImgLoad backGround = new PanelImgLoad("src/main/resources/img/mainHud_back.png");
    private ImgClock imgClock = new ImgClock();
    private ClockMessage clockMessage = new ClockMessage();
    private MyStarPanel starPanel = new MyStarPanel();

    public static void main(String[] args) {
        new ManageView();
    }

    public ManageView() {

        // Frame 구성
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(Setting.bDimen);
        setTitle("ManageView");
        setLayout(null);
        setLocation(Setting.locationX, Setting.locationY);

        setPanel(layeredPane).setBounds(0, 0, 1600, 900);

        // 배경
        setPanel(backGround).setBounds(0, -30, 1600, 900);

        // 시계
        setPanel(imgClock).setBounds(15, 20, 179, 149);
        // new Thread(imgClock).start();

        // 시계글씨
        
        setPanel(clockMessage).setBounds(80, 53, 100, 100);
        // new Thread(clockMessage).start();

        // 움직이는 광원처리
        
        setPanel(starPanel).setBounds(0, -30, 1600, 900);
        // new Thread(starPanel).start();

        // Thread.start
        threadStart(imgClock, clockMessage, starPanel);
        
        // 최종 삽입
        // layeredPane.add(panelImgLoad, Integer.valueOf(0));
        // layeredPane.add(starPanel, Integer.valueOf(3));
        // layeredPane.add(imgClock, Integer.valueOf(4));
        // layeredPane.add(clockMessage, Integer.valueOf(5));
        add(setJLayer(backGround, starPanel, imgClock, clockMessage));

    }

    private JComponent setPanel(JComponent panel) {
        panel.setLayout(null);
        panel.setOpaque(false);

        return panel;
    }

    private JLayeredPane setJLayer(Component... components) {
        int i=0;
        for(Component component : components) {
            layeredPane.add(component, Integer.valueOf(i++));
        }        

        return layeredPane;
    }

    private void threadStart(Runnable... target) {
        for (Runnable runnable : target) {
            new Thread(runnable).start();
        }
    }
}
