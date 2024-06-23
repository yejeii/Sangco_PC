package com.arahansa.date;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class CalendarAmPm {

    @Test
    public void testName() throws Exception {
        
        int i = Calendar.getInstance().get(Calendar.AM_PM);
        String[] ampm = {"AM", "PM"};
 
        System.out.println("Current AM PM val : " + i + ", " + ampm[i]);
        System.out.println();
        assertEquals(1, i);

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String time = sdf.format(new Date());
        System.out.println("Current time : " + time);
    }
}
