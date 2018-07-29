package com.examhelper.app;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        List<String> selectList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\w\\..+\\w");
        Matcher matcher = pattern.matcher("A.18岁以下  B.25岁至35岁  C.35岁至45岁  D.45岁以上");
        while (matcher.find()) {
            selectList.add(matcher.group());
        }
        for (String s : selectList) {
            System.out.println(s);
        }
    }
}