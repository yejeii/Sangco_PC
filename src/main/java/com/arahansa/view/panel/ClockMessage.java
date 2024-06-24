package com.arahansa.view.panel;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.arahansa.util.Setting;

// 시계글씨 클래스
public class ClockMessage extends JPanel implements Runnable, ConstraintPanel {

    private int ampmCode = Calendar.getInstance().get(Calendar.AM_PM);
    private String[] ampmValues = {"AM", "PM"};
    private String timeValue = new SimpleDateFormat("hh:mm:ss").format(new Date());
    private JLabel timeLabel, ampmLabel;

    private Object constrains;

    public ClockMessage(Object constrains) {
        this.constrains = constrains;
        init();
    }

    private void init() {
        this.setLayout(null);
        timeLabel = new JLabel(timeValue);
        setLabel(timeLabel).setBounds(Setting.timeLabel);
        
        ampmLabel = new JLabel(ampmValues[ampmCode]);
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
            timeLabel.setText(timeValue);
        } while(true);
    }

    // Setting Methods
    private JComponent setLabel(JComponent component) {
        component.setForeground(Setting.bColor);
        component.setFont(Setting.bFont);
        return  component;
    }

    @Override
    public Object getConstrains() {
        return this.constrains;
    }
}