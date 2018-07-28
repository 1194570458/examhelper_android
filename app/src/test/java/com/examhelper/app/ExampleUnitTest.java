package com.examhelper.app;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
        try {
            System.out.println(simpleDateFormat.parse("57:13").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        simpleDateFormat.
        Date date = new Date(1000);
        System.out.println(simpleDateFormat.format(date));
    }
}