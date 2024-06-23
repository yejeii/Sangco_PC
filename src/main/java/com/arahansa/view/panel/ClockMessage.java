package com.arahansa.view.panel;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.arahansa.asset.Setting;

// 시계글씨 클래스
public class ClockMessage extends JPanel implements Runnable {

    int i = Calendar.getInstance().get(Calendar.AM_PM);
    String[] ampm = {"AM", "PM"};
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    String time = sdf.format(new Date());
    JLabel timeLabel, ampmLabel;

    public ClockMessage() {
        this.setLayout(null);

        timeLabel = new JLabel(time);
        setLabel(timeLabel).setBounds(Setting.timeLabel);
        
        ampmLabel = new JLabel(ampm[i]);
        setLabel(ampmLabel).setBounds(Setting.ampmLabel);

        add(timeLabel, BorderLayout.NORTH);
        add(ampmLabel, BorderLayout.CENTER);
    }

    public void run() {
        do {
            try {
                Thread.sleep(1000); // 1초
            } catch (InterruptedException e) {
            }
            timeLabel.setText(sdf.format(new Date()));
        } while(true);
    }

    // Setting Methods
    private JComponent setLabel(JComponent component) {
        component.setForeground(Setting.bColor);
        component.setFont(Setting.bFont);
        return  component;
    }
}