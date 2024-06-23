package com.arahansa.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.arahansa.asset.Setting;
import com.arahansa.view.panel.ClockMessage;
import com.arahansa.view.panel.ImgClock;
import com.arahansa.view.panel.MyStarPanel;
import com.arahansa.view.panel.PanelImgLoad;

public class FrameManage extends JFrame {

    private JLayeredPane layeredPane = new JLayeredPane();

    // JPanels
    private PanelImgLoad backGround = new PanelImgLoad("src/main/resources/img/mainHud_back.png");
    private ImgClock imgClock = new ImgClock();
    private ClockMessage clockMessage = new ClockMessage();
    private MyStarPanel starPanel = new MyStarPanel();

    int posXpanSeat, posYpanSeat;
	PanSeat[] pan = new PanSeat[50];
	JPanel seat50 = new JPanel();
 
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        FrameManage manageView = new FrameManage();
        manageView.setRectangles(FrameManage.class, manageView, Setting.class, Setting.getInstance());
    }

    public FrameManage() {

        // Frame 구성
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(Setting.bDimen);
        setTitle("ManageView");
        setLayout(null);
        setLocation(Setting.locationX, Setting.locationY);

        // setPanel(layeredPane).setBounds(0, 0, 1600, 900);
        // setPanel(backGround).setBounds(0, -30, 1600, 900);
        // setPanel(imgClock).setBounds(15, 20, 179, 149);
        // setPanel(clockMessage).setBounds(80, 53, 100, 100);
        // setPanel(starPanel).setBounds(0, -30, 1600, 900);

        // threadStart(imgClock, clockMessage, starPanel);
        
        // 최종 삽입
        // add(setJLayer(backGround, starPanel, imgClock, clockMessage));

        for (int seat = 0; seat < 50; seat++) {
			pan[seat] = new PanSeat(seat);
			if (seat % 10 == 0 && seat != 0) {
				posXpanSeat = 0;
				posYpanSeat += 140;
			}
			pan[seat].setBounds(posXpanSeat, posYpanSeat, 99, 99);
			posXpanSeat += 135;
			// seat50.add(pan[seat]);
			
		}
		new SeatThread().start();
		add(setJLayer(backGround, starPanel, imgClock, clockMessage, seat50));
		add(layeredPane);

    }

    private JComponent setPanel(JComponent panel) {
        panel.setLayout(null);
        panel.setOpaque(false);

        return panel;
    }

    // Reflection
    public void setRectangles(Class<?> clazz, Object instance, Class<?> targetClass, Object target) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        Object tempObject = null;
        for (Field field : clazz.getDeclaredFields()) {
            if ((tempObject = field.get(instance)) instanceof JComponent) {
                ((Component) tempObject).setBounds((Rectangle) targetClass.getDeclaredField(field.getName()).get(target));
                ((JComponent) tempObject).setOpaque(false);
                ((Container) tempObject).setLayout(null);                
            }

            if(tempObject instanceof Runnable) {
                new Thread((Runnable) tempObject).start();
            }
        }
    }

    private JLayeredPane setJLayer(Component... components) {
        int i=0;
        for(Component component : components) {
            layeredPane.add(component, Integer.valueOf(i++));
        }        

        return layeredPane;
    }

    // 좌석 쇼 쓰레드
	class SeatThread extends Thread {
		@Override
		public void run() {
			Set<Integer> randomNumbers = new LinkedHashSet<Integer>();
			for (; randomNumbers.size() < 50;) {
				int x = (int) ((Math.random() * 50));
				randomNumbers.add(x);
			}
			int tmp = 0;
			try {
				for (Integer s : randomNumbers) {
					tmp++;
					if (tmp > 30)
						Thread.sleep(5*s);
					if (tmp == 50) {
						Thread.sleep(1000);
						System.out.println("50번째");
					}
					seat50.add(pan[s]);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

    // private void threadStart(Runnable... target) {
    //     for (Runnable runnable : target) {
    //         new Thread(runnable).start();
    //     }
    // }
}
