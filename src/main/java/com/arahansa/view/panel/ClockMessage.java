package com.arahansa.view.panel;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        timeLabel.setBounds(0, 0, 100, 20);
        timeLabel.setForeground(Setting.bColor);
        timeLabel.setFont(Setting.bFont);
        
        ampmLabel = new JLabel(ampm[i]);
        ampmLabel.setBounds(15, 20, 100, 30);
        ampmLabel.setForeground(Setting.bColor);
        ampmLabel.setFont(Setting.bFont);

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
}